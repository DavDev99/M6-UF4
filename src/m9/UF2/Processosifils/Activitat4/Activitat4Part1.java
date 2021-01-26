/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat4;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author Alumne
 */
public class Activitat4Part1{
    
    
    
    public static void main(String[] args) throws InterruptedException {
        int clients[] = new int[50];
        
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
                
        for (int i = 0; i < clients.length; i++) {
            System.out.print("Creant el client " + (i + 1));
            
            Client client = new Client(i);
            
            Thread.sleep(1000);
            
            executor.execute(client);
            
            System.out.print("Client " + (client.numClient + 1) + " passa per caixa...");
        }
        

        
        
    }

}
