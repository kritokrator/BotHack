package bothack.agents.authentication;

import bothack.classes.*;
import bothack.classes.Error;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.swing.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.*;
import java.util.Map;

/**
 * Created by administrator on 12/22/14.
 */
public class NethackLoginModule implements LoginModule{
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;
    private String filename;
    private String url;
    private String user;
    private String password;

    private boolean succeeded = false;

    public NethackLoginModule() {

    }

    public boolean abort() throws LoginException {
        return false;
    }

    public boolean commit() throws LoginException {
        return succeeded;
    }
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
                           Map<String, ?> options) {

        System.out.println("Login Module - initialize called");
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;

        if(options.get("database") != null){
            //setup player database from MySql database
            url = (String) options.get("database");
            user = (String)options.get("user");
            password = (String) options.get("password");
        }

        succeeded = false;
    }

    public boolean login() throws LoginException {
        Error error= null;
        if (callbackHandler == null) {
            throw new LoginException("Oops, callbackHandler is null");
        }
        if(url == null){
            throw new LoginException("Database URL not set");
        }

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("name:");
        callbacks[1] = new PasswordCallback("password:", false);

        try {
            callbackHandler.handle(callbacks);
        } catch (IOException e) {
            throw new LoginException("Oops, IOException calling handle on callbackHandler");
        } catch (UnsupportedCallbackException e) {
            throw new LoginException("Oops, UnsupportedCallbackException calling handle on callbackHandler");
        }

        NameCallback nameCallback = (NameCallback) callbacks[0];
        PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];

        String name = nameCallback.getName();
        String password = new String(passwordCallback.getPassword());

        if(url != null){
            error = databaseCheck(name,password);
        }
        else if(filename != null){
            error = fileCheck(name,password);
        }
        if(error.getCode() == 0){
            succeeded = true;
            return succeeded;
        }
        else {
            succeeded = false;
            throw new FailedLoginException(error.getCode() + " "+ error.getText());
        }
    }

    public Error fileCheck(String name, String password){
        Error error = new Error();
        return error;
    }

    public bothack.classes.Error databaseCheck(String name, String password){
        Error error = new Error();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(this.url,this.user,this.password);
            pst = connection.prepareStatement("SELECT * FROM users WHERE name=(?)");
            pst.setString(1,name);

            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(2)+ ':'+ rs.getString(3));
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] inputPassword = password.getBytes("UTF-8");
                byte[] inputHash = md.digest(inputPassword);
                String databaseHash = rs.getString(3);
                StringBuilder sb = new StringBuilder();
                for (byte b : inputHash) {
                    sb.append(String.format("%02X", b));
                }
                if(sb.toString().equals(databaseHash.toUpperCase())){
                    error.setCode(new Integer(0));
                    error.setText("Success");
                }
                else{
                    error.setCode(new Integer(140));
                    error.setText("Password incorrect");
                }
            }
            else{
                error.setCode(new Integer(141));
                error.setText("User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            error.setCode(new Integer(142));
            error.setText("SqlException : \n" + e.getMessage());
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (connection != null) {
                    connection.close();
                }
                } catch (SQLException e) {
                e.printStackTrace();
                e.printStackTrace();
                error.setCode(new Integer(142));
                error.setText("SqlException : \n" + e.getMessage());
                return error;
            }
            return error;
        }
    }

    public boolean logout() throws LoginException {
        return false;
    }

}
