/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF1PersistenciaEnFitxers.Exercici3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercici3 {

	public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        int opcio = -1;
        int opcioBusqueda = 0;
        String nom;
        String cognom;
        String segonCognom;
        int any;
        ArrayList<Persona> fitxes = new ArrayList<>();
        
        // Bucle principal del menu
        
        fitxes = Persona.llegirFitxes();
        
        while(opcio != 0){
            System.out.println("Que vols fer? \n"
                    + "1. Crear una fitxa \n"
                    + "2. Buscar una fitxa per camp \n"
                    + "0. sortir \n"
                    + "");
        
            opcio = teclado.nextInt();
            teclado.nextLine();
        
            // Creacio de la fitxa
            if (opcio == 1) {
                System.out.println("Introdueix un nom");
                nom = teclado.nextLine();

                System.out.println("Introdueix un cognom");
                cognom = teclado.nextLine();

                System.out.println("Introdueix un segon cognom");
                segonCognom = teclado.nextLine();

                System.out.println("Introdueix un any");
                any = teclado.nextInt();
                teclado.nextLine();
                
                Persona persona = new Persona(nom, cognom, segonCognom, any);

                fitxes.add(persona);

            }else if(opcio == 2){
                // Si vol buscar un cotxe demanem el camp de busqueda
                System.out.println("Opcio per la que vols buscar\n"
                        + "1. per nom,\n" 
                        + "2. per cognom,\n"
                        + "3. per segonCognom,\n"
                        + "4. per any,"
                );
                
                opcioBusqueda = teclado.nextInt();
                teclado.nextLine();
                // Segons el camp de busqueda treiem totes les caracteristiques de tots el cotxes que tenim al garatge
                if (opcioBusqueda == 1) {
                    System.out.println("Quin nom busques?");
                    nom = teclado.nextLine();
                    
                    for (int i = 0; i < fitxes.size(); i++) {
                        if (fitxes.get(i).getNom().equals(nom)) {
                            System.out.println(fitxes.get(i).toString());
                        }
                    }
                } else if (opcioBusqueda == 2) {
                    System.out.println("Que cognom busques?");
                    cognom = teclado.nextLine();

                    for (int i = 0; i < fitxes.size(); i++) {
                        if (fitxes.get(i).getCognom().equals(cognom)) {
                            System.out.println(fitxes.get(i).toString());
                        }
                    }
                } else if (opcioBusqueda == 3) {
                    System.out.println("Que segon cognom busques?");
                    segonCognom = teclado.nextLine();

                    for (int i = 0; i < fitxes.size(); i++) {
                        if (fitxes.get(i).getSegonCognom().equals(segonCognom)) {
                            System.out.println(fitxes.get(i).toString());

                        }
                    }
                }else{
                    System.out.println("Que any busques?");
                    any = teclado.nextInt();
                    teclado.nextLine();
                    for (int i = 0; i < fitxes.size(); i++) {
                        if (fitxes.get(i).getAnyNaixement()== any) {
                            System.out.println(fitxes.get(i).toString());
                        }
                    }
                }
            }

        }
    }

}
