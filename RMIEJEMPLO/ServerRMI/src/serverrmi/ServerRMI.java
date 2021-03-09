/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrmi;

import java.rmi.AlreadyBoundException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi.RMI;

public class ServerRMI extends UnicastRemoteObject implements RMI {

    public ServerRMI() throws RemoteException {
        super();
    }

    @Override
    public int sumar(int a, int b) throws RemoteException {
        return a + b;
    }

    public static void main(String[] Args) throws AlreadyBoundException {
        try {
            Registry registro = LocateRegistry.createRegistry(7777);
            registro.bind("RemotoRMI", new ServerRMI());
            System.out.println("Servidor activado");
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

}
