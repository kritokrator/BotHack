package bothack.agents.ontologies;

import bothack.interfaces.Command;
import jade.content.onto.*;
import jade.content.schema.*;

/**
 * Created by krito on 11/26/14.
 */
public class BothackOntology extends BasicOntology {
    public static final String ONTOLOGY_NAME = "Bothack-ontology";

    public static final String ACTION = "ACTION";

    public static final String PERFORM = "PERFORM";
    public static final String PERFORM_ACTION = "PERFORM_ACTION";

    private static Ontology theInstance = new BothackOntology();

    public static Ontology getInstance(){
        return theInstance;
    }
    private BothackOntology() {
        super(ONTOLOGY_NAME,BasicOntology.getInstance());
        try{
            add(new ConceptSchema(ACTION, Command.class));

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
