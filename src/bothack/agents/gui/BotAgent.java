package bothack.agents.gui;

import bothack.agents.behaviours.ObjectSendingBehaviour;
import bothack.agents.messages.*;
import bothack.classes.*;
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
    private JRadioButton setupRadioButton;
    private JRadioButton quitRadioButton;
    private JRadioButton interactRadioButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private String cookie;
    private JTextArea output;
    private JScrollPane scroll;
    private JButton loginButton;
    private JTextField login;
    private JTextArea password;
    private JTextField textField;
    private JButton addOptionButton;
    private bothack.agents.BotAgent agent;
    private NethackMenuChoice menuChoice= null;
    String[] requestTypes = {"PlayerCharacter Update","MapUpdate","Action","Choice","Menu","Direction","Text"};
    String[] choiceTypes = {"Yes","No"};

    public BotAgent(bothack.agents.BotAgent a){
        agent = a;
        setupRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quitRadioButton.isSelected() && setupRadioButton.isSelected()){
                    quitRadioButton.setSelected(false);
                }
                else if(interactRadioButton.isSelected() && setupRadioButton.isSelected()){
                    interactRadioButton.setSelected(false);
                    comboBox1.setEnabled(false);
                }
            }
        });

        interactRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quitRadioButton.isSelected() && interactRadioButton.isSelected()){
                    quitRadioButton.setSelected(false);
                }
                else if(interactRadioButton.isSelected() && setupRadioButton.isSelected()){
                    setupRadioButton.setSelected(false);
                }
                if(interactRadioButton.isSelected()){
                    comboBox1.setEnabled(true);
                }
            }
        });
        quitRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quitRadioButton.isSelected() && interactRadioButton.isSelected()){
                    interactRadioButton.setSelected(false);
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
                if(item.equals("Action")){
                    //comboBox2 = new JComboBox(requestTypes);
                    comboBox2.removeAllItems();
                    for(Command c : Command.values()){
                        comboBox2.addItem(c);
                    }
                    comboBox2.setEnabled(true);
                }
                else if(item.equals("Choice"))
                {
                    //comboBox2 = new JComboBox(choiceTypes);
                    comboBox2.removeAllItems();
                    try{
                        String choices = agent.getChoice().getChoices();
                        for(int i =0; i <choices.length();i++){
                            comboBox2.addItem(choices.charAt(i));
                        }
                        comboBox2.setEnabled(true);
                    }
                    catch (NullPointerException e1){
                        JOptionPane.showMessageDialog(panel1,e1.getMessage());
                    }
                }
                else if(item.equals("Direction")){
                    comboBox2.removeAllItems();
                    comboBox2.addItem("n");
                    comboBox2.addItem("ne");
                    comboBox2.addItem("e");
                    comboBox2.addItem("se");
                    comboBox2.addItem("s");
                    comboBox2.addItem("sw");
                    comboBox2.addItem("w");
                    comboBox2.addItem("nw");
                    comboBox2.setEnabled(true);
                }
                else if(item.equals("Menu")){
                    comboBox2.removeAllItems();
                    try {
                        NethackMenuObject menu = agent.getMenu();
                        for(NethackMenuItem menuItem : menu.getItems()){
                            comboBox2.addItem(menuItem.getDescription());
                        }
                        comboBox2.setEnabled(true);
                    } catch (NullPointerException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(panel1,e1.getMessage());
                    }
                }
                else if(item.equals("Text")){
                    textField.setEnabled(true);
                    comboBox2.setEnabled(false);
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
                InteractMessage interactMessage = new InteractMessage();
                interactMessage.setCookie(agent.getCookie());
                if(setupRadioButton.isSelected()){
                    SetupMessage content = new SetupMessage();
                    content.setRandom(true);
                    content.setCookie(agent.getCookie());
                    agent.addBehaviour(new ObjectSendingBehaviour(null,agent.getDungeon(),content, ACLMessage.REQUEST));
                }
                else if(quitRadioButton.isSelected()){
                    QuitMessage content = new QuitMessage(agent);
                    content.setCookie(agent.getCookie());
                    agent.addBehaviour(new ObjectSendingBehaviour(null,agent.getDungeon(),content, ACLMessage.REQUEST));
                }
                else if(interactRadioButton.isSelected()) {
                    String tmp = (String)comboBox1.getSelectedItem();
                    if (tmp.equals("Action")) {
                        interactMessage.setAction((Command) comboBox2.getSelectedItem());
                        agent.addBehaviour(new ObjectSendingBehaviour(null,agent.getDungeon(), interactMessage, ACLMessage.REQUEST));
                    }
                    else if(tmp.contains("Choice")){
                        NethackChoice choice = new NethackChoice();
                        choice.setChoice((Character)comboBox2.getSelectedItem());
                        interactMessage.setChoice(choice);
                        agent.addBehaviour(new ObjectSendingBehaviour(null,agent.getDungeon(), interactMessage, ACLMessage.REQUEST));

                    }
                    else if(tmp.contains("Menu")){
                        if(menuChoice == null){
                            JOptionPane.showMessageDialog(panel1,"No options chosen");
                            return;
                        }
                        interactMessage.setMenuOption(menuChoice);
                        agent.addBehaviour(new ObjectSendingBehaviour(null,agent.getDungeon(), interactMessage, ACLMessage.REQUEST));
                        menuChoice = null;
                    }
                    else if(tmp.contains(("Direction"))){
                        NethackDirectionObject nethackDirectionObject= new NethackDirectionObject();
                        nethackDirectionObject.setDirection((String)comboBox2.getSelectedItem());
                        interactMessage.setDirection(nethackDirectionObject);
                        agent.addBehaviour(new ObjectSendingBehaviour(null,agent.getDungeon(), interactMessage, ACLMessage.REQUEST));

                    }
                    else if(tmp.contains("Text")){
                        NethackStringObject text = new NethackStringObject();
                        text.setText((String)comboBox2.getSelectedItem());
                        interactMessage.setText(text);
                        agent.addBehaviour(new ObjectSendingBehaviour(null,agent.getDungeon(), interactMessage, ACLMessage.REQUEST));
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
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(login.getText().isEmpty()){
                    JOptionPane.showMessageDialog(panel1,"login needs to be not empty");
                }
                else if(password.getText().isEmpty()){
                    JOptionPane.showMessageDialog(panel1,"password needs to be not empty");
                }
                else{
                    LoginMessage loginMessage = new LoginMessage();
                    loginMessage.setName(login.getText());
                    loginMessage.setPassword(password.getText());
                    agent.addBehaviour(new ObjectSendingBehaviour(null,agent.getGuardian(),loginMessage,ACLMessage.REQUEST));
                }
            }
        });
        addOptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem().equals("Menu")){
                    if(menuChoice == null){
                        menuChoice = new NethackMenuChoice();
                    }
                    MenuOption option = new MenuOption();
                    String description = (String)comboBox2.getSelectedItem();
                    for(NethackMenuItem item : agent.getMenu().getItems()){
                    if(item.getDescription().equals(description)){
                        option.setCategory(item.getGroupAcc());
                        option.setChoice(item.getSymbol());
                        }
                    }
                    menuChoice.getChoices().add(option);
                }
                else{
                    JOptionPane.showMessageDialog(panel1,"Select a menu option");
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

