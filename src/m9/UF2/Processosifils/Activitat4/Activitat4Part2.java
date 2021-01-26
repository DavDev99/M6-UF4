/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat4;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author Alumne
 */
public class Activitat4Part2 {

    public static void main(String[] args) {
        
        int[] numerosRandom = new int[20];
        int rangeNumeros = (9 - 1) + 1; 
        
        ArrayBombolla[] arrayBombollas = new ArrayBombolla[4];
        
        for (int i = 0; i < numerosRandom.length; i++) {
            numerosRandom[i] = (int)(Math.random() * rangeNumeros) + 1;
        }  
        
        System.out.println(Arrays.toString(numerosRandom));
        
        int contadorAux = 0;
        for (int i = 0; i < arrayBombollas.length; i++) {
            int[] arrayAux = new int[numerosRandom.length / 4];
            for (int j = 0; j < arrayAux.length; j++) {
                arrayAux[j] = numerosRandom[contadorAux];
                contadorAux++;
            }
            ArrayBombolla arrayBomb = new ArrayBombolla(arrayAux, i);
            arrayBombollas[i] = arrayBomb;
        }
        
        
        
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        
        for (int i = 0; i < arrayBombollas.length; i++) {
            executor.execute(arrayBombollas[i]);
        }
        executor.shutdown();
        
    }


}
