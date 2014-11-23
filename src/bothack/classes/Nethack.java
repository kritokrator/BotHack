package bothack.classes;
import bothack.interfaces.*;
import java.io.*;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Created by administrator on 10/21/14.
 */
public class Nethack implements NethackInterface {
    private Process theGame;                        //main nethack process
    private BufferedReader input;                   //feedback sent from the game
    private BufferedReader errors;                  //self explanatory really
    private BufferedWriter output;                  //commands sent to nethack
    private String terminal;                        //placeholder for the feedback
    private String terminalErrors;                  //placeholder for the errors
    private NethackMap theMap;                      //this will contain the current level of the game
    private PlayerCharacter avatar;                 //this will represent the player character of the user
    private boolean readyForUserInput;              // this will be set to true if nethack is read to accept a command
    private ArrayList<Object> objectContainer;      // this will contain questions, inventory and other various objects
    private NethackCommandObject lastCommandPrompt; // this will contain the last command prompt given by the NethackGame

    private static ArrayList<String> prompts = new ArrayList<String>(Arrays.asList(
            "command",
            "menu",
            "dummy",
            "direction",
            "number",
            "string")
            );
    static final ArrayList<String> outputResult = new ArrayList<String>(Arrays.asList(
            "nhapi-update-status",
            "nhapi-print-glyph",
            "nhapi-yn-function",
            "nhapi-start-menu",
            "nhapi-add-menu",
            "nhapi-select-menu",
            "nhapi-end-menu"
            )
    );
    static final String[] statusMessages = {
            "You are",
    };
    public Nethack(){
        // this is the default constructor, creates a random character for the user
        try {
            theMap = new NethackMap();
            avatar = new PlayerCharacter();
            objectContainer = new ArrayList<Object>();
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


    public void setup(String role,String race,String gender,String alignment) {
        while(true){
            try {
                processInputBoolean();
                if (lastCommandPrompt != null && lastCommandPrompt.getPrompt().contains("command")) {
                    System.out.println("we're ready to play!");
                    break;
                }
                if(objectContainer == null){
                    throw new NullPointerException("objectContainer is a null pointer");
                }
                for (Iterator<Object> obj = objectContainer.iterator();obj.hasNext();) {
                    
                    Object o = obj.next();
                    if (o instanceof NethackChoiceObject) {
                        if (((NethackChoiceObject) o).getText().contains("game in progress")) {
                            obj.remove();
                            sendNethackCommand(new NethackChoice(121));
                        }
                        else if(((NethackChoiceObject) o).getText().contains("Shall I pick a character for you? [ynq]")){
                            obj.remove();
                            sendNethackCommand(new NethackChoice(110));
                        }
                    }
                    else if( o instanceof NethackMenuObject){
                        if(((NethackMenuObject) o).getCaption().contains("race")){
                            obj.remove();
                            sendNethackCommand(new NethackMenuChoice(((NethackMenuObject) o).getMethod(),Integer.parseInt(race)));
                        }
                        else if(((NethackMenuObject) o).getCaption().contains("role")){
                            obj.remove();
                            sendNethackCommand(new NethackMenuChoice(((NethackMenuObject) o).getMethod(),Integer.parseInt(role)));
                        }
                        else if(((NethackMenuObject) o).getCaption().contains("gender")){
                            obj.remove();
                            sendNethackCommand(new NethackMenuChoice(((NethackMenuObject) o).getMethod(),Integer.parseInt(gender)));
                        }
                        else if(((NethackMenuObject) o).getCaption().contains("alignment")){
                            obj.remove();
                            sendNethackCommand(new NethackMenuChoice(((NethackMenuObject) o).getMethod(),Integer.parseInt(alignment)));
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }
    /*public void setup(String role,String race,String gender,String alignment){
        try {
            processInputBoolean();
            for(Object o : objectContainer){
                if(o instanceof NethackChoiceObject){
                    if(lastCommandPrompt.getPrompt().equals("number")){
                        sendNethackCommand(new NethackChoice(110));
                    }
                    obj.remove();
                }
            }
            processInputBoolean();
            for(Object o : objectContainer){
                if(o instanceof NethackMenuObject){
                    if(lastCommandPrompt.getPrompt().equals("menu")){
                        sendNethackCommand(new NethackMenuChoice("pick-one",Integer.parseInt(role)));
                    }
                    obj.remove();
                }
            }
            processInputBoolean();
            for(Object o : objectContainer){
                if(o instanceof NethackMenuObject){
                    if(lastCommandPrompt.getPrompt().equals("menu")){
                        sendNethackCommand(new NethackMenuChoice("pick-one",Integer.parseInt(race)));
                    }
                    obj.remove();
                }
            }
            processInputBoolean();
            for(Object o : objectContainer){
                if(o instanceof NethackMenuObject){
                    if(lastCommandPrompt.getPrompt().equals("menu")){
                        sendNethackCommand(new NethackMenuChoice("pick-one",Integer.parseInt(gender)));
                    }
                    obj.remove();
                }
            }
            processInputBoolean();
            for(Object o : objectContainer){
                if(o instanceof NethackMenuObject){
                    if(lastCommandPrompt.getPrompt().equals("menu")){
                        sendNethackCommand(new NethackMenuChoice("pick-one",Integer.parseInt(gender)));
                    }
                    obj.remove();
                }
            }
            processInputBoolean();
            for(Object o : objectContainer){
                if(o instanceof NethackMenuObject){
                    if(lastCommandPrompt.getPrompt().equals("menu")){
                        sendNethackCommand(new NethackMenuChoice("pick-one",Integer.parseInt(alignment)));
                    }
                    obj.remove();
                }
            }
            processInputBoolean();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }*/

    @Override
    public void play() throws NotYetImplementedException {
        String command;
        try {
            while(theGame.isAlive()) {
                    readNethackLine();
                }
        } catch (IOException e)
        {
               e.printStackTrace();
        }
        finally {
            System.out.println("Nethack instance is no longer alive");
        }
    }

    @Override
    public void save() throws NotYetImplementedException {
        //TODO: implement this
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
            while(input.ready())
            {
                character = input.read();
                result += Character.toString((char)character);
            }
            //process command prompt
            output.write(getUserInput(result));
            output.flush();
        }
    }
    public Object readNethackLineObject() throws IOException{
        int character;
        String result = "";
        character = input.read();
        if (character == '('){
            result += Character.toString((char)character);
            result +=input.readLine();
            //process command output
            System.out.println(result);
            return processNethackCommandObject(result);
        }
        else{
            //the buffer contains an user prompt, the game is waiting for user input
            result += Character.toString((char)character);
            while(input.ready())
            {
                character = input.read();
                result += Character.toString((char)character);
            }
            if(result.contains("dummy")){
                sendNethackDummyCommand();
                return null;
            }
            else {
                readyForUserInput = true;
                NethackCommandObject tmp = new NethackCommandObject(result);
                lastCommandPrompt = tmp;
                return tmp;
            }
        }
    }
    public Object processNethackCommandObject(String output){
        String[] tmp = output.split(" ");                   // placeholder for the name of the command with the preceeding parenthesis
        String command = tmp[0].substring(1);               // the actual command without the preceeding '(' and without other parameters
        if(outputResult.contains(command)){
            //process the command
            if(output.contains("nhapi-print-glyph")){
                theMap.update(output);
                return null;
            }
            else if(output.contains("nhapi-update-status")){
                try {
                    avatar.updateStatus(output);
                    return null;
                } catch (PlayerUpdateStatusException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            else if(output.contains("nhapi-yn-function")){
                NethackChoiceObject result = new NethackChoiceObject(output);
                objectContainer.add(result);
                return null;
            }
            else if(output.contains("nhapi-start-menu")){
                NethackMenuObject menu = new NethackMenuObject(output);
                objectContainer.add(menu);
                return null;
            }
            else if(output.contains("nhapi-add-menu") || output.contains("nhapi-end-menu")){
                for(Object o : objectContainer){
                    if(o instanceof NethackMenuObject){
                        ((NethackMenuObject) o).parseInput(output);
                    }
                }
                return null;
            }
            else if(output.contains("nhapi-select-menu")){
                for(Object o : objectContainer){
                    if(o instanceof NethackMenuObject){
                        ((NethackMenuObject) o).parseInput(output);
                        return (NethackMenuObject) o;
                    }
                }
                return null;
            }
            else{
                System.err.println("command is in progress");
                return null;
            }
        }
        else{
            //System.err.println("the command has not been implemented yet");
            return null;
        }
    }


    @Override
    public void quit() throws NotYetImplementedException {
        //TODO implement this
        throw new NotYetImplementedException();
    }
    @Override
    public String getUserInput(String prompt){
        Scanner keyboard = new Scanner(in);
        System.out.printf("enter a %s command: ", prompt);
        String input = keyboard.nextLine();

        return input + "\n";

    }
    public void processNethackCommand(String output){
        String[] tmp = output.split(" ");                   // placeholder for the name of the command with the preceeding parenthesis
        String command = tmp[0].substring(1);               // the actual command without the preceeding '(' and without other parameters
        if(outputResult.contains(command)){
            //process the command
            if(output.contains("nhapi-print-glyph")){
                theMap.update(output);
            }
            else if(output.contains("nhapi-update-status")){
                try {
                    avatar.updateStatus(output);
                } catch (PlayerUpdateStatusException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.err.println("command is in progress");
            }
        }
        else{
            //System.err.println("the command has not been implemented yet");
        }
    }
    public boolean sendNethackCommand(NethackChoice choice){
        if(lastCommandPrompt.getPrompt().equals("number")){
            try {
                readyForUserInput = false;
                this.output.write(choice.getChoice() + "\n");
                this.output.flush();
                return true;
            }
            catch(IOException e){
                e.printStackTrace();
                return false;
            }
        }
        else{
            return false;
        }
    }
    public boolean sendNethackCommand(NethackMenuChoice choice) {
        if (lastCommandPrompt.getPrompt().equals("menu")) {
            try {
                readyForUserInput = false;
                this.output.write(choice.getMenu() + "\n");
                this.output.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;

        }
    }
    public void sendNethackCommand(Command c){
        try{
            readyForUserInput = false;
            this.output.write(c.getDescription()+"\n");
            this.output.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }
    public void sendNethackDummyCommand() {
        try {
            readyForUserInput = false;
            this.output.write("\n");
            this.output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object processInputObject() throws IOException{
        Object result = null;
        while(result == null){
            result = readNethackLineObject();
        }
        return result;
    }
    public void processInputBoolean() throws  IOException{
        while(!readyForUserInput){
            readNethackLineObject();
        }
    }

}