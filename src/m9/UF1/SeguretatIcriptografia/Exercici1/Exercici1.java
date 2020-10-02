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
public class Exercici1 {
        public static void main(String[] args) {
            Scanner teclado = new Scanner(System.in);
            
            int code = 0;
            String frase;
            String abecedari = "abcdefghijklmnopqrstuvwxyz";
            
            System.out.println("Introdueix el numero de desplaçaments:");
            code = teclado.nextInt();
            teclado.nextLine();
            
            System.out.println("Introdueix la frase a encripta:");
            frase = teclado.nextLine();
            
            String cifrat = "";
            // Segons el numero de lletres que tingui la frase a encriptar
            for (int i = 0; i < frase.length(); i++) {
                // Si la posicio de la lletra que estem encriptan exixsteix al abecedari la encriptem si no posem la que llegim
                int posicio = abecedari.indexOf(frase.charAt(i));
                if (posicio >= 0) {
                    // Conseguim la posicio de la lletra encriptada pasant el numero de vegades que saltem al abecedari, 
                    // i per a les lletres del final com la z agafem el residu de la divisio entre la lletra encriptada i la llargaria del abecedari
                    cifrat += abecedari.charAt((posicio + code) % abecedari.length());
                } else {
                    cifrat += frase.charAt(i);
                }
            }
            
            System.out.println(cifrat);
            
        }
}
