package bothack.agents.gui;

import bothack.agents.behaviours.ObjectSendingBehaviour;
import bothack.agents.messages.QuitMessage;
import bothack.agents.messages.RequestMessage;
import bothack.agents.messages.SetupMessage;
import bothack.classes.Nethack;
import bothack.classes.NethackChoice;
import bothack.classes.NethackMenuChoice;
import bothack.interfaces.Command;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by krito on 11/27/14.
 */
public class BotAgent extends JFrame implements ActionListener, Runnable{

    private JPanel panel1;
    private JToolBar toolBar1;
    private JButton exitButton;
    private JButton sendButton;
    private JLabel agentNameLabel;
    private JRadioButton setupRadioButton;
    private JRadioButton quitRadioButton;
    private JRadioButton requestRadioButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextArea output;
    private JScrollPane scroll;
    private bothack.agents.BotAgent agent;
    String[] requestTypes = {"PlayerCharacter Update","MapUpdate","PerformAction","PerformChoice","PerformMenuChoice"};
    String[] choiceTypes = {"Yes","No"};

    public BotAgent(bothack.agents.BotAgent a){
        agent = a;
        setupRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quitRadioButton.isSelected() && setupRadioButton.isSelected()){
                    quitRadioButton.setSelected(false);
                }
                else if(requestRadioButton.isSelected() && setupRadioButton.isSelected()){
                    requestRadioButton.setSelected(false);
                    comboBox1.setEnabled(false);
                }
            }
        });

        requestRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quitRadioButton.isSelected() && requestRadioButton.isSelected()){
                    quitRadioButton.setSelected(false);
                }
                else if(requestRadioButton.isSelected() && setupRadioButton.isSelected()){
                    setupRadioButton.setSelected(false);
                }
                if(requestRadioButton.isSelected()){
                    comboBox1.setEnabled(true);
                }
            }
        });
        quitRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quitRadioButton.isSelected() && requestRadioButton.isSelected()){
                    requestRadioButton.setSelected(false);
                    comboBox1.setEnabled(false);
                }
                else if(quitRadioButton.isSelected() && setupRadioButton.isSelected()){
                    setupRadioButton.setSelected(false);
                }
            }
        });
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String item = (String)e.getItem();
                if(item.equals("PerformAction")){
                    //comboBox2 = new JComboBox(requestTypes);
                    comboBox2.removeAllItems();
                    for(Command c : Command.values()){
                        comboBox2.addItem(c);
                    }
                    comboBox2.setEnabled(true);
                }
                else if(item.equals("PerformChoice"))
                {
                    //comboBox2 = new JComboBox(choiceTypes);
                    comboBox2.removeAllItems();
                    comboBox2.addItem("Yes");
                    comboBox2.addItem("No");
                    comboBox2.setEnabled(true);
                }
                else{
                    comboBox2.setEnabled(false);
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agent.doDelete();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object content;
                if(setupRadioButton.isSelected()){
                    content = new SetupMessage();
                    ((SetupMessage) content).setRandom(true);
                    agent.addBehaviour(new ObjectSendingBehaviour(agent.getDungeon(),content, ACLMessage.REQUEST));
                }
                else if(quitRadioButton.isSelected()){
                    content = new QuitMessage(agent);
                    agent.addBehaviour(new ObjectSendingBehaviour(agent.getDungeon(),content, ACLMessage.REQUEST));
                }
                else if(requestRadioButton.isSelected()) {
                    content = new RequestMessage();
                    String tmp = (String)comboBox1.getSelectedItem();
                    if (tmp.equals("PerformAction")) {
                        ((RequestMessage) content).setAction((Command) comboBox2.getSelectedItem());
                        agent.addBehaviour(new ObjectSendingBehaviour(agent.getDungeon(), content, ACLMessage.REQUEST));
                    }
                    else if(tmp.contains("PerformChoice")){
                        RequestMessage choiceContent = new RequestMessage();
                        NethackChoice choice = new NethackChoice();
                        if(((String)comboBox2.getSelectedItem()).contains("Yes")){
                            choice.setChoice(121);
                        }
                        else{
                            choice.setChoice(110);
                        }

                        choiceContent.setChoice(choice);
                        agent.addBehaviour(new ObjectSendingBehaviour(agent.getDungeon(), choiceContent, ACLMessage.REQUEST));

                    }
                    else{
                        System.out.println("BotAgent : no other messages implemented yet");
                        printOutput("BotAgent : no other messages implemented yet");
                    }
                }
                else{
                    System.out.println("BotAgentgui : No message type selected");
                    output.setText("");
                    printOutput("BotAgentgui : No message type selected");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action performed");
    }

    @Override
    public void run() {
        setContentPane(this.panel1);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        comboBox1 = new JComboBox(requestTypes);
        comboBox2 = new JComboBox(Command.values());
        output = new JTextArea();
        DefaultCaret caret = (DefaultCaret) output.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public void printOutput(String out){
        output.append(out +"\n");
    }
}

