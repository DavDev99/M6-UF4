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

    int[] arrayBombolla;
    int part;

    public ArrayBombolla(int[] arrayBombolla, int part) {
        this.arrayBombolla = arrayBombolla;
        this.part = part;
    }

    @Override
    public void run() {
        int auxiliar;

        for (int i = 1; i < arrayBombolla.length; i++) {

            for (int j = 0; j < arrayBombolla.length - i; j++) {

                if (arrayBombolla[j] > arrayBombolla[j + 1]) {
                    auxiliar = arrayBombolla[j];
                    arrayBombolla[j] = arrayBombolla[j + 1];
                    arrayBombolla[j + 1] = auxiliar;
                }

            }

        }
        
        System.out.println(Arrays.toString(arrayBombolla));
    }
}
