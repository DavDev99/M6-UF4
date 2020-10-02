/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici1;

import java.util.Scanner;

/**
 *
 * @author Alumne
 */
public class Exercici1_3 {
        public static void main(String[] args) {
            Scanner teclado = new Scanner(System.in);
            
            String frase;
            String abecedari = "abcdefghijklmnopqrstuvwxyz";
            final int numeroDeLletres = abecedari.length();
            
            System.out.println("Introdueix la frase a desencriptar:");
            frase = teclado.nextLine();
            
            
            // Agafem l'exercici de desencriptar i cambien el codi demanat per la i del primer for que es el numero posibilitats que hi ha
            for (int i = 0; i < numeroDeLletres; i++) {    
                String descifrat = "";
                for (int j = 0; j < frase.length(); j++) {

                    int posicio = abecedari.indexOf(frase.charAt(j));
                    if (posicio >= 0) {

                        descifrat += (abecedari.charAt(Math.abs((posicio - i + numeroDeLletres) % numeroDeLletres)));
                    } else {
                        descifrat += frase.charAt(j);
                    }
                }

                System.out.println(descifrat);
            }

        }
}
