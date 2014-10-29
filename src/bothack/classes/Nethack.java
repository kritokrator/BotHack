package bothack.classes;
import bothack.interfaces.*;
import java.io.*;
import java.util.*;

/**
 * Created by administrator on 10/21/14.
 */
public class Nethack implements NethackInterface {
    private Process theGame;        //main nethack process
    private BufferedReader input;   //feedback sent from the game
    private BufferedReader errors;  //self explanatory really
    private BufferedWriter output;  //commands sent to nethack
    private String terminal;        //placeholder for the feedback
    private String terminalErrors;        //placeholder for the errors
    private static ArrayList<String> prompts = new ArrayList<String>(Arrays.asList(
            "command",
            "menu",
            "dummy",
            "direction",
            "number",
            "string")
            );
    static final String[] commands = {
            "nhapi-yn-function",
            "nhapi-update-inventory",
            "nhapi-clear-map",
            "nhapi-print-glyph",
            "nhapi-create-menu",
            "nhapi-menu-putstr",
            "nhapi-display-menu",
            "nhapi-message",
            "nhapi-curs",
    };
    static final String[] statusMessages = {
            "You are",
    };
    public Nethack(){
        // this is the default constructor, creates a random character for the user
        terminal = new String();
        int character;
        terminalErrors = new String();
        String userInput = new String();
        try {
            theGame = new ProcessBuilder("nethack-lisp").start();
            input = new BufferedReader(new InputStreamReader(theGame.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(theGame.getOutputStream()));
            errors = new BufferedReader(new InputStreamReader(theGame.getErrorStream()));

            while(true){
                terminal = readNethackLine();
                if(prompts.contains(terminal))
                {
                    System.out.println(terminal);
                    //TODO user input handling here 'handleUserInput(terminal)'
                    System.out.println("UserInterface input handling here");
                    break;
                }
                System.out.println(terminal);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setup() {
        System.out.println("Hello, world!");
    }

    @Override
    public void play() throws NotYetImplementedException {
        //TODO: implement this
        throw new NotYetImplementedException();

    }

    @Override
    public void save() throws NotYetImplementedException {
        //TODO: implement this
        throw new NotYetImplementedException();

    }

    @Override
    public void quit() throws NotYetImplementedException {
        //TODO implement this
        throw new NotYetImplementedException();
    }

    @Override
    public String readNethackLine() throws IOException {
        int character;
        String result = "";
        character = input.read();
        if(character == '(')
        {
            result += Character.toString((char)character);
            result +=input.readLine();
        }
        else{
            result += Character.toString((char)character);
            while((character = input.read()) != 62)
            {
                result += Character.toString((char)character);

            }
        }

        return result;
    }
}
