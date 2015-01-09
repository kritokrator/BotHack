package bothack.agents.authentication;

import javax.security.auth.callback.*;
import java.io.IOException;

/**
 * Created by administrator on 12/22/14.
 */
public class NethackCallbackHandler implements CallbackHandler{
    String name;
    String password;

    public NethackCallbackHandler(String name, String password) {
        System.out.println("Callback Handler - constructor called");
        this.name = name;
        this.password = password;
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        System.out.println("Callback Handler - handle called");
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                NameCallback nameCallback = (NameCallback) callbacks[i];
                nameCallback.setName(name);
            } else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback passwordCallback = (PasswordCallback) callbacks[i];
                passwordCallback.setPassword(password.toCharArray());
            } else {
                throw new UnsupportedCallbackException(callbacks[i], "The submitted Callback is unsupported");
            }
        }
    }
}
