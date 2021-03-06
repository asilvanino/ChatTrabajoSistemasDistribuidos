package ejemplohilo;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client 
{
	  
  public static void main(String args[]) throws  IOException 
  { 
	  Socket socket = null; 
	   Scanner enviar = new Scanner(System.in);
	  DataInputStream in       =  null;
	  PrintWriter out     = null; 
	 String host = "localhost";
	 int puerto = 4000;
	 socket = new Socket(host, puerto); 
	 in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	 out    = new PrintWriter(socket.getOutputStream(),true); 
	
	 String line = ""; 
     String line1 = "";   
     // keep reading until "Over" is input 
     System.out.println("conectado con:" +socket.toString());
     
     
	 
	 
     
     while (!line.equals("chao")) 
     { 	
    	 System.out.println("introdusca la cadena");
   	  line=enviar.nextLine();
    	 out.println(line);
    	 
    	 line= in.readLine();
    	 System.out.print("[" + socket.getInetAddress().getHostName() + "]" + line +"\n" );
    	 System.out.println("Introdusca la cadena");
    	 line=enviar.nextLine();
     } 
     
     
     
         out.close(); 
        
         in.close(); 
         System.out.println("Closing connection"); 
         
         socket.close();
     } 
     
 }

