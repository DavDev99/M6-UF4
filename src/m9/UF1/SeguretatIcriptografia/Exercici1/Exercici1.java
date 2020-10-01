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
            String abecedari[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "v" , "w", "x", "y", "z", " "};
            String abecedariEncriptat[] = new String[abecedari.length];
            
            Scanner teclado = new Scanner(System.in);
            
            int code = 0;
            String frase;
            String fraseEncriptada[];
            
            System.out.println("Introdueix el numero de desplaçaments:");
            
            code = teclado.nextInt();
            teclado.nextLine();
            
            System.out.println("Introdueix el numero de desplaçaments:");
            
            frase = teclado.nextLine();
            fraseEncriptada = frase.split("");
            
            for (int i = 0; i < abecedari.length; i++) {
                if (i + code ) {
                    
                }
                abecedariEncriptat[i] = abecedari[i + code];
            }
            
            for (int i = 0; i < fraseEncriptada.length; i++) {
                fraseEncriptada[i] = 
            }
            
        }
}
