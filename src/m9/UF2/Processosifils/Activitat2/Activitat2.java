/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat2;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author david
 */
public class Activitat2 {

    public static void main(final String... args) throws InterruptedException, ExecutionException {
        // Mostrem hora d'execusio
        Calendar calendario = new GregorianCalendar();
        System.out.println("Inici: " 
                + calendario.get(Calendar.HOUR_OF_DAY) + ":" 
                + calendario.get(Calendar.MINUTE) + ":"
                + calendario.get(Calendar.SECOND));
        
        // Li diem el numero de fils que executarem
        final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(4);
        final Runnable ob = new Activitat2().new ExecutaFil();
        
        // Li diem que se exucutara en 5 segons i fara la repeticio cada 6 segons
        schExService.scheduleWithFixedDelay(ob, 5, 6, TimeUnit.SECONDS);
        // Li diem quan tardara en acabar
        schExService.awaitTermination(30, TimeUnit.SECONDS);
        // Quan acaba se tanca
        schExService.shutdownNow();
        System.out.println("Completat");
    }

    // Clase amb metode run
    class ExecutaFil implements Runnable {

        @Override
        public void run() {
            Calendar calendario = new GregorianCalendar();
            System.out.println("Hora execució tasca: "
                    + calendario.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendario.get(Calendar.MINUTE) + ":"
                    + calendario.get(Calendar.SECOND));
            System.out.println("Tasca en execució");
            System.out.println("Execució acabada");
        }
    }
}
