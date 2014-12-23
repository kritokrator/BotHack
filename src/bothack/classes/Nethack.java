package bothack.classes;
import bothack.interfaces.*;

import java.io.*;
import java.util.*;

import static java.lang.System.in;

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
    private boolean update;

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
            "nhapi-end-menu",
            "nhapi-exit-nhwindows",
            "nhapi-message",
            "nhapi-menu-putstr",
            "nhapi-display-menu"
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
    public Nethack(String user){
        try {
            theMap = new NethackMap();
            avatar = new PlayerCharacter();
            objectContainer = new ArrayList<Object>();
            theGame = new ProcessBuilder("nethack-lisp" ,"-u",user).start();
            input = new BufferedReader(new InputStreamReader(theGame.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(theGame.getOutputStream()));
            errors = new BufferedReader(new InputStreamReader(theGame.getErrorStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setup() {
        while(true) {
            try {
                processInputBoolean();
                if (lastCommandPrompt != null && lastCommandPrompt.getPrompt().contains("command")) {
                    System.out.println("we're ready to play!");
                    break;
                }
                if (objectContainer == null) {
                    throw new NullPointerException("objectContainer is a null pointer");
                }
                for(Iterator<Object> obj = objectContainer.iterator();obj.hasNext();){
                    Object o = obj.next();
                    if(o instanceof NethackChoiceObject){
                        if(((NethackChoiceObject) o).getText().contains("Shall I pick a character for you? [ynq]")){
                            obj.remove();
                            sendNethackCommand(new NethackChoice(121));
                        }
                        else  if (((NethackChoiceObject) o).getText().contains("game in progress")) {
                            obj.remove();
                            sendNethackCommand(new NethackChoice(121));
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void createNethackDeathObject(){
        while(true) {
            try {
                processInputBoolean();

            if (lastCommandPrompt.getPrompt().contains("menu")) {
                sendNethackDummyCommand();
            }
            if(lastCommandPrompt.getPrompt().contains("quit")){
                break;
            }
            for (Iterator<Object> obj = objectContainer.iterator(); obj.hasNext(); ) {
                Object o = obj.hasNext();
                if (o instanceof NethackChoiceObject) {
                    obj.remove();
                    sendNethackCommand(new NethackChoice(121));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        try {
            theGame.getInputStream().close();
            theGame.getOutputStream().close();
            theGame.getErrorStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
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
                            sendNethackCommand(new NethackMenuChoice(Integer.parseInt(race)));
                        }
                        else if(((NethackMenuObject) o).getCaption().contains("role")){
                            obj.remove();
                            sendNethackCommand(new NethackMenuChoice(Integer.parseInt(role)));
                        }
                        else if(((NethackMenuObject) o).getCaption().contains("gender")){
                            obj.remove();
                            sendNethackCommand(new NethackMenuChoice(Integer.parseInt(gender)));
                        }
                        else if(((NethackMenuObject) o).getCaption().contains("alignment")){
                            obj.remove();
                            sendNethackCommand(new NethackMenuChoice(Integer.parseInt(alignment)));
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }


    @Override
    public void save() throws NotYetImplementedException {
        try{
            if(lastCommandPrompt.getPrompt().contains("command")){
                sendNethackCommand(Command.SAVE);
                while(true){
                    processInputBoolean();
                    if (lastCommandPrompt != null && lastCommandPrompt.getPrompt().contains("quit")) {
                        System.out.println("game is shut down");
                        break;
                    }
                    if(objectContainer == null){
                        throw new NullPointerException("objectContainer is a null pointer");
                    }
                    for(Iterator<Object> obj = objectContainer.iterator();obj.hasNext();){
                        Object o = obj.next();
                        if(o instanceof NethackChoiceObject){
                            if(((NethackChoiceObject) o).getText().contains("Really save")){
                                obj.remove();
                                sendNethackCommand(new NethackChoice(121));
                            }
                        }
                    }

                }
                theGame.getInputStream().close();
                theGame.getOutputStream().close();
                theGame.getErrorStream().close();
            }
            else{
                System.out.println("Nethack : different command expected");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private Object readNethackLineObject() throws IOException{
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
        else if(character != -1){
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
            else{
                readyForUserInput = true;
                NethackCommandObject tmp = new NethackCommandObject(result);
                lastCommandPrompt = tmp;
                return tmp;
            }
        }
        else{
            return null;
        }
    }

    private Object processNethackCommandObject(String output){
        String[] tmp = output.split(" ");                   // placeholder for the name of the command with the preceeding parenthesis
        String command = tmp[0].substring(1);               // the actual command without the preceeding '(' and without other parameters
        if(outputResult.contains(command)){
            //process the command
            if(output.contains("nhapi-print-glyph")){
                theMap.update(output);
                update = true;
                return null;
            }
            else if(output.contains("nhapi-update-status")){
                try {
                    avatar.updateStatus(output);
                    update = true;
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
            else if(output.contains("nhapi-exit-nhwindows")){
                lastCommandPrompt = new NethackCommandObject("quit");
                readyForUserInput=true;
                return null;
            }
            else if(output.contains("welcome to NetHack!")){
                avatar.updateGenderRoleRace(output);
                return null;
            }
            else if(output.contains("nhapi-menu-putstr")){
                for(Object o : objectContainer){
                    if(o instanceof NethackTextWindowObject){
                        if(((NethackTextWindowObject) o).isActive()){
                            ((NethackTextWindowObject) o).parseInput(output);
                            return null;
                        }
                        else if(!((NethackTextWindowObject) o).isActive()){
                            NethackTextWindowObject tmp1 = new NethackTextWindowObject();
                            tmp1.parseInput(output);
                            return null;
                        }
                    }
                }
                NethackTextWindowObject tmp2 = new NethackTextWindowObject();
                tmp2.parseInput(output);
                return null;
            }
            else if(output.contains("nhapi-display-menu")){
                for(Object o : objectContainer){
                    if(o instanceof NethackTextWindowObject && ((NethackTextWindowObject) o).isActive()){
                        ((NethackTextWindowObject) o).setActive(false);
                        return null;
                    }
                }
            }
            else if(output.contains("nhapi-message")){
                if(output.contains("You die..")){
                        lastCommandPrompt.setPrompt("dead");
                }
                NethackMessageObject tmpMessage = new NethackMessageObject();
                tmpMessage.parseInput(output);
                objectContainer.add(tmp);
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
        return null;
    }

    @Override
    public void quit() {
        if(lastCommandPrompt.getPrompt().contains("command")){
            sendNethackCommand(Command.QUIT);
            while(true){
                try
                {
                    processInputBoolean();
                    if (lastCommandPrompt != null && lastCommandPrompt.getPrompt().contains("quit")) {
                        System.out.println("game is shut down");
                        break;
                    }
                    if(objectContainer == null){
                        throw new NullPointerException("objectContainer is a null pointer");
                    }
                    for(Iterator<Object> obj = objectContainer.iterator();obj.hasNext();){
                        Object o = obj.next();
                        if(o instanceof NethackChoiceObject){
                            if(((NethackChoiceObject) o).getText().contains("Really quit")){
                                obj.remove();
                                sendNethackCommand(new NethackChoice(121));//yes
                            }
                            else if(((NethackChoiceObject) o).getText().contains("possessions identified")){
                                obj.remove();
                                sendNethackCommand(new NethackChoice(121));//yes
                            }
                            else if(((NethackChoiceObject) o).getText().contains("attributes")){
                                obj.remove();
                                sendNethackCommand(new NethackChoice(121));//yes
                            }
                            else if(((NethackChoiceObject) o).getText().contains("conduct")){
                                obj.remove();
                                sendNethackCommand(new NethackChoice(121));//yes
                            }
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            try {
                theGame.getInputStream().close();
                theGame.getOutputStream().close();
                theGame.getErrorStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Different command expected");
        }
    }


    @Override
    public Object action(NethackStringObject s){
        Error errCheck = checkInput(s);
        if(errCheck != null){
            return errCheck;
        }
        objectContainer.clear();
        sendNethackCommand(s);
        try{
            processInputBoolean();
            String prompt = lastCommandPrompt.getPrompt();
            if( prompt.contains("command") || prompt.contains("direction") || prompt.contains("string")){
                return lastCommandPrompt;
            }
            else if(prompt.contains("menu")){
                for(Iterator<Object> objectIterator = objectContainer.iterator();objectIterator.hasNext();){
                    Object o = objectIterator.next();
                    if(o instanceof NethackMenuObject){
                        return o;
                    }
                }
            }
            else if(prompt.contains("number")){
                for(Iterator<Object> objectIterator = objectContainer.iterator();objectIterator.hasNext();){
                    Object o = objectIterator.next();
                    if(o instanceof NethackChoiceObject){
                        return o;
                    }
                }
            }
            else if(prompt.contains("dead")){
                createNethackDeathObject();
                return new NethackDeathObject();

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Object action(NethackDirectionObject d){
        Error errCheck = checkInput(d);
        if(errCheck != null){
            return errCheck;
        }
        objectContainer.clear();
        sendNethackCommand(d);
        try{
            processInputBoolean();
            String prompt = lastCommandPrompt.getPrompt();
            if( prompt.contains("command") || prompt.contains("direction") || prompt.contains("string")){
                return lastCommandPrompt;
            }
            else if(prompt.contains("menu")){
                for(Iterator<Object> objectIterator = objectContainer.iterator();objectIterator.hasNext();){
                    Object o = objectIterator.next();
                    if(o instanceof NethackMenuObject){
                        return o;
                    }
                }
            }
            else if(prompt.contains("number")){
                for(Iterator<Object> objectIterator = objectContainer.iterator();objectIterator.hasNext();){
                    Object o = objectIterator.next();
                    if(o instanceof NethackChoiceObject){
                        return o;
                    }
                }
            }
            else if(prompt.contains("dead")){
                createNethackDeathObject();
                return new NethackDeathObject();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Object action(Command c){
        Error errCheck = checkInput(c);
        if(errCheck != null){
            return errCheck;
        }
        objectContainer.clear();
        sendNethackCommand(c);
        try{
            processInputBoolean();
            String prompt = lastCommandPrompt.getPrompt();
            if( prompt.contains("command") || prompt.contains("direction") || prompt.contains("string")){
                return lastCommandPrompt;
            }
            else if(prompt.contains("menu")){
                for(Iterator<Object> objectIterator = objectContainer.iterator();objectIterator.hasNext();){
                    Object o = objectIterator.next();
                    if(o instanceof NethackMenuObject){
                        return o;
                    }
                }
            }
            else if(prompt.contains("number")){
                for(Iterator<Object> objectIterator = objectContainer.iterator();objectIterator.hasNext();){
                    Object o = objectIterator.next();
                    if(o instanceof NethackChoiceObject){
                        return o;
                    }
                }
            }
            else if(prompt.contains("dead")){
                createNethackDeathObject();
                return new NethackDeathObject();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object action(NethackChoice nc){
        Error errCheck = checkInput(nc);
        if(errCheck != null){
            return errCheck;
        }
        objectContainer.clear();
        sendNethackCommand(nc);
        try{
            processInputBoolean();
            String prompt = lastCommandPrompt.getPrompt();
            if( prompt.contains("command") || prompt.contains("direction") || prompt.contains("string")){
                return lastCommandPrompt;
            }
            else if(prompt.contains("menu")){
                for(Iterator<Object> objectIterator = objectContainer.iterator();objectIterator.hasNext();){
                    Object o = objectIterator.next();
                    if(o instanceof NethackMenuObject){
                        return o;
                    }
                }
            }
            else if(prompt.contains("number")){
                for(Iterator<Object> objectIterator = objectContainer.iterator();objectIterator.hasNext();){
                    Object o = objectIterator.next();
                    if(o instanceof NethackChoiceObject){
                        return o;
                    }
                }
            }
            else if(prompt.contains("dead")){
                createNethackDeathObject();
                return new NethackDeathObject();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Object action(NethackMenuChoice nmc){
        Error errCheck = checkInput(nmc);
        if(errCheck != null){
            return errCheck;
        }
        objectContainer.clear();
        sendNethackCommand(nmc);
        try{
            processInputBoolean();
            String prompt = lastCommandPrompt.getPrompt();
            if( prompt.contains("command") || prompt.contains("direction") || prompt.contains("string")){
                return lastCommandPrompt;
            }
            else if(prompt.contains("menu")){
                for(Iterator<Object> objectIterator = objectContainer.iterator();objectIterator.hasNext();){
                    Object o = objectIterator.next();
                    if(o instanceof NethackMenuObject){
                        return o;
                    }
                }
            }
            else if(prompt.contains("number")){
                for(Iterator<Object> objectIterator = objectContainer.iterator();objectIterator.hasNext();){
                    Object o = objectIterator.next();
                    if(o instanceof NethackChoiceObject){
                        return o;
                    }
                }
            }
            else if(prompt.contains("dead")){
                createNethackDeathObject();
                return new NethackDeathObject();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void quitFast(){
        if(lastCommandPrompt.getPrompt().contains("command")){
            sendNethackCommand(Command.QUIT);
            while(true){
                try
                {
                    processInputBoolean();
                    if (lastCommandPrompt != null && lastCommandPrompt.getPrompt().contains("quit")) {
                        System.out.println("game is shut down");
                        break;
                    }
                    if(objectContainer == null){
                        throw new NullPointerException("objectContainer is a null pointer");
                    }
                    for(Iterator<Object> obj = objectContainer.iterator();obj.hasNext();){
                        Object o = obj.next();
                        if(o instanceof NethackChoiceObject){
                            if(((NethackChoiceObject) o).getText().contains("Really quit")){
                                obj.remove();
                                sendNethackCommand(new NethackChoice(121));//yes
                            }
                            else if(((NethackChoiceObject) o).getText().contains("possessions identified")){
                                obj.remove();
                                sendNethackCommand(new NethackChoice(110));//no
                            }
                            else if(((NethackChoiceObject) o).getText().contains("attributes")){
                                obj.remove();
                                sendNethackCommand(new NethackChoice(110));//no
                            }
                            else if(((NethackChoiceObject) o).getText().contains("conduct")){
                                obj.remove();
                                sendNethackCommand(new NethackChoice(110));//no
                            }
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            try {
                theGame.getInputStream().close();
                theGame.getOutputStream().close();
                theGame.getErrorStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Different command expected");
        }
    }

    private String getUserInput(String prompt){
        Scanner keyboard = new Scanner(in);
        System.out.printf("enter a %s command: ", prompt);
        String input = keyboard.nextLine();

        return input + "\n";

    }

    private boolean sendNethackCommand(NethackChoice choice){
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

    private boolean sendNethackCommand(NethackMenuChoice choice) {
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

    private void sendNethackCommand(Command c){
        try{
            readyForUserInput = false;
            this.output.write(c.getDescription()+"\n");
            this.output.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }

    private void sendNethackDummyCommand() {
        try {
            readyForUserInput = false;
            this.output.write("\n");
            this.output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendNethackCommand(NethackDirectionObject d){
        try {
            readyForUserInput = false;
            this.output.write(d.getDirection()+"\n");
            this.output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendNethackCommand(NethackStringObject s){
        try {
            readyForUserInput = false;
            this.output.write(s.getText()+"\n");
            this.output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object processInputObject() throws IOException{
        Object result = null;
        while(result == null){
            result = readNethackLineObject();
        }
        return result;
    }

    private void processInputBoolean() throws  IOException{
        while(!readyForUserInput){
            readNethackLineObject();
        }
    }

    private Error checkInput(Object input){
        //checks whether the next input sent to nethack-lisp is valid
        //otherwise there might be a segmentation fault
        //consider moving this to a separate class
        if((input instanceof Command) && !lastCommandPrompt.getPrompt().equals("command")){
            return new Error(40+lastCommandPrompt.getCode(),"Wrong input type. Received: "+ Command.class.getName() + ", expected: "+ lastCommandPrompt.getPrompt());
        }
        if(input instanceof NethackChoice){
            if(!lastCommandPrompt.getPrompt().equals("number")) {
                return new Error(40 + lastCommandPrompt.getCode(), "Wrong input type. Received: " + NethackChoice.class.getName() + ", expected: " + lastCommandPrompt.getPrompt());
            }
            else{
                return checkNethackChoice((NethackChoice)input);
            }
        }
        if(input instanceof NethackMenuChoice){
            if(!lastCommandPrompt.getPrompt().equals("menu")) {
                return new Error(40 + lastCommandPrompt.getCode(), "Wrong input type. Received: " + NethackMenuChoice.class.getName() + ", expected: " + lastCommandPrompt.getPrompt());
            }
            else{
                return checkNethackMenuChoice((NethackMenuChoice)input);
            }
        }
        if((input instanceof NethackDirectionObject) && !lastCommandPrompt.getPrompt().equals("direction")){
            return new Error(40+lastCommandPrompt.getCode(),"Wrong input type. Received: "+ NethackDirectionObject.class.getName() + ", expected: "+ lastCommandPrompt.getPrompt());
        }
        if((input instanceof NethackStringObject) && !lastCommandPrompt.getPrompt().equals("string")){
            return new Error(40+lastCommandPrompt.getCode(),"Wrong input type. Received: "+ NethackDirectionObject.class.getName() + ", expected: "+ lastCommandPrompt.getPrompt());
        }

        return null;
    }

    private Error checkNethackChoice(NethackChoice input){
        for(Object o : objectContainer){
            if( o instanceof  NethackChoiceObject) {
                if (((NethackChoiceObject) o).getChoices().indexOf((char) input.getChoice()) == -1) {
                    return new Error(61, "Choice not present among the options. Received : " + input.getChoice() + ". Expected one of: " + ((NethackChoiceObject) o).getChoices());
                }
                //a choice object was found, returning from the method
                return null;
            }
        }
        //all objects were checked, no NethackChoiceObject was found
        return new Error(62, "Choice not present among the options. No NethackChoiceObject were found");
    }

    private Error checkNethackMenuChoice(NethackMenuChoice input){
        boolean optionFound = false;
        //states whether the option was found at all
        for(Object o : objectContainer){
            if(o instanceof NethackMenuObject){
                for(MenuOption mo : input.getChoices()){
                    optionFound = false;
                    for(NethackMenuItem nmi : ((NethackMenuObject) o).getItems()){
                        if(mo.getChoice() == nmi.getSymbol()){
                            if(mo.getCategory() != nmi.getGroupAcc()){
                                return new Error(83,"Wrong group category was provided. Received: " +mo.getCategory() +". Expected: " + nmi.getGroupAcc());
                            }
                            //the item was found and has the correct category
                            optionFound = true;
                            break;
                        }
                    }
                    if(!optionFound){
                        return new Error(84,"Choice not present among the options. NethackMenuObject was found, the selected option, was not");
                    }

                }
                //every option was found so return null
                return null;
            }
        }
        //all objects were checked, no NethackMenuObject was found
        return new Error(82, "Choice not present among the options. No NethackMenuObject were found");
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public PlayerCharacter getAvatar(){
        return avatar;
    }

    public NethackMap getTheMap(){
        return  theMap;
    }

}