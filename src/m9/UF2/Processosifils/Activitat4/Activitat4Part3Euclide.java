/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import m9.UF2.Processosifils.Activitat3.A_fibonacci_forkJoin;
import static m9.UF2.Processosifils.Activitat4.Euclid.gcd;

/**
 *
 * @author david
 */
public class Activitat4Part3Euclide extends RecursiveTask<Long> {

    long q;
    long p;

    public Activitat4Part3Euclide(long q, long p) {
        this.q = q;
        this.p = p;
    }

    @Override
    protected Long compute() {
        if (q == 0) {
            return p;
        } else {
            // q, p % q
            Activitat4Part3Euclide aux = new Activitat4Part3Euclide(q, p % q);

            aux.fork();
            return aux.compute() % aux.join();
        }
    }

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Calculat:  " + pool.invoke(new Activitat4Part3Euclide(50, 100)));

    }
}
