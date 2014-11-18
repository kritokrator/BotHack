package bothack.classes;

/**
 * Created by administrator on 10/21/14.
 */
public class Main {
    public static void main(String[] args){
        try {
            Nethack game = new Nethack();
            game.setup("118","104","109","99");
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
