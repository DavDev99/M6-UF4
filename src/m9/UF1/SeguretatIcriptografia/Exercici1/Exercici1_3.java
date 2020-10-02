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
            
            
            // Segons el numero de lletres que tingui la frase a desencriptar
            for (int i = 0; i < numeroDeLletres; i++) {    
                String descifrat = "";
                for (int j = 0; j < frase.length(); j++) {
                    // Si la lletra que estem desencriptan exixsteix al abecedari la desencriptem si no, posem la mateixa que ja llegit
                    int posicio = abecedari.indexOf(frase.charAt(j));
                    if (posicio >= 0) {
                        // Conseguim la posicio de la lletra desencriptada pasant el numero de vegades que saltem al abecedari i restant este numero, 
                        // i per a les lletres del principi com la a user el valor absolut de la resta
                        descifrat += (abecedari.charAt(Math.abs((posicio - i + numeroDeLletres) % numeroDeLletres)));
                    } else {
                        descifrat += frase.charAt(j);
                    }
                }

                System.out.println(descifrat);
            }

        }
}
