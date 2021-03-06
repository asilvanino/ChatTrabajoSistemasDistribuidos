package ejemplohilo;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class hilo  extends Thread
{
	 private DataInputStream in       =  null;
	    private PrintWriter out     = null; 
	    private Socket          socket   = null; 
	    private Scanner enviar = new Scanner(System.in);
 public hilo(Socket s)
 {
	 socket =s;
	try {
		in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		 out    = new PrintWriter(socket.getOutputStream(),true); 
		    
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 
 public void run()
 {
	 String line1,line= "";
	 
	 
	 while (!line.equals("chao")) 
     { 
		 System.out.println("comunico con :" +socket.toString());
		 
         try
        { 
         	//recibe
        	 line=in.readLine();
        	 
        	 //line = in.readUTF(); 
             System.out.println("\n" +"["  + socket.getInetAddress().getHostName() + "]"  +  line + "");
             
        	 //if(line!=null)
        	 //{
        		 System.out.print("\"[Usted] => \"");
             	line1=enviar.nextLine();
                //line1 = input.readLine(); 
                 out.println(line1); 
        	// }
         } 
         catch(IOException e) 
         { 
             e.printStackTrace();
         } 
     } 
     System.out.println("Terminada Conexion"); 
 
     // close connection 
      try
      {
    	  out.close(); 
    	  in.close();
    	  socket.close(); 
    	        
      } catch(IOException e)
      {
    	  e.printStackTrace();
      }
  
      
     
 }
 

}
