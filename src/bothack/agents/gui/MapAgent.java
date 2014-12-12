package bothack.agents.gui;

import bothack.classes.VisualInterfaceWrapper;
import com.sun.org.glassfish.external.statistics.Stats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.*;

/**
 * Created by krito on 12/1/14.
 */
public class MapAgent extends JFrame implements Runnable{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JToolBar toolbar;
    private JButton exitButton;
    private JButton getPlayersButton;
    private JLabel oldPlayersCount;
    private JLabel newPlayersCount;
    private JButton getMapButton;
    private JTextArea output;
    private bothack.agents.MapAgent agent;
    private HashMap<String,VisualInterfaceWrapper> playersGui;
    private int counter = 0;

    public MapAgent(bothack.agents.MapAgent a){
        agent = a;
        playersGui = new HashMap<String, VisualInterfaceWrapper>();

        output = new JTextArea();
        tabbedPane1.add(output);

        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               agent.doDelete();
            }
        });
    }

    public MapAgent(){
        playersGui = new HashMap<String, VisualInterfaceWrapper>();

        output = new JTextArea();
        tabbedPane1.add(output);
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void run() {
        setContentPane(this.panel1);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1080,600);
        setPreferredSize(new Dimension(1080,600));
        panel1.setSize(1030,550);
        panel1.setPreferredSize(new Dimension(1030,550));
        tabbedPane1.setSize(new Dimension(1020, 540));
        tabbedPane1.setPreferredSize(new Dimension(1020,540));

        tabbedPane1.validate();
        tabbedPane1.repaint();
        this.validate();
        this.repaint();

        setVisible(true);

    }
    public void addNewPanel(PropertyChangeEvent evt){
        JPanel panel = new JPanel();
        panel.setName("new-player-panel");
        tabbedPane1.add(panel);
    }
    public void addNewPanel(HashMap<String,VisualInterfaceWrapper> oldPlayers,HashMap<String,VisualInterfaceWrapper> newPlayers){
        for(String owner : newPlayers.keySet()){
            if(!oldPlayers.containsKey(owner)){
                JPanel panel = new JPanel();
                panel.setSize(1015,515);
                panel.setPreferredSize(new Dimension(1015,515));
                panel.setLayout(new FlowLayout());
                panel.setName(owner);
                StatsPane stats = new StatsPane();
                stats.setSize(500, 500);
                stats.setPreferredSize(new Dimension(500, 500));
                stats.setName(owner);
                stats.updatePane(newPlayers.get(owner).getPlayerCharacter());
                panel.add(stats);
                Map map = new Map();
                JScrollPane scroll = new JScrollPane(map);

                scroll.setSize(500,500);
                scroll.setPreferredSize(new Dimension(500, 500));
                map.populateBasicTileSet();
                map.setName("map");
                //map.test();
                map.updateMap(newPlayers.get(owner).getMap());
                panel.add(scroll);
                panel.validate();
                panel.repaint();
                tabbedPane1.add(panel);
                validate();


            }
        }


    };
    public void updatePanels(HashMap<String,VisualInterfaceWrapper> newPlayers){
        for(Component tab : tabbedPane1.getComponents()){
           if(newPlayers.containsKey(tab.getName())){
                if(tab instanceof JPanel ){
                    for(Component stats : ((JPanel) tab).getComponents()){
                        System.out.println(stats.getName());
                        System.out.println("panel componentChild");
                        if(stats instanceof StatsPane){
                            ((StatsPane)stats).updatePane(newPlayers.get(tab.getName()).getPlayerCharacter());
                            System.out.println("MapAgent : Stats found");
                        }
                        else if(stats instanceof Map){
                            ((Map) stats).updateMap(newPlayers.get(tab.getName()).getMap());
                            System.out.println("MapAgent : map found");
                        }
                        else if(stats instanceof JScrollPane){
                            System.out.println("MapAgent : scroll found");
                            System.out.println(stats.getName());
                            ((Map)((JScrollPane) stats).getViewport().getView()).updateMap(newPlayers.get(tab.getName()).getMap());
                        }
                        else{
                            System.out.println("MapAgent_gui: somehow wrong class was used. Excpected StatsPane");
                            //TODO add a popup with an error message
                        }
                    }

                    tab.validate();
                    tab.repaint();
                    validate();

                }
               else{
                    System.out.println("MapAgent gui somehow wrong class was used. Expected JPanel.");
                    //TODO add a popup with an error message
                }
            }
        }

    };


    public void updatePanels(PropertyChangeEvent evt){
        output.append("Panels updated");
    }

    public void updatePlayers(HashMap<String,VisualInterfaceWrapper> newVal){
        HashMap<String,VisualInterfaceWrapper> oldVal = this.playersGui;
        oldPlayersCount.setText(new Integer(oldVal.size()).toString());
        newPlayersCount.setText(new Integer(newVal.size()).toString());
        //firePropertyChange("playersGui",oldVal,newVal);
        if(oldVal.size() != newVal.size()){
            addNewPanel(oldVal, newVal);
        }
        else {
            updatePanels(newVal);
        }
        this.playersGui = (HashMap<String,VisualInterfaceWrapper>)newVal.clone();

        }



    public void printOutput(String out){
        output.append(out+"\n");
    }

    public HashMap<String, VisualInterfaceWrapper> getPlayersGui() {
        return playersGui;
    }
}