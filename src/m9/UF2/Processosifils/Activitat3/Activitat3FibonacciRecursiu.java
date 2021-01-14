/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat3;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author Alumne
 */
public class Activitat3FibonacciRecursiu {

    public static void main(String[] args) {
        calculaFibonacci(50);
    }

    public static long calculaFibonacci(long numero) {
        // Calcul dificil
        //double calcul = java.lang.Math.cos(54879854);
        
        if (numero == 0) {
            return 0;
        } else if (numero == 1) {
            return 1;
        } else {
            return (calculaFibonacci(numero - 2) + calculaFibonacci(numero - 1));
        }
    }
}
