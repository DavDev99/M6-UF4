/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author Alumne
 */
public class A_fibonacci_forkJoin extends RecursiveTask<Long> {
    long numero;
    public A_fibonacci_forkJoin(long numero){
        this.numero=numero;
    }
    
    @Override
    protected Long compute() {
        // ATENCIO **1** double calcul = java.lang.Math.cos(54879854);
        if(numero <= 1) return numero;
        A_fibonacci_forkJoin fib1 = new A_fibonacci_forkJoin(numero-1);
        //fib1.fork();
	A_fibonacci_forkJoin fib2 = new A_fibonacci_forkJoin(numero-2);
        fib2.fork();
	 return fib1.compute()+ fib2.join();
	 }
    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Calculat:  " + pool.invoke(new A_fibonacci_forkJoin(35)));    
    }
}