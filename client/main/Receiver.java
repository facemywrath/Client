package client.main;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Receiver extends Thread {
	
    protected Socket socket;
    private Client client;
    
    public Receiver(Client client, Socket clientSocket) {
        this.socket = clientSocket;
        this.client = client;
    }

    public void run() {
        InputStream inp = null;
        DataInputStream brinp = null;
        DataOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new DataInputStream(new BufferedInputStream(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String line;
        while (true) {
            try {
                line = brinp.readUTF();
                if(line != null)
                	System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}