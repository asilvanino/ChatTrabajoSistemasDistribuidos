package com.uniminuto.sockets.hilos.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.DefaultListModel;

public class HiloServidor extends Thread{
	
	private DataInputStream entrada;
	private DataOutputStream salida;
	private SocketServidor server;
	private String nombreCliente;
	private Socket cliente;
	public static Vector<HiloServidor> clienteAct = new Vector<HiloServidor>();
	private ObjectOutputStream salidaObjeto;

	public HiloServidor(Socket c, SocketServidor s, String nombreCliente) throws Exception {
		this.cliente = c;
		this.server = s;
		this.nombreCliente = nombreCliente;
		clienteAct.add(this);
		for(int i = 0; i < clienteAct.size(); i++) {
			clienteAct.get(i).envioMensaje(nombreCliente+" se conecto");			
		}
	}

	public void run() {
		String m = "";
		while(true) {
			try {
				entrada = new DataInputStream(cliente.getInputStream());
				salida = new DataOutputStream(cliente.getOutputStream());
				m = entrada.readUTF();
				for (int i = 0; i < clienteAct.size(); i++) {
					clienteAct.get(i).envioMensaje(m);
					server.mensajeria("**.:Mensaje Enviado:.**");
					salida.writeUTF(".:Su mensaje se envio:.");
				}
			} catch (Exception e) {
				break;
			}
		}
		clienteAct.removeElement(this);
		server.mensajeria("**.:Cliente Desconectado:.**");
		try {
			cliente.close();
		} catch (Exception e) {
			System.out.println(e);
		}
			
	}
		


    private void envioMensaje(String msg) throws Exception{
        salida = new DataOutputStream(cliente.getOutputStream()); 
        salida.writeUTF(msg); 
        DefaultListModel<String> modelo = new DefaultListModel<String>(); 
        for (int i = 0; i < clienteAct.size(); i++) {
                modelo.addElement(clienteAct.get(i).nombreCliente); 
        }
        salidaObjeto = new ObjectOutputStream(cliente.getOutputStream()); 
        salidaObjeto.writeObject(modelo); 
    }
	

}
