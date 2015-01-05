package bothack.agents;

import bothack.agents.behaviours.GenericMessageAcceptingBehaviour;
import bothack.agents.behaviours.ObjectSendingBehaviour;
import bothack.agents.messages.QuitMessage;
import bothack.agents.messages.RequestMessage;
import bothack.agents.messages.SetupMessage;
import bothack.classes.NethackChoice;
import bothack.classes.NethackChoiceObject;
import bothack.classes.NethackMenuObject;
import bothack.interfaces.Command;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.introspection.ACLMessage;

/**
 * Created by administrator on 11/21/14.
 */
public class BotAgent extends Agent {

    private bothack.agents.gui.BotAgent gui;
    private AID dungeon;
    private AID guardian;
    private String cookie;
    private int port;
    private NethackMenuObject menu;
    private NethackChoiceObject choice;

    @Override
    public void setup(){
        try{
            choice = null;
            System.out.println("BothackAgent: prepping up the agent for interaction");
            guardian = findNethack();
            System.out.println("BothackAgent : found nethack guardian");
            gui = new bothack.agents.gui.BotAgent(this);
            gui.run();
            addBehaviour(new GenericMessageAcceptingBehaviour());


        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println("BothackAgent: Agent ready for the game");
        }
    }

    @Override
    public void takeDown(){
        gui.dispose();
        System.out.println("Agent : "+ getName()+" is shutting down");

    }

    public AID findNethack(){
        DFAgentDescription[] result = null;
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();

        sd.setType("dungeon_login");
        dfAgentDescription.addServices(sd);
        try{
            result = DFService.search(this,dfAgentDescription);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(result.length != 1){
            return null;
        }
        else {
            return result[0].getName();
        }
    }

    public bothack.agents.gui.BotAgent getGui() {
        return gui;
    }

    public void setGui(bothack.agents.gui.BotAgent gui) {
        this.gui = gui;
    }

    public AID getDungeon() {
        return dungeon;
    }

    public void setDungeon(AID dungeon) {
        this.dungeon = dungeon;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public NethackMenuObject getMenu() {
        return menu;
    }

    public void setMenu(NethackMenuObject menu) {
        this.menu = menu;
    }

    public NethackChoiceObject getChoice() {
        return choice;
    }

    public void setChoice(NethackChoiceObject choice) {
        this.choice = choice;
    }

    public AID getGuardian() {
        return guardian;
    }

    public void setGuardian(AID guardian) {
        this.guardian = guardian;
    }
}
