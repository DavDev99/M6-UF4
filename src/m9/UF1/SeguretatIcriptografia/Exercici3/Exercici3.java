/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici3;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Alumne
 */
public class Exercici3 {

    public static SecretKey passwordKeyGeneration(String text, int keySize) throws UnsupportedEncodingException {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize/8);
                sKey = new SecretKeySpec(key, "AES");
            } catch (Exception ex) {
                System.err.println("Error generant la clau: " + ex);
            }
        }
        return sKey;
    }
    
        public static SecretKey passwordKeyGenerationSHA384(String text, int keySize) throws UnsupportedEncodingException {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-384");
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize/8);
                sKey = new SecretKeySpec(key, "AES");
            } catch (Exception ex) {
                System.err.println("Error generant la clau: " + ex);
            }
        }
        return sKey;
    }
        
        public static SecretKey passwordKeyGenerationSHA512224(String text, int keySize) throws UnsupportedEncodingException {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-512/224");
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize/8);
                sKey = new SecretKeySpec(key, "AES");
            } catch (Exception ex) {
                System.err.println("Error generant la clau: " + ex);
            }
        }
        return sKey;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        //Generem clau
        //SecretKey key1 = passwordKeyGeneration("aaaa", 128);
        //SecretKey key1 = passwordKeyGenerationSHA384("aaaa", 128);
        SecretKey key1 = passwordKeyGenerationSHA512224("aaaa", 128);

        //Agafem els bytes
        byte[] keyBytes = key1.getEncoded();
        System.out.println("Els bytes generats son:");

        for (int i = 0; i < keyBytes.length; i++) {
            // Pasem els bytes a un string
            String aux = String.format("%8s", Integer.toBinaryString(keyBytes[i] & 0xFF)).replace(' ', '0');

            // Pasem de binari a decimal
            int aux2 = keyBytes[i];

            // Mostrem el nombre
            System.out.println(aux + " - " + aux2);

        }

    }

}
