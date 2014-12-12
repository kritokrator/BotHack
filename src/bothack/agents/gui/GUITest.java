package bothack.agents.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by administrator on 12/10/14.
 */
public class GUITest extends JFrame implements Runnable{


    private JPanel main;
    private JPanel main1;
    private JPanel panelA;
    private JScrollPane panelB;

    private Map map1;
    private JScrollPane scroll;
    private  StatsPane stats;



    public GUITest(){
        main1 = new JPanel();
        stats = new StatsPane();
        map1 = new Map();
        main1.setSize(615,550);
        main1.setBackground(Color.BLACK);
        main1.setLayout(new FlowLayout());
        panelA = new JPanel();
        panelB = new JScrollPane(map1);
        panelA.setSize(200,500);
        panelA.setPreferredSize(new Dimension(300, 500));
        panelB.setSize(300, 500);
        panelB.setPreferredSize(new Dimension(300, 500));
        panelA.setBackground(Color.BLUE);
        panelB.setBackground(Color.DARK_GRAY);
        main1.add(panelA);
        panelA.add(stats);
        main1.add(panelB);
        main1.setAutoscrolls(true);
        panelB.setAutoscrolls(true);



      /*  super();
        main.setLayout(new GridLayout());
        map = new Map();
        map.setBackground(new Color(46, 40, 181));
        main.setBackground(new Color(0xC74556));
        scroll = new JScrollPane(map);
        scroll.setSize(100,50);
        //main.add(map);
        scroll.setAutoscrolls(true);
        main.add(scroll);*/
        }
    public void test(){
   /*     map.populateBasicTileSet();
        //this.pack();
        main.setSize(100,100);
        map.setSize(180, 160);
        map.test();
        map.validate();
        map.repaint();*/

        map1.populateBasicTileSet();
        map1.test();

    }

    @Override
    public void run() {
        this.setContentPane(this.main1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(640,560);
        this.validate();
        this.repaint();
        this.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        map1 = new Map();
    }
}
