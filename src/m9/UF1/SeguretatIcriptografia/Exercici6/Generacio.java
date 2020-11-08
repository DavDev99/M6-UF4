/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici6;

import java.io.FileOutputStream;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author david
 */
public class Generacio {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        // Creem un keyPair
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(512);

        // Generar les claus
        KeyPair pair = keyPairGen.generateKeyPair();
        PublicKey clauPublica = pair.getPublic();
        PrivateKey clauPrivada = pair.getPrivate();

        //Guardar una clauPublica
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(clauPublica.getEncoded());
            FileOutputStream fos = new FileOutputStream("src/m9/UF1/SeguretatIcriptografia/Exercici6/clauPublica.txt");
            fos.write(x509EncodedKeySpec.getEncoded());
            fos.close();
        } catch (Exception e) {
            System.out.println("ERROR guardant clau publica");
        }

        //Guardar una clauPrivada
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(clauPrivada.getEncoded());
            FileOutputStream fos = new FileOutputStream("src/m9/UF1/SeguretatIcriptografia/Exercici6/clauPrivada.txt");
            fos.write(pkcs8EncodedKeySpec.getEncoded());
            fos.close();
        } catch (Exception e) {
            System.out.println("ERROR guardant clau privada");
        }
    }

}
