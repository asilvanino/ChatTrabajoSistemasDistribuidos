
import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

class servidor {

    public static void main(String[] args) {

        ServerSocket server;
        Socket connection;

        DataOutputStream output;
        BufferedInputStream bis;
        BufferedOutputStream bos;

        byte[] receivedData;
        int in;
        String file;

        try {
            //Servidor Socket en el puerto 5000
            server = new ServerSocket(5000);
            while (true) {
                //Aceptar conexiones
                connection = server.accept();
                //Buffer de 1024 bytes
                receivedData = new byte[1024];
                bis = new BufferedInputStream(connection.getInputStream());
                DataInputStream dis = new DataInputStream(connection.getInputStream());
                //Recibimos el nombre del fichero
                file = dis.readUTF();
                file = file.substring(file.indexOf('\\') + 1, file.length());

                FileInputStream Dir;
                Dir = new FileInputStream(file);
                AudioStream audio = new AudioStream(Dir);
                AudioPlayer.player.start(audio);

                //Para guardar fichero recibido
                bos = new BufferedOutputStream(new FileOutputStream(file));
                while ((in = bis.read(receivedData)) != -1) {
                    bos.write(receivedData, 0, in);
                }
                bos.close();
                dis.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
