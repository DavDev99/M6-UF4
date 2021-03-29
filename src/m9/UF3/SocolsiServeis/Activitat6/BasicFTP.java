/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF3.SocolsiServeis.Activitat6;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;
import org.apache.commons.net.ftp.*;

/**
 *
 * @author David
 */
public class BasicFTP {

    public static void main(String[] args) {

        //Servidor FTP
        FTPClient client = new FTPClient();
        String ServerFTP = "ftp.urv.es";
        System.out.println("Ens connectem al servidor: " + ServerFTP);

        //Usuari FTP
        String usuari = "anonymous";
        String contrasenya = "guest";

        try {
            //Variables
            int consulta = -1;
            Scanner teclado = new Scanner(System.in);

            client.connect(ServerFTP);
            boolean login = client.login(usuari, contrasenya);

            if (login) {
                System.out.println("Login correcte... ");
            } else {

                System.out.println("Login incorrecte... ");
                client.disconnect();
                System.exit(1);

            }

            while (consulta != 0) {

                System.out.print("1. Directori actual:  \n"
                        + "2. Numero de fitxers de directori actual: \n"
                        + "3. Mostrar fitxers: \n"
                        + "0. Logout: \n"
                );

                consulta = teclado.nextInt();
                teclado.nextLine();

                if (consulta == 1) {

                    directoriActual(client);
                    System.out.println("");

                } else if (consulta == 2) {

                    numFitxerActual(client);
                    System.out.println("");

                } else if (consulta == 3) {

                    mostrarFitxers(client);
                    System.out.println("");

                } else if (consulta == 0) {

                    logutFtp(client);
                    System.out.println("");

                }

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private static void directoriActual(FTPClient client) throws IOException {
        System.out.println("Directori actual: " + client.printWorkingDirectory());
    }

    private static void numFitxerActual(FTPClient client) throws IOException {
        FTPFile[] files = client.listFiles();
        System.out.println("Fitxers al directori actual: " + files.length);
    }

    private static void mostrarFitxers(FTPClient client) throws IOException {
        FTPFile[] files = client.listFiles();
        String tipus[] = {"Fitxer", "Directori", "Enllaç simbolic"};

        for (int i = 0; i < files.length; i++) {

            System.out.println("\t" + files[i].getName() + "=>" + tipus[files[i].getType()]);

        }

    }

    private static void logutFtp(FTPClient client) throws IOException {
        boolean logout = client.logout();

        if (logout) {
            System.out.println("Logout del servidor FTP... ");
        } else {
            System.out.println("Error en fer un logout... ");
        }

        client.disconnect();
        System.out.println("Desconnectat... ");
    }

}
