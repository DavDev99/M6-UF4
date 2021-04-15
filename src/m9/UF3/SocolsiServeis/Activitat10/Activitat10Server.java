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
    static ArrayList<Activitat10Server> clients = new ArrayList<>();
    int idClient;
    String name;
    Socket client;

    public void run() {
        BufferedReader fentrada = null;
        String cadena = "";
        boolean logout = false;
        try {
            System.out.println("Client connectat... ");

            //FLUX DE SORTIDA AL CLIENT
            PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);

            //FLUX D'ENTRADA DEL CLIENT
            fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));

            while ((cadena = fentrada.readLine()) != null && !logout) {

                if (cadena.equals(Protocols.LOG_OUT)) {

                    logout = true;

                } else if (cadena.contains(Protocols.NAME)) {

                    String auxName = cadena.replace(Protocols.NAME, "");

                    for (int i = 0; i < clients.size(); i++) {
                        if (idClient != i) {

                            Activitat10Server activitat10Server = (Activitat10Server) clients.get(i);

                            if (!activitat10Server.name.equals(auxName)) {
                                name = auxName;
                            }
                        }
                    }

                    if (clients.size() <= 1) {
                        name = auxName;
                    }

                    if (name == null) {
                        fsortida.println(Protocols.BAD_NAME);
                    }else{
                        fsortida.println(Protocols.NAME);
                    }

                } else if (cadena.equals(Protocols.USER_LIST)) {

                    PrintWriter sender = new PrintWriter(client.getOutputStream(), true);
                    String userList = "";
                    int count = 0;

                    for (int i = 0; i < clients.size(); i++) {
                        if (idClient != i) {

                            count++;
                            Activitat10Server activitat10Server = (Activitat10Server) clients.get(i);

                            userList += count + ". " + activitat10Server.name + "\n";
                        }
                    }

                    sender.println("User list: \n" + userList);

                } else if (cadena.contains(Protocols.MESSAGE)) {

                    for (int i = 0; i < clients.size(); i++) {
                        if (idClient != i) {

                            Activitat10Server activitat10Server = (Activitat10Server) clients.get(i);

                            PrintWriter sender = new PrintWriter(activitat10Server.client.getOutputStream(), true);
                            sender.println(name + ": " + cadena.replace(Protocols.MESSAGE, ""));
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
            server.idClient = i;

            Thread fil = new Thread(server);
            clients.add(server);
            fil.start();
        }

        serverSocket.close();
    }
}
