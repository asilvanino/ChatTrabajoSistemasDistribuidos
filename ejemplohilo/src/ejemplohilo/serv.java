package ejemplohilo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class serv  extends Thread
{
  
	 
  public static void main(String args[]) throws IOException 
  { 
	 ServerSocket    server   = null;
	 
     server = new ServerSocket(4000); 
     System.out.println("Servidor iniciado");
     while(true) 
     {
    	 Socket cliente = new Socket();
    	 cliente=server.accept();// esperando al cliente	 
    	 hilo hilo = new hilo(cliente);
    	 hilo.start();
    	 System.out.println("hilo arrancado");
    	 
     }
    
      
     
  } 
 }


