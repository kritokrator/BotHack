package bothack.agents;

import bothack.agents.behaviours.AddressAcceptingBehaviour;
import bothack.agents.behaviours.JadeMessageAcceptingBehaviour;
import bothack.agents.behaviours.SocketMessageAcceptingBehaviour;
import bothack.agents.authentication.CookieManager;
import bothack.classes.QueueElement;
import jade.core.Agent;
import jade.core.NotFoundException;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by administrator on 12/22/14.
 */
public class LoginAgent extends Agent {
    private ThreadedBehaviourFactory tbf;
    private SocketMessageAcceptingBehaviour socketListener;
    private ServerSocket socket;
    private JadeMessageAcceptingBehaviour jadeListener;
    private AddressAcceptingBehaviour addressAcceptingBehaviour;
    private Integer portNumber;
    private CookieManager cookieManager;
    private volatile ArrayList<QueueElement> waitingQueue;
    private ReentrantLock lock;

    @Override
    public void setup(){
        try {
            System.setProperty("java.security.auth.login.config", "loginAgent.conf");
            lock = new ReentrantLock();
            tbf = new ThreadedBehaviourFactory();
            portNumber =Integer.parseInt((String)getArguments()[0]);
            socket = new ServerSocket(portNumber);
            cookieManager = new CookieManager();
            waitingQueue = new ArrayList<QueueElement>();
            registerService();
            //initiate both listening behaviours
            socketListener = new SocketMessageAcceptingBehaviour(socket);
            addBehaviour(tbf.wrap(socketListener));
            jadeListener = new JadeMessageAcceptingBehaviour();
            addBehaviour(jadeListener);
            addressAcceptingBehaviour = new AddressAcceptingBehaviour();
            addBehaviour(addressAcceptingBehaviour);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void takeDown(){
        //get the behaviour threads and kill them
        try {
            tbf.interrupt(socketListener);
            removeBehaviour(socketListener);
            jadeListener.done();
            removeBehaviour(jadeListener);
            addressAcceptingBehaviour.done();
            removeBehaviour(addressAcceptingBehaviour);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerService(){
        DFAgentDescription dfad = new DFAgentDescription();
        dfad.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("dungeon_login");
        sd.setName(getLocalName()+"-login");
        dfad.addServices(sd);
        try {
            DFService.register(this, dfad);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    public CookieManager getCookieManager() {
        return cookieManager;
    }

    public void setCookieManager(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }

    public boolean addToQueue(QueueElement element){
        lock.lock();
        try{
            waitingQueue.add(element);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            lock.unlock();
            return true;
        }
    }
    public boolean removeFromQueue(QueueElement element){
        lock.lock();
        try{
            waitingQueue.remove(element);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            lock.unlock();
            return true;
        }
    }

    public ArrayList<QueueElement> getWaitingQueue(){
        lock.lock();
        try{
            return  waitingQueue;
        }
        finally {
            lock.unlock();
        }

    }
}
