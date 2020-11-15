/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici7;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author PC-Casa
 */
public class Signatura {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, IOException, InvalidAlgorithmParameterException, SignatureException, InvalidKeySpecException {

        System.out.print("Generant claus publiques i privades (arxius clauPublica i clauPrivada)...");

        // Crear una KeyPair
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(512);

        // Creant les claus
        KeyPair pair = keyPairGen.generateKeyPair();
        PublicKey clauPublica = pair.getPublic();
        PrivateKey clauPrivada = pair.getPrivate();

        //Guardar una clauPublica
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(clauPublica.getEncoded());
            FileOutputStream fos = new FileOutputStream("src/m9/UF1/SeguretatIcriptografia/Exercici7/clauPublica.txt");
            fos.write(x509EncodedKeySpec.getEncoded());
            fos.close();
        } catch (Exception e) {
            System.out.println("ERROR guardant clau publica");
        }

        //Guardar una clauPrivada
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(clauPrivada.getEncoded());
            FileOutputStream fos = new FileOutputStream("src/m9/UF1/SeguretatIcriptografia/Exercici7/clauPrivada.txt");
            fos.write(pkcs8EncodedKeySpec.getEncoded());
            fos.close();
        } catch (Exception e) {
            System.out.println("ERROR guardant clau privada");
        }

        System.out.println("OK");
        
        // Declarem variables
        Scanner teclado = new Scanner(System.in);
        String missatge;

        // Guardem el missatge a firmar
        System.out.println("Introdueix el missatge a signar:");
        missatge = teclado.nextLine();
        System.out.print("Signant el missatge...");
        
        // Generem firma i firmem missatge
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(clauPrivada);
        privateSignature.update(missatge.getBytes("UTF8"));
        System.out.println("OK");

        byte[] firma = privateSignature.sign();

        // Guardem la firma a un fitxer
        System.out.print("Generant arxiu firma_missatge");
        
        try {
            FileOutputStream fos = new FileOutputStream("src/m9/UF1/SeguretatIcriptografia/Exercici7/firma_missatge.txt");
            fos.write(firma);
            fos.close();
        } catch (Exception e) {
            System.out.println("ERROR guardant la firma");
        }
        System.out.println("...OK");
        
        
        System.out.print("Generant arxiu missatge");
        try{
            FileOutputStream fos = new FileOutputStream("src/m9/UF1/SeguretatIcriptografia/Exercici7/missatge.txt");
            fos.write(missatge.getBytes());
            fos.close();
        } catch (Exception e) {
            System.out.println("ERROR guardant el missatge");
        }        

        System.out.println("...OK");

    }

}
