/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici5;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Alumne
 */
public class Exercici5 {

    // Variables de clase

    static PrivateKey privateKey;
    static PublicKey publicKey;

    public static void main(String[] args) throws Exception {
        // Variables
        Scanner teclado = new Scanner(System.in);
        String text;

        String clauPrivada;
        String clauPublica;

        // Recollir dades
        System.out.println("TEXT:");
        text = teclado.next();
        
        clauPrivada = "clauPrivada.txt";
        clauPublica = "rclauPublica.txt";

        genKeyPair(512);

    }

    public static void genKeyPair(int size) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(size);
        KeyPair kp = kpg.genKeyPair();

        PublicKey publicKey2 = kp.getPublic();
        PrivateKey privateKey2 = kp.getPrivate();

        privateKey = privateKey2;
        publicKey = publicKey2;
    }
}
