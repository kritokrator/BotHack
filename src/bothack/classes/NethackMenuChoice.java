package bothack.classes;

/**
 * Created by administrator on 11/13/14.
 */
public class NethackMenuChoice {
    private String method;
    private char choice;

    public NethackMenuChoice(){
        method = "";
        choice ='0';
    }
    public NethackMenuChoice(String m,int c){
        method = m;
        choice = (char)c;
    }
    public String getMethod(){
        return method;
    }
    public void setMethod(String m){
        method = m;
    }
    public char getChoice(){
        return choice;
    }
    public void setChoice(int c){
        choice = (char)c;
    }

    public String getMenu(){
        return method + " "+choice;
    }

}
