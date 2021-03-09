import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
	
	private static Scanner write = new Scanner(System.in);
	private ArrayList<String> clientesAct = new ArrayList<String>();
	private DataInputStream in;
	private DataOutputStream out;
	
	public void iniciarClienteServidor() {
		try {
			String msg = "";
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("..::Cliente Servidor Iniciado::..");
			Socket sc = ss.accept();
			in = new DataInputStream(sc.getInputStream());
			out = new DataOutputStream(sc.getOutputStream());
			while(true) {
				System.out.println("Mensaje: "+in.readUTF());
				System.out.println("**Escriba un mensaje...");
				msg = write.nextLine();
				out.writeUTF(msg);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void iniciarCliente() {
		try {
			Socket sc = new Socket("localhost",9999);
			in = new DataInputStream(sc.getInputStream());
			out = new DataOutputStream(sc.getOutputStream());
			String msg = "";
			while(!msg.equals("x")) {
				System.out.println("**Escriba un mensaje...");
				msg = write.nextLine();
				out.writeUTF(msg);
				System.out.println("Mensaje: "+in.readUTF());
			}
			sc.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void listaClientesActivos() {
		try {
			String msg="";
			while(!msg.equals("x")) {
				msg = in.readUTF();
				clientesAct.add(msg);
				System.out.println("- "+msg);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void eventosServidor() {
		try {
			String nombre ="";
			String com ="";
			String msg = "";
			Socket sc = new Socket("localhost", 9898);
			System.out.println("..::Se conecto con el servidor::..");
			in = new DataInputStream(sc.getInputStream());
			out = new DataOutputStream(sc.getOutputStream());
			while(!msg.equals("x")) {
				System.out.println(
						  "..connect   \t \t [Ingresa al servidor y deja su nombre en la lista] \n"
						+ "..deconnect \t \t [Elimina su nombre de la lista del servidor] \n"
						+ "..listC     \t \t [Se mostrara la lista de usuarios conectados]");
				System.out.print(">");
				com = write.nextLine();
				out.writeUTF(com);
				if(!com.equals("..listC")) {
					System.out.println(in.readUTF());
					nombre = write.nextLine();
					out.writeUTF(nombre);
					System.out.println(in.readUTF());
					msg="x";
				}else {
					System.out.println("Clientes Conectados: ");
					while(!msg.equals("x")) {
						if(!msg.equals("x")) {
							System.out.println(" -"+in.readUTF());
						}
						else {
							break;
						}
					}
				}
			}
			sc.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void comandosCliente() {
		String com = "";
		while(true) {
			System.out.print(">");
			com = write.nextLine();
			switch(com) {
				case "..help":
					System.out.println( "Lista de Comandos: \n"
							  + "..help     \t \t [Muestra todos los comandos] \n"
							  + "..createCS \t \t [Crea un nodo en el cliente] \n"
							  + "..createC  \t \t [Crea un cliente para comunicar] \n"
							  + "..eventS   \t \t [Se puede conectar o desconectar del servidor] \n"
							  /*+ "..listC    \t \t [Muestra la lista de clientes activos] \n"*/);
					break;
				case "..createCS":
					iniciarClienteServidor();
					break;
				case "..createC":
					iniciarCliente();
					break;
				case "..eventS":
					eventosServidor();
					break;
				/*case "..listC":
					listaClientesActivos();
					break;*/
				default:
					System.out.println("Error al escribir comando");
					System.out.println( "Lista de Comandos: \n"
							  + "..help     \t \t [Muestra todos los comandos] \n"
							  + "..createCS \t \t [Crea un nodo en el cliente] \n"
							  + "..createC  \t \t [Crea un cliente para comunicar] \n"
							  + "..eventS   \t \t [Se puede conectar o desconectar del servidor] \n"
							  + "..listC    \t \t [Muestra la lista de clientes activos] \n");
			}
		}
	}
	
	public static void main(String[] args) {
		Cliente c = new Cliente();		
		c.eventosServidor();
		c.comandosCliente();
	}
}
