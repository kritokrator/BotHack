package bothack.agents.gui;

import bothack.agents.SocketTest;
import bothack.agents.behaviours.ObjectSendingBehaviour;
import bothack.agents.behaviours.SocketTransferBehaviour;
import bothack.agents.messages.InteractMessage;
import bothack.agents.messages.LoginMessage;
import bothack.agents.messages.QuitMessage;
import bothack.agents.messages.SetupMessage;
import bothack.classes.*;
import bothack.interfaces.Command;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.*;

/**
 * Created by administrator on 12/29/14.
 */
public class SocketAgent extends JFrame implements ActionListener, Runnable{
    private bothack.agents.SocketTest agent;
    private JPanel mainPanel;
    private JButton exitButton;
    private JButton sendButton;
    private JButton addOptionButton;
    private JButton loginButton;
    private JTextField login;
    private JTextField password;
    private JRadioButton setupRadioButton;
    private JRadioButton quitRadioButton;
    private JRadioButton interactRadioButton;
    private JTextArea output;
    private JComboBox comboBox2;
    private JComboBox comboBox1;
    private JTextField textMessage;
    private JScrollPane scroll;
    private NethackMenuChoice menuChoice;
    String[] requestTypes = {"PlayerCharacter Update","MapUpdate","Action","Choice","Menu","Direction","Text"};

    public SocketAgent(SocketTest a) {
        this.agent = a;
        menuChoice = null;
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
                        JOptionPane.showMessageDialog(mainPanel,e1.getMessage());
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
                        JOptionPane.showMessageDialog(mainPanel,e1.getMessage());
                    }
                }
                else if(item.equals("Text")){
                    textMessage.setEnabled(true);
                    comboBox2.setEnabled(false);
                }
                else{
                    comboBox2.setEnabled(false);
                }
            }
        });
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
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agent.doDelete();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(login.getText().isEmpty()){
                    JOptionPane.showMessageDialog(mainPanel,"login needs to be not empty");
                }
                else if(password.getText().isEmpty()){
                    JOptionPane.showMessageDialog(mainPanel,"password needs to be not empty");
                }
                else{
                    LoginMessage loginMessage = new LoginMessage();
                    loginMessage.setName(login.getText());
                    loginMessage.setPassword(password.getText());
                    agent.addBehaviour(new SocketTransferBehaviour(loginMessage,agent.getLoginPort()));
                    //agent.addBehaviour(new ObjectSendingBehaviour(null,agent.getGuardian(),loginMessage, ACLMessage.REQUEST));
                }
            }
        });
        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == KeyEvent.VK_PAGE_UP){
                    JOptionPane.showMessageDialog(mainPanel, "page up");
                }
            }
        });
        login.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN)
                    JOptionPane.showMessageDialog(mainPanel, "released");
            }
        });
        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_PAGE_UP){
                    JOptionPane.showMessageDialog(mainPanel, "page up");
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
                    JOptionPane.showMessageDialog(mainPanel,"Select a menu option");
                }
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
                    agent.addBehaviour(new SocketTransferBehaviour(content,agent.getNethackPort()));
                }
                else if(quitRadioButton.isSelected()){
                    QuitMessage content = new QuitMessage(agent);
                    content.setCookie(agent.getCookie());
                    agent.addBehaviour(new SocketTransferBehaviour(content,agent.getNethackPort()));
                }
                else if(interactRadioButton.isSelected()) {
                    String tmp = (String)comboBox1.getSelectedItem();
                    if (tmp.equals("Action")) {
                        interactMessage.setAction((Command) comboBox2.getSelectedItem());
                        agent.addBehaviour(new SocketTransferBehaviour(interactMessage,agent.getNethackPort()));
                    }
                    else if(tmp.contains("Choice")){
                        NethackChoice choice = new NethackChoice();
                        choice.setChoice((Character)comboBox2.getSelectedItem());
                        interactMessage.setChoice(choice);
                        agent.addBehaviour(new SocketTransferBehaviour(interactMessage,agent.getNethackPort()));

                    }
                    else if(tmp.contains("Menu")){
                        if(menuChoice == null){
                            JOptionPane.showMessageDialog(mainPanel,"No options chosen");
                            return;
                        }
                        interactMessage.setMenuOption(menuChoice);
                        agent.addBehaviour(new SocketTransferBehaviour(interactMessage,agent.getNethackPort()));
                        menuChoice = null;
                    }
                    else if(tmp.contains(("Direction"))){
                        NethackDirectionObject nethackDirectionObject= new NethackDirectionObject();
                        nethackDirectionObject.setDirection((String)comboBox2.getSelectedItem());
                        interactMessage.setDirection(nethackDirectionObject);
                        agent.addBehaviour(new SocketTransferBehaviour(interactMessage,agent.getNethackPort()));

                    }
                    else if(tmp.contains("Text")){
                        NethackStringObject text = new NethackStringObject();
                        text.setText(textMessage.getText());
                        interactMessage.setText(text);
                        agent.addBehaviour(new SocketTransferBehaviour(interactMessage,agent.getNethackPort()));
                        textMessage.setText("");
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

    }

    @Override
    public void run() {
        setContentPane(this.mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        mainPanel.grabFocus();
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
