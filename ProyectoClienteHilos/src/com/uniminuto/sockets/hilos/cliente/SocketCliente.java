package com.uniminuto.sockets.hilos.cliente;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.DefaultListModel;

public class SocketCliente {
	
	private Socket cliente;
	private final int puerto=1526;
	private String host = "localhost";
	private DataOutputStream salida;
	private String nombre;
	private Vector<String> mensajes = new Vector<String>();
	
	public void initSocketCliente() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Escriba su nombre: ");
			nombre = sc.nextLine();
			cliente = new Socket (host, puerto);
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			salida.writeUTF(nombre);
			HiloCliente hilo = new HiloCliente(cliente, this);
			hilo.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void mensajeria() {
		String msg="";
		for (int i = 0; i < mensajes.size(); i++) {
			msg = mensajes.get(i);
			System.out.println(msg+"\n");
		}
	}
	
	public void salidaEvento() {
		try {
			Scanner sc = new Scanner(System.in);
			String m = "";
			System.out.println("Escriba su mensaje: ");
			m = sc.nextLine();
			salida = new DataOutputStream(cliente.getOutputStream());
			mensajes.add(nombre+": "+m);
			salida.writeUTF(nombre+": "+m);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void actualizarLista(DefaultListModel<?> modelo) {
		System.out.println(modelo);
	}
	
	public static void main(String[] args) {
		SocketCliente sc = new SocketCliente();
		sc.initSocketCliente();
	}
}
