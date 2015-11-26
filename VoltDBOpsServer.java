import java.net.*;
import java.io.*;

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
         try
         {
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
            DataInputStream in =
                  new DataInputStream(server.getInputStream());

	    while(!(data=in.readUTF).equals("Exit")){
		            System.out.println(data);		
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
