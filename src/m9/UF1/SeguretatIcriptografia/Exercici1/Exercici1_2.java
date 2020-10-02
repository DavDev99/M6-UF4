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
public class Exercici1_2 {
        public static void main(String[] args) {
            Scanner teclado = new Scanner(System.in);
            
            int code = 0;
            String frase;
            String abecedari = "abcdefghijklmnopqrstuvwxyz";
            final int numeroDeLletres = abecedari.length();
            
            System.out.println("Introdueix el numero de desplaçaments:");
            code = teclado.nextInt();
            teclado.nextLine();
            
            System.out.println("Introdueix la frase a desencriptar:");
            frase = teclado.nextLine();
            
            String descifrat = "";
            // Segons el numero de lletres que tingui la frase a desencriptar
            for (int i = 0; i < frase.length(); i++) {
                // Si la lletra que estem desencriptan exixsteix al abecedari la desencriptem si no, posem la mateixa que ja llegit
                int posicio = abecedari.indexOf(frase.charAt(i));
                if (posicio >= 0) {
                    // Conseguim la posicio de la lletra desencriptada pasant el numero de vegades que saltem al abecedari i restant este numero, 
                    // i per a les lletres del principi com la a user el valor absolut de la resta
                    descifrat += (abecedari.charAt(Math.abs((posicio - code + numeroDeLletres) % numeroDeLletres)));
                } else {
                    descifrat += frase.charAt(i);
                }
            }
            
            System.out.println(descifrat);
            
        }
}
