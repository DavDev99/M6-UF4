/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici7;

import java.io.FileOutputStream;
import java.io.IOException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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
    }
}
