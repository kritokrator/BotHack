package bothack.classes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by administrator on 10/21/14.
 */
public class Main {
    public static void main(String[] args){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String password = md.digest("password".getBytes("UTF-16")).toString();
            System.out.println(password);
            byte[] passByte = md.digest("password".getBytes("UTF-8"));
            System.out.println(passByte);
            StringBuilder sb = new StringBuilder();
            for (byte b : passByte) {
                sb.append(String.format("%02X", b));
            }
            System.out.println(sb.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
   /*     Connection con = null;
        Statement st = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/bothack";
        String user = "admin";
        String password = "admin1";

        try {
            con = DriverManager.getConnection(url, user, password);
            //st = con.createStatement();
            pst = con.prepareStatement("SELECT * FROM users WHERE name=(?)");
            pst.setString(1,"test_player1");

            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(2)+ ':'+ rs.getString(3));
            }
            else{
                System.out.println("Query not found");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
*/
    }
}
