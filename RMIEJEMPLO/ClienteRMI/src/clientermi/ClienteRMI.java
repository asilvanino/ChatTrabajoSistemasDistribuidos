/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientermi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.RMI;

/**
 *
 * @author Andres Felipe Silva
 */
public class ClienteRMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ClienteRMI client = new ClienteRMI();
        client.ConnectToServer();
        
    }
    
    public void ConnectToServer() {
        try {
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 7777);
            RMI interfaz = (RMI)registro.lookup("RemotoRMI");
            int suma; 
            suma = interfaz.sumar(1,2);
            System.out.println("Resultado = " + suma);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
