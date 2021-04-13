package m9.UF3.SocolsiServeis.Activitat10;

import m9.UF3.SocolsiServeis.Activitat9.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Activitat10Server implements Runnable {

    static int numPort = 60000;
    static ServerSocket serverSocket;
    static ArrayList<Socket> clients = new ArrayList<>();
    int idClient;
    String name;
    Socket client;

    public void run() {
        BufferedReader fentrada = null;
        String cadena = "";

        try {
            System.out.println("Client connectat... ");

            //FLUX DE SORTIDA AL CLIENT
            PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);

            //FLUX D'ENTRADA DEL CLIENT
            fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));

            while ((cadena = fentrada.readLine()) != null) {

                if (cadena.equals("*")) {
                    break;
                } else if (cadena.contains(Protocols.NAME)) {
                    name = cadena.split(Protocols.NAME)[1];
                } else if (cadena.contains(Protocols.MESSAGE)) {
                    
                    for (int i = 0; i < clients.size(); i++) {
                        if (idClient != i) {
                            PrintWriter sender = new PrintWriter(clients.get(i).getOutputStream(), true);
                            sender.println(name + ": " + cadena.split(Protocols.MESSAGE)[1]);
                        }
                    }
                    
                }

            }

            //TANCAR STREAMS I SOCKETS
            System.out.println("Tancant connexió... ");
            fentrada.close();
            fsortida.close();
            client.close();

        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP4.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                fentrada.close();
            } catch (IOException ex) {
                Logger.getLogger(ServidorTCP4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner teclado = new Scanner(System.in);

        serverSocket = new ServerSocket(numPort);

        System.out.println("Indica el numero de clients: ");
        int numClients = teclado.nextInt();
        teclado.nextLine();

        for (int i = 0; i < numClients; i++) {
            Activitat10Server server = new Activitat10Server();
            System.out.println("Esperant connexió... ");

            server.client = server.serverSocket.accept();
            clients.add(server.client);

            server.idClient = i;

            Thread fil = new Thread(server);
            fil.start();
        }

        serverSocket.close();
    }
}
