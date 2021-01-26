/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat4;

/**
 *
 * @author Alumne
 */
public class ArrayBombolla {

    int[] arrayBombolla;

    public ArrayBombolla(int[] arrayBombolla) {
        this.arrayBombolla = arrayBombolla;
    }

    public static int[] metodeBombolla(int[] array) {
        int auxiliar;

        for (int i = 1; i < array.length; i++) {

            for (int j = 0; j < array.length - i; j++) {

                if (array[j] > array[j + 1]) {
                    auxiliar = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = auxiliar;
                }

            }

        }

        return array;
    }
}
