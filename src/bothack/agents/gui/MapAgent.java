package bothack.agents.gui;

import bothack.agents.behaviours.ObjectSendingBehaviour;
import bothack.agents.messages.RequestMessage;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by krito on 12/1/14.
 */
public class MapAgent extends JFrame implements Runnable{
    private JPanel panel1;
    private JButton nethackButton;
    private JButton getMapButton;
    private JTextArea output;
    private bothack.agents.MapAgent agent;

    public MapAgent(bothack.agents.MapAgent a){
        agent = a;
        getMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RequestMessage content = new RequestMessage();
                content.setMapUpdate(true);
                agent.addBehaviour(new ObjectSendingBehaviour(agent.getDungeon(),content, ACLMessage.REQUEST));
            }
        });
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void run() {
        setContentPane(this.panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }



    public void printOutput(String out){
        output.append(out+"\n");
    }
}
