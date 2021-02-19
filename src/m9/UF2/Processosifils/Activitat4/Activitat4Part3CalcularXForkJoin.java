/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author PC-Casa
 */
public class Activitat4Part3CalcularXForkJoin  extends RecursiveTask<Long>{
        // recursive implementation
    
    long n;
    long x;

    public Activitat4Part3CalcularXForkJoin(long n, long x) {
        this.n = n;
        this.x = x;
    }
    
    public static int calacular(int n, int x) {
        if (n == 0) 
            return 1;
        else 
            return calacular(n - 1, x) * x;
    }
    
    @Override
    protected Long compute() {
        
        if (n == 0) {
            return (long) 1;
        }else{
            Activitat4Part3CalcularXForkJoin aux = new Activitat4Part3CalcularXForkJoin(n - 1, x);
            
            aux.fork();
            return aux.join() * x;
        }
        
    }
    
    public static void main(String[] args) {
        
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Valor: " + pool.invoke(new Activitat4Part3CalcularXForkJoin(2,4)));

    }


}
