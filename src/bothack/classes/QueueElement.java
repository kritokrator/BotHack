package bothack.classes;

import jade.core.AID;

import java.net.Socket;

/**
 * Created by administrator on 12/28/14.
 */
public class QueueElement {
    private String operation; //the type of message this recipient is waiting
    private String dungeon; // form which instance of nethack this is supposed to be
    private AID recipientAgent; // where to send the response
    private Socket recipientSocket; //where to send the response

    public QueueElement(String operation, String dungeon, AID recipientAgent, Socket recipientSocket) {
        this.operation = operation;
        this.dungeon = dungeon;
        this.recipientAgent = recipientAgent;
        this.recipientSocket = recipientSocket;
    }

    public QueueElement() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDungeon() {
        return dungeon;
    }

    public void setDungeon(String dungeon) {
        this.dungeon = dungeon;
    }

    public AID getRecipientAgent() {
        return recipientAgent;
    }

    public void setRecipientAgent(AID recipientAgent) {
        this.recipientAgent = recipientAgent;
    }

    public Socket getRecipientSocket() {
        return recipientSocket;
    }

    public void setRecipientSocket(Socket recipientSocket) {
        this.recipientSocket = recipientSocket;
    }
}
