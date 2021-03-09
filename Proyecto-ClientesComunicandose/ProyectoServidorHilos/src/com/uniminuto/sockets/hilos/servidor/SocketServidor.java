package com.uniminuto.sockets.hilos.servidor;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {
	
	private ServerSocket server;
	private final int puerto=1526;

	public void initSocketServidor() {
		try {
			server = new ServerSocket(puerto);
			System.out.println("**.:Servidor Iniciado:.**");
			while(true) {
				Socket cliente = server.accept();
				mensajeria("Cliente conectado desde: "+cliente.getInetAddress().getHostAddress());
				DataInputStream entrada = new DataInputStream(cliente.getInputStream());
				HiloServidor hilo = new HiloServidor(cliente, this,entrada.readUTF());
				hilo.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void mensajeria(String msg) {
		System.out.println(" "+msg+" ");
	}
	
	public static void main(String[] args) {
		SocketServidor ss = new SocketServidor();
		ss.initSocketServidor();
	}
}
