package bothack.classes;

import bothack.agents.NethackAgent;
import bothack.agents.gui.BotAgent;
import bothack.agents.gui.GUITest;
import bothack.agents.gui.MapAgent;
import jade.security.Name;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.File;
import java.util.HashMap;


/**
 * Created by administrator on 10/21/14.
 */
public class Main {
    public static void main(String[] args){
        try {
            MapAgent form = new MapAgent();

            HashMap<String,VisualInterfaceWrapper> players = form.getPlayersGui();

            HashMap<String,VisualInterfaceWrapper> players2 = new HashMap<String, VisualInterfaceWrapper>();
            VisualInterfaceWrapper wrapper = new VisualInterfaceWrapper();
            wrapper.setPlayerCharacter(new PlayerCharacter());
            players2.put("player2",wrapper);
            form.run();
            form.addNewPanel(players,players2);

        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
