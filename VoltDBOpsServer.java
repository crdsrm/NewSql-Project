import java.net.*;
import java.io.*;
import org.json.JSONObject;
import org.json.JSONException;

public class VoltDBOpsServer extends Thread
{
   private ServerSocket serverSocket;

   public VoltDBOpsServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);

   }

   public void run()
   {
      while(true)
      {
        String data = "";
         try
         {
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
            DataInputStream in =
                  new DataInputStream(server.getInputStream());

	    while(!(data=in.readUTF()).equals("Exit")){
                try{
                JSONObject js = new JSONObject(data);

		            System.out.println(js.get("id"));
                System.out.println(js.get("name"));
                System.out.println(js.get("balance"));
                System.out.println(js.get("num"));
                System.out.println(js.get("is_vip"));

              }catch(JSONException e)
              {
                e.printStackTrace();
              }

			    System.out.println("\n");
		}

            //server.close();
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   public static void main(String [] args)
   {
      int port = Integer.parseInt(args[0]);
      try
      {
         Thread t = new VoltDBOpsServer(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}
