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
        Scanner teclado = new Scanner(System.in);
        String description;
        int stockActual;
        int minStock;
        int pvp;
        int nextId;
        BaseDades baseDades = new BaseDades();
        
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
