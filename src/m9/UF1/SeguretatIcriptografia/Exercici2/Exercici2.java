/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici2;

import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Alumne
 */


public class Exercici2 {
    public static SecretKey keygenKeyGeneration (int keySize) {
        SecretKey sKey = null ;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) { 
            try {
                KeyGenerator kgen =  KeyGenerator.getInstance("AES"); 
                kgen.init(keySize);
                sKey = kgen.generateKey();
            }catch (NoSuchAlgorithmException ex) {
                System.err.println ("Generador no disponible.");
            }
        }
        return sKey;
    }
    
    public static void main(String[] args) {
        
        //Generem clau
        SecretKey key1 = keygenKeyGeneration(128);
        
        //Agafem els bytes
        byte[] keyBytes = key1.getEncoded();
        System.out.println("Els bytes generats son:");
        
        for (int i = 0; i < keyBytes.length; i++) {
            // Pasem els bytes a un string
            String aux = Integer.toBinaryString(keyBytes[i] & 0xFF).replace(' ', '0');
            
            // Posem 0 si no arriba a 8
            if(aux.length() < 8){
                
                String faltenCeros = "";
                
                for (int j = 0; j + (aux.length()) <8; j++) {
                    faltenCeros = "0" + faltenCeros;
                }
                
                aux = faltenCeros + aux;
            }
            
            // Pasem de binari a decimal
            int aux2 = Integer.parseInt(aux, 2);
             
            // Mostrem el nombre
            System.out.println(aux + " - " + aux2);
           
        }

    }
}

