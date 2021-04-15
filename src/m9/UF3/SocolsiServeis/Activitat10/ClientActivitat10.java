package m9.UF3.SocolsiServeis.Activitat10;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientActivitat10 implements Runnable {

    String name;
    static String host = "localhost";
    static int port = 60000;//Port remot
    Socket socket;
    PrintWriter fsortida;
    BufferedReader fentrada;
    BufferedReader in;
    boolean goodName = false;

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
        String cadena;

        client.socket = new Socket(host, port);

        //FLUX DE SORTIDA AL SERVIDOR
        client.fsortida = new PrintWriter(client.socket.getOutputStream(), true);

        //FLUX D'ENTRADA AL SERVIDOR
        client.fentrada = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));

        //FLUX PER A ENTRADA ESTÀNDARD
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        client.in = in;

        System.out.println("Introdueix un nom d'usuari: ");
        client.name = in.readLine();

        client.fsortida.println(Protocols.NAME + client.name);

        while (!client.goodName) {

            if ((cadena = client.fentrada.readLine()) != null) {

                if (cadena.equals(Protocols.BAD_NAME)) {

                    System.out.println("Aquest nom ja esta agafat, introdueix un altre:");
                    cadena = in.readLine();

                    if (!cadena.equals(client.name)) {
                        client.goodName = true;
                        cadena = client.name;
                    }

                    client.fsortida.println(Protocols.NAME + cadena);

                } else {
                    client.goodName = true;
                    cadena = client.name;
                }
            }
        }

        Thread fil = new Thread(client);
        fil.start();

        System.out.println("=============================================================");
        System.out.println("Escriu '!help' per a mostrar totes les comandes del servidor");
        System.out.println("=============================================================");

        cadena = in.readLine();

        while (cadena != null && !cadena.equals("!logout")) {

            if (cadena.equals("!help")) {

                System.out.println("Aquestes son les comandes: \n"
                        + "1. !msg [nom]: Envia un missatge privat a l'usuari indicat \n"
                        + "2. !user-list: Llista tots els usaris del grup \n"
                        + "3. !logout: Te desconectes del grup \n");

            } else if (cadena.equals("!logout")) {

                client.fsortida.println(Protocols.LOG_OUT);

            } else if (cadena.equals("!user-list")) {

                client.fsortida.println(Protocols.USER_LIST);

            } else if (cadena.contains("!msg")) {

                client.fsortida.println(Protocols.PRIVATE_MESSAGE);

            } else {
                client.fsortida.println(Protocols.MESSAGE + cadena);
            }
            //Lectura del teclat
            cadena = in.readLine();
        }

        client.fsortida.close();
        client.fentrada.close();
        client.socket.close();
        in.close();
    }
}
