package sockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jcar3
 */
class ClientHandler extends Thread {

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        String opc;
        String Respuesta;
        while (true) {
            try {

                // Ask user what he wants 
                dos.writeUTF("SD > ");

                // receive the answer from client 
                opc = dis.readUTF();
                String[] Array = opc.split(" ");

                if (Array[0].equals("Exit")) {
                    System.out.println("Cliente " + this.s + " cerrando...");
                    System.out.println("Cerrando conexion.");
                    this.s.close();
                    System.out.println("Chao conexión");
                    break;
                }

                // creating Date object 
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                // write on output stream based on the 
                // answer from the client 

                switch (Array[0]) {
                    case "suma":
                        int result = 0; 
                        
                        System.out.println("datos ingresados");

                        for (int i = 1; i < Array.length; i++) {
                            result = result + Integer.parseInt(Array[i]);
                            System.out.print(Array[i]);
                        }
                        System.out.println("");
                        Respuesta = Integer.toString(result);
                        dos.writeUTF(Respuesta);
                        break;

                    case "resta":
                        int resultr = Integer.parseInt(Array[1]); 
                        
                        System.out.println("datos ingresados");

                        for (int i = 1; i < Array.length; i++) {
                            resultr = resultr - Integer.parseInt(Array[i]);
                            System.out.print(Array[i]);
                        }
                        System.out.println("");
                        Respuesta = Integer.toString(resultr);
                        dos.writeUTF(Respuesta);
                        break;

                    case "multiplicar":
                        int resultm = 1; 
                        
                        System.out.println("datos ingresados");

                        for (int i = 1; i < Array.length; i++) {
                            resultm = resultm * Integer.parseInt(Array[i]);
                            System.out.print(Array[i]);
                        }
                        System.out.println("");
                        Respuesta = Integer.toString(resultm);
                        dos.writeUTF(Respuesta);
                        break;

                    case "dividir":
                        int resultd = Integer.parseInt(Array[1]); 
                        
                        System.out.println("datos ingresados");

                        for (int i = 1; i < Array.length; i++) {
                            resultd = resultd / Integer.parseInt(Array[i]);
                            System.out.print(Array[i]);
                        }
                        System.out.println("");
                        Respuesta = Integer.toString(resultd);
                        dos.writeUTF(Respuesta);
                        break;

                    case "dir":
                        dos.writeUTF("suma num1 num2 numn \n\nresta num1 num2 numn \n\nmultiplicarn um1 num2 numn \n\ndividir num1 num2");
                        break;

                    default:
                        dos.writeUTF("No se reconoce como un comando, dir para ver la lista de comandos.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            // closing resources 
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
