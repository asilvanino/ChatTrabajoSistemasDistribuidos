import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
	
	private ArrayList<String> clientesAct = new ArrayList<String>();
	
	public void iniciarServidor() {
		try {	
			ServerSocket ss = new ServerSocket(9898);	
			Socket sc = new Socket();
			String com = "";
			while(!com.equals("x")) {
				String msg = "";
				System.out.println("..::Servidor Iniciado::..");
				sc = ss.accept();
				System.out.println("..::Se conecto un cliente::..");
				DataInputStream in = new DataInputStream(sc.getInputStream());
				DataOutputStream out = new DataOutputStream(sc.getOutputStream());
				com = in.readUTF();
				System.out.println(com);
				if(com.equals("..connect")) {
					out.writeUTF("Escriba su nombre: ");
					msg = in.readUTF();
					clientesAct.add(msg);
					out.writeUTF("Se conecto al servidor "+msg);
				}
				if(com.equals("..desconnect")) {
					out.writeUTF("Escriba su nombre: ");
					msg = in.readUTF();
					for (int i = 0; i < clientesAct.size(); i++) {
						if (msg.equals(clientesAct.get(i))) {
							clientesAct.remove(i);
							out.writeUTF("Se desconecto al servidor "+msg);
						}
					}
				}
				if(com.equals("..listC")) {
					for (int j = 0; j < clientesAct.size(); j++) {
						msg = clientesAct.get(j);
						out.writeUTF(msg);
					}
				}	
				sc.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		Servidor s = new Servidor();
		s.iniciarServidor();
	}
	
}
