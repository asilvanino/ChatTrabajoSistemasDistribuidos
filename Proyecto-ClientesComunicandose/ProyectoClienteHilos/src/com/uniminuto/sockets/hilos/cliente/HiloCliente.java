package com.uniminuto.sockets.hilos.cliente;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class HiloCliente extends Thread{
	private Socket socketCliente;
	private DataInputStream entrada;
	private SocketCliente cliente;
	private ObjectInputStream entradaObjeto;
	
	public HiloCliente(Socket sc, SocketCliente c) {
		this.socketCliente = sc;
		this.cliente = c;
	}
	
	public void run() {
		while(true) {
			try {
				Scanner sc = new Scanner(System.in);
				entradaObjeto = new ObjectInputStream(socketCliente.getInputStream());	
				int op = -1;
	            entrada = new DataInputStream(socketCliente.getInputStream());
	            System.out.println("**.:Que desea hacer? \n 1. Mirar Conectados \n 2. Esperar con otro cliente \n 3.Conectarse con un cliente en espera \n 4. Desconectarse:.**");
	            op = sc.nextInt();
	            switch (op) {
				case 1:            
		            cliente.actualizarLista((DefaultListModel<?>) entradaObjeto.readObject());
					break;
				case 2:
					cliente.salidaEvento();
					break;
				case 3:
					cliente.conectarseServerCliente();
					break;
				case 4:
					System.exit(0);
					break;

				}
            } catch (IOException | ClassNotFoundException ex) {
	            System.out.println(ex);
            }
		}
	}
}
