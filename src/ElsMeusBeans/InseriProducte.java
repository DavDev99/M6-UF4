/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElsMeusBeans;

import java.util.Scanner;

/**
 *
 * @author David
 */
public class InseriProducte {

    public static void main(String[] args) {
        //Obrir la base de dades
        String urldb = "jdbc:mysql://localhost/m6_uf4_activitat3";
        String usuari = "root";
        String contrasenya = "";
        String driver = "com.mysql.jdbc.Driver";
        BaseDades baseDades = new BaseDades(urldb, usuari, contrasenya, driver);

        Scanner teclado = new Scanner(System.in);
        String description;
        int stockActual;
        int minStock;
        int pvp;
        int nextId;

        baseDades.setCrearConnexio();//Es crea la connexió a la base de dades

        if (baseDades.getCrearConnexio()) {
            
            System.out.println("Connectat");
            System.out.println("Introdueix la descripció del producte: ");
            description = teclado.nextLine();

            System.out.println("Introdueix el stock actual del producte: ");
            stockActual = teclado.nextInt();
            teclado.nextLine();

            System.out.println("Introdueix el stock minim del producte: ");
            minStock = teclado.nextInt();
            teclado.nextLine();

            System.out.println("Introdueix el PVP del producte: ");
            pvp = teclado.nextInt();
            teclado.nextLine();

            nextId = baseDades.obtenirUltimID("producte");

            Producte pro = new Producte(nextId, description, stockActual, minStock, pvp);

            baseDades.inserirProducte(pro);

            System.out.println("Producte inserit correctament");
        }
    }
}
