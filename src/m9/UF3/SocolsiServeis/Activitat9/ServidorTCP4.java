package m9.UF3.SocolsiServeis.Activitat9;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorTCP4 implements Runnable {
    String cadena = "";
    int numClient = 1;
    Socket client;
    
    public void run() {
        BufferedReader fentrada = null;
        try {
            System.out.println("Client connectat... ");
            //FLUX DE SORTIDA AL CLIENT
            PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);
            fsortida.println("Client " + numClient);
            //FLUX D'ENTRADA DEL CLIENT
            fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while ((cadena = fentrada.readLine()) != null) {
                
                fsortida.println(cadena);
                System.out.println("Rebent: " + cadena);
                if (cadena.equals("*")) {
                    break;
                }
                
            }   //TANCAR STREAMS I SOCKETS
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

        int numPort = 60000;
        ServerSocket servidor = new ServerSocket(numPort);


        Scanner teclado = new Scanner(System.in);
        System.out.println("Indica el numero de clients: ");
        int numClients = teclado.nextInt();
        teclado.nextLine();
        
        ServidorTCP4 server = new ServidorTCP4();

        for (int i = 0; i < numClients; i++) {
            

            System.out.println("Esperant connexió... ");
            Socket clientConnectat = servidor.accept();
            server.client = clientConnectat;
            
            Thread fil = new Thread(server);
            fil.start();
            numClients++;
        }
        servidor.close();

    }

}
