/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat4;

import java.util.Arrays;

/**
 *
 * @author Alumne
 */
public class Activitat4Part2 {

    public static void main(String[] args) {
        
        int[] numerosRandom = new int[20];
        int rangeNumeros = (9 - 1) + 1; 
        
        for (int i = 0; i < numerosRandom.length; i++) {
            numerosRandom[i] = (int)(Math.random() * rangeNumeros) + 1;
        }  
        
        metodeBombolla(numerosRandom);
        
        System.out.println(Arrays.toString(numerosRandom));
    }


}
