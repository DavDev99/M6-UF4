/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat4;

/**
 *
 * @author david
 */
public class Euclid {

    // recursive implementation
    public static int gcd(int p, int q) {
        if (q == 0) 
            return p;
        else 
            return gcd(q, p % q);
    }

    public static void main(String[] args) {
        int p = 3;  
        int q = 5;
        int d  = gcd(p, q);
        System.out.println("gcd(" + p + ", " + q + ") = " + d);

    }
}
