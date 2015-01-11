package bothack.agents;

import bothack.agents.gui.SocketAgent;
import bothack.classes.NethackChoiceObject;
import bothack.classes.NethackMenuChoice;
import bothack.classes.NethackMenuObject;
import jade.core.Agent;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by administrator on 12/22/14.
 */
public class SocketTest extends Agent {
    private int nethackPort;
    private String cookie;
    private SocketAgent gui;
    private String loginHost;
    private int loginPort;
    private NethackMenuObject menu;
    private NethackChoiceObject choice;

    public NethackMenuObject getMenu() {
        return menu;
    }

    public void setMenu(NethackMenuObject menu) {
        this.menu = menu;
    }

    public NethackChoiceObject getChoice() {
        return choice;
    }

    public void setChoice(NethackChoiceObject choice) {
        this.choice = choice;
    }

    public int getNethackPort() {
        return nethackPort;
    }

    public void setNethackPort(int nethackPort) {
        this.nethackPort = nethackPort;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public SocketAgent getGui() {
        return gui;
    }

    public void setGui(SocketAgent gui) {
        this.gui = gui;
    }

    public String getLoginHost() {
        return loginHost;
    }

    public void setLoginHost(String loginHost) {
        this.loginHost = loginHost;
    }

    public int getLoginPort() {
        return loginPort;
    }

    public void setLoginPort(int loginPort) {
        this.loginPort = loginPort;
    }

    @Override
    public void setup(){
        Object[] args = getArguments();
        loginHost = (String)args[0];
        loginPort = Integer.parseInt((String)args[1]);
        gui = new SocketAgent(this);
        gui.run();

    }

    @Override
    public void takeDown(){
            gui.dispose();
    }

}
