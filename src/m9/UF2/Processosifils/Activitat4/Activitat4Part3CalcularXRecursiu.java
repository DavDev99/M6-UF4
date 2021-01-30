/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat4;

/**
 *
 * @author PC-Casa
 */
public class Activitat4Part3CalcularXRecursiu {
        // recursive implementation
    public static int calacular(int n, int x) {
        if (n == 0) 
            return 1;
        else 
            return calacular(n - 1, x) * x;
    }

    public static void main(String[] args) {
        
       
        System.out.println("Valor: " + calacular(2,4));

    }
}
