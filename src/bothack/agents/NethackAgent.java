package bothack.agents;

import bothack.agents.behaviours.MessageAcceptingBehaviour;
import bothack.agents.behaviours.NethackRegisteringBehaviour;
import bothack.classes.Nethack;
import jade.core.Agent;
/**
 * Created by administrator on 11/21/14.
 */
public class NethackAgent extends Agent{
    private Nethack game;

    public Nethack getGame() {
        return game;
    }

    public void setGame(Nethack game) {
        this.game = game;
    }

    @Override
    public void setup(){
        try{
            System.out.println("NethackAgent: Prepping up the environment");
            game = new Nethack();
            addBehaviour(new NethackRegisteringBehaviour());
            addBehaviour(new MessageAcceptingBehaviour());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println("NethackAgent: ready for messages");
        }

    }
}
