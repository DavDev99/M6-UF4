package m9.UF3.SocolsiServeis.Activitat5;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ServidorTCP4 {

    public static void main(String[] args) throws Exception {

        int numPort = 60000;
        ServerSocket servidor = new ServerSocket(numPort);
        String cadena = "";
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Indica el numero de clients: ");
        int numClients = teclado.nextInt();
        teclado.nextLine();
        
        for (int i = 0; i < numClients; i++) {

            System.out.println("Esperant connexió... ");
            Socket clientConnectat = servidor.accept();
            System.out.println("Client connectat... ");

            //FLUX DE SORTIDA AL CLIENT
            PrintWriter fsortida = new PrintWriter(clientConnectat.getOutputStream(), true);

            fsortida.println("Client " + (i + 1));
            
            //FLUX D'ENTRADA DEL CLIENT
            BufferedReader fentrada = new BufferedReader(new InputStreamReader(clientConnectat.getInputStream()));

            while ((cadena = fentrada.readLine()) != null) {

                fsortida.println(cadena);
                System.out.println("Rebent: " + cadena);
                if (cadena.equals("*")) {
                    break;
                }

            }

            //TANCAR STREAMS I SOCKETS
            System.out.println("Tancant connexió... ");
            fentrada.close();
            fsortida.close();
            clientConnectat.close();
        }
        servidor.close();

    }

}
