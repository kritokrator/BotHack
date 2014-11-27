package bothack.classes;

import bothack.agents.NethackAgent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by administrator on 10/21/14.
 */
public class Main {
    public static void main(String[] args){
        try {
            Nethack game = new Nethack();
            game.setup("118","104","109","99");
            NethackMap testMap;
            try {
                File xml = new File("/home/krito/test.xml");
                JAXBContext context = JAXBContext.newInstance(NethackMap.class);
                Marshaller jaxbMarshaller = context.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
                jaxbMarshaller.marshal(game.getTheMap(),xml);



                Unmarshaller jaxbUnmarhsaller = context.createUnmarshaller();
                testMap = (NethackMap) jaxbUnmarhsaller.unmarshal(xml);

                for(Tile t : testMap.getMap().values()){
                    System.out.println(t);
                }

                if(testMap.equals(game.getTheMap())){
                    System.out.print("TRUE");
                }
                else{
                    System.out.print("FALSE");
                }


            } catch (JAXBException e) {
                e.printStackTrace();
            }
            game.quitFast();
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
