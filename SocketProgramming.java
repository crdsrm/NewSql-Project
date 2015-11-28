/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprogramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class SocketProgramming {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String serverName = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            Socket client = new Socket(serverName, port);

            //for(int i=0;i<5;i++){ 
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Connecting to " + serverName + " on port " + port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            for(int i=1;i<=1000000000;i++) {
            JSONObject obj = new JSONObject();

            try {
                obj.put("id",new Integer(i));
                obj.put("name", "client2");
                obj.put("num", new Integer(100));
                obj.put("balance", new Double(1000.21));
                obj.put("is_vip", new Boolean(true));

            } catch (JSONException ex) {
                Logger.getLogger(SocketProgramming.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.print(obj)
            out.writeUTF(obj.toString());
            }
            out.writeUTF("Exit");
            //System.out.println("Server says " + in.readUTF());
            //Thread.sleep(3000);
            //}
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

}
