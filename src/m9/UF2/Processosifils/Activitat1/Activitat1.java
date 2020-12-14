/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author david
 */
public class Activitat1 {

    //clase declarada dins de una altra clase
    static class Suma implements Callable<Integer> {

        private int operador1;
        private int operador2;

        public Suma(int operador1, int operador2) {
            this.operador1 = operador1;
            this.operador2 = operador2;
        }

        @Override
        public Integer call() throws Exception {
            return operador1 + operador2;
        }
    }

    public static void main(String[] args) throws
        InterruptedException, ExecutionException {
        // Li indiquem el numero de fils
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        
        //Creem una llista del que volem fer als fils
        List<Suma> llistaTasques = new ArrayList<Suma>();
        
        // Rellenem llista
        for (int i = 0; i < 25; i++) {
            Suma calcula = new Suma((int) (Math.random() * 10), (int) (Math.random() * 10));
            llistaTasques.add(calcula);
        }
        
        //Llista de resultats
        List<Future<Integer>> llistaResultats;
        
        // Diem que els fils comencin a treballar
        llistaResultats = executor.invokeAll(llistaTasques);
        
        // Quan ha acabat tanca tots els fils
        executor.shutdown();
        
        // Mostrem resultats
        for (int i = 0; i < llistaResultats.size(); i++) {
            Future<Integer> resultat = llistaResultats.get(i);
            try {
                System.out.println("Resultat tasca " + i + " és:"
                        + resultat.get());
            } catch (InterruptedException | ExecutionException e) {
            }
        }
    }

}
