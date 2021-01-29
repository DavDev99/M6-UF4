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
public class ArrayBombolla implements Runnable{

    int[] array;
    int part;

    public ArrayBombolla(int[] arrayBombolla, int part) {
        this.array = arrayBombolla;
        this.part = part;
    }

    @Override
    public void run() {
        int auxiliar;
        int[] arrayAux;
        for (int i = 1; i < array.length; i++) {

            for (int j = 0; j < array.length - i; j++) {

                if (array[j] > array[j + 1]) {
                    arrayAux = array.clone();
                    auxiliar = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = auxiliar;
                    
                    System.out.println("Part " + (this.part + 1) + ": " + Arrays.toString(arrayAux) + " -> " + Arrays.toString(array));
                }

            }

        }
        
        System.out.println(Arrays.toString(array));
    }
}
