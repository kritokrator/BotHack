package bothack.agents.behaviours;

import bothack.agents.NethackAgent;
import bothack.classes.*;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by administrator on 12/8/14.
 */
public class visualizationUpdateBehaviour extends OneShotBehaviour {
    private VisualInterfaceWrapper visualInterfaceWrapper;

    public visualizationUpdateBehaviour(String dungeonOwner,PlayerCharacter pc, NethackMap nm){
        visualInterfaceWrapper = new VisualInterfaceWrapper();
        visualInterfaceWrapper.setOwner(dungeonOwner);
        visualInterfaceWrapper.setMap(nm);
        visualInterfaceWrapper.setPlayerCharacter(pc);
    }
    @Override
    public void action() {
        if(myAgent instanceof NethackAgent){
            try{
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                for(AID address : ((NethackAgent) myAgent).getVisualizers()){
                    msg.addReceiver(address);
                }
                JAXBContext context = JAXBContext.newInstance(VisualInterfaceWrapper.class);
                Marshaller marshaller = context.createMarshaller();
                StringWriter writer = new StringWriter();
                marshaller.marshal(visualInterfaceWrapper,writer);

                msg.setContent(writer.toString());
                System.out.println(myAgent.getName() + ": sending visuaInterface update recepients");
                myAgent.send(msg);

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }
}
