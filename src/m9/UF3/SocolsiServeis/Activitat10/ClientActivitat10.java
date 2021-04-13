package m9.UF3.SocolsiServeis.Activitat10;

import m9.UF3.SocolsiServeis.Activitat9.*;
import m9.UF3.SocolsiServeis.Activitat5.*;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static m9.UF3.SocolsiServeis.Activitat10.Activitat10Server.clients;

public class ClientActivitat10 implements Runnable {

    String name;
    static String host = "localhost";
    static int port = 60000;//Port remot
    Socket socket;
    PrintWriter fsortida;
    BufferedReader fentrada;

    @Override
    public void run() {
        try {
            String cadena = "";
            while ((cadena = fentrada.readLine()) != null) {
                System.out.println(cadena);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientActivitat10.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws Exception {

        ClientActivitat10 client = new ClientActivitat10();

        client.socket = new Socket(host, port);

        //FLUX DE SORTIDA AL SERVIDOR
        client.fsortida = new PrintWriter(client.socket.getOutputStream(), true);

        //FLUX D'ENTRADA AL SERVIDOR
        client.fentrada = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));

        //FLUX PER A ENTRADA ESTÀNDARD
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Introdueix un nom d'usuari: ");
        client.name = in.readLine();

        client.fsortida.println(Protocols.NAME + client.name);

        Thread fil = new Thread(client);
        fil.start();

        System.out.println("Benvingut " + client.name + ", ja pots començar a chatejar!");
        String cadena = in.readLine();

        while (cadena != null && !cadena.equals("*")) {

            //Enviament cadena al servidor
            client.fsortida.println(Protocols.MESSAGE + cadena);

            //Lectura del teclat
            cadena = in.readLine();
        }

        client.fsortida.close();
        client.fentrada.close();
        client.socket.close();
        in.close();
    }
}
