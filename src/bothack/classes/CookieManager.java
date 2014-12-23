package bothack.classes;

import java.util.Random;

/**
 * Created by administrator on 12/22/14.
 */
public class CookieManager {
    private Random r;

    public CookieManager(){
        r = new Random(433494437);
    }

    public String getCookie(){
        return new Integer(r.nextInt()).toString();
    }
}
