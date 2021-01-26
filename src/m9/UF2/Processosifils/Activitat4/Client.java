/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat4;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumne
 */
public class Client implements Runnable{
    public int[] articles;
    
    public int numClient;

    public Client(int numClient) {
        int rangeProduces = (30 - 1) + 1;             
        int rangeTemps = (8 - 2) + 2;
        
        this.numClient = numClient;
        
        this.articles = new int[(int)(Math.random() * rangeProduces) + 1];
        
        System.out.print(" amb " + articles.length + " (");
        
        for (int i = 0; i < articles.length; i++) {
            articles[i] = (int)(Math.random() * rangeTemps) + 2;
            
            if (articles.length - 1 == i) {
                System.out.println(articles[i] + ")");
            }else{
                System.out.print(articles[i] + ", ");
            }
            
        }

    }

    @Override
    public void run() {
        System.out.print("Client " + numClient + " article " + 1 + "/" + articles.length);
        
        for (int i = 0; i < articles.length; i++) {
            
            if (articles.length - 1 == i) {
                
            }else{
                System.out.println("Client " + numClient + " article " + i + "/" + articles.length + " (" + articles[i] + "segons)...");
            }
            
            try {
                TimeUnit.SECONDS.sleep(articles[i]);
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    
    
}
