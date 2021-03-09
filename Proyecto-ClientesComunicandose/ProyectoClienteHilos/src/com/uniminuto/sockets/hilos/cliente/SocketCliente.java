package com.uniminuto.sockets.hilos.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.DefaultListModel;

public class SocketCliente {
	
	private ServerSocket clienteServer;
	private Socket cliente;
	private final int puertoC=1526;
	private final int puertoS=9999;
	private String host = "localhost";
	private DataInputStream entrada;
	private DataOutputStream salida;
	private String nombre;
	private Vector<String> mensajes = new Vector<String>();
	
	public void initSocketCliente() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Escriba su nombre: ");
			nombre = sc.nextLine();
			cliente = new Socket (host, puertoC);
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			salida.writeUTF(nombre);
			HiloCliente hilo = new HiloCliente(cliente, this);
			hilo.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void salidaEvento() {
		try {
			clienteServer = new ServerSocket(puertoS);
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());
			Scanner sc = new Scanner(System.in);
			String nce ="";
			String mensaje="";
			System.out.println("¿A quien esta esperando?");
			nce = sc.nextLine();
			System.out.println("** Esperando a que se conecte el cliente "+nce+"... **");
			cliente = clienteServer.accept();
			System.out.println("**.:Un cliente se a conectado:.**");
			mensaje = entrada.readUTF();
			if(nce.equals(mensaje)) {
				while(!mensaje.equals("x")) {
					salida.writeUTF(mensaje);
					mensaje = entrada.readUTF();
					System.out.println(""+mensaje+"");
					System.out.println("Envie un mensaje: *Escriba x para salir del chat*");
					mensaje = sc.nextLine();
					salida.writeUTF(mensaje);
				}
				clienteServer.close();
			}
			else {
				clienteServer.close();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void actualizarLista(DefaultListModel<?> modelo) {
		System.out.println(modelo);
	}
	
	public void conectarseServerCliente() {
		try {
			cliente = new Socket(host, puertoS);
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());
			Scanner sc = new Scanner(System.in);
			String mensaje="";
			System.out.println("Escriba su nombre: ");
			mensaje = sc.nextLine();
			salida.writeUTF(mensaje);
			mensaje = entrada.readUTF();
			System.out.println(""+mensaje+"");
			while(!mensaje.equals("x")) {
				System.out.println("Escriba un mensaje: *Escriba x para salir del chat*");
				mensaje = sc.nextLine();
				salida.writeUTF(mensaje);
				entrada.readUTF();
				System.out.println(""+mensaje+"");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		SocketCliente sc = new SocketCliente();
		sc.initSocketCliente();
	}
}
