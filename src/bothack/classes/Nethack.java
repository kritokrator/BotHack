package bothack.classes;
import bothack.interfaces.*;
import java.io.*;
import java.util.*;

import static java.lang.System.in;

/**
 * Created by administrator on 10/21/14.
 */
public class Nethack implements NethackInterface {
    private Process theGame;            //main nethack process
    private BufferedReader input;       //feedback sent from the game
    private BufferedReader errors;      //self explanatory really
    private BufferedWriter output;      //commands sent to nethack
    private String terminal;            //placeholder for the feedback
    private String terminalErrors;      //placeholder for the errors
    private NethackMap theMap;          //this will contain the current level of the game
    private PlayerCharacter avatar;     //this will represent the player character of the user

    private static ArrayList<String> prompts = new ArrayList<String>(Arrays.asList(
            "command",
            "menu",
            "dummy",
            "direction",
            "number",
            "string")
            );
    static final ArrayList<String> outputResult = new ArrayList<String>(Arrays.asList(
            "nhapi-yn-function",
            "nhapi-update-inventory",
            "nhapi-clear-map",
            "nhapi-print-glyph",
            "nhapi-create-menu",
            "nhapi-menu-putstr",
            "nhapi-display-menu",
            "nhapi-message",
            "nhapi-curs")
    );
    static final String[] statusMessages = {
            "You are",
    };
    public Nethack(){
        // this is the default constructor, creates a random character for the user
        try {
            theMap = new NethackMap();
            avatar = new PlayerCharacter();
            theGame = new ProcessBuilder("nethack-lisp").start();
            input = new BufferedReader(new InputStreamReader(theGame.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(theGame.getOutputStream()));
            errors = new BufferedReader(new InputStreamReader(theGame.getErrorStream()));
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
        String command;
        if(theGame.isAlive()){
            try {
                while(input.ready()){
                    readNethackLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.err.println("The nethack-process died while executing. Check up on this");
        }

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
    public void readNethackLine() throws IOException {
        int character;
        boolean prompt = false;
        String result = "";
        character = input.read();
        if(character == '(')
        {
            //the buffer contains an output of an command
            result += Character.toString((char)character);
            result +=input.readLine();
            //process command output
            System.out.println(result);
            processNethackCommand(result);
        }
        else{
            //the buffer contains an user prompt, the game is waiting for user input
            result += Character.toString((char)character);
            while((character = input.read()) != 62)
            {
                result += Character.toString((char)character);
            }
            //process command prompt
            output.write(getUserInput(result));
            output.flush();
        }
    }

    public String getUserInput(String prompt){
        Scanner keyboard = new Scanner(in);
        System.out.printf("enter a %s command", prompt);
        String input = keyboard.nextLine();
        return input;
    }
    public void processNethackCommand(String output){
        if(outputResult.contains(output)){
            //process the command
        }
        else{
            System.err.println("the command has not been implemented yet");
        }
    }
}
