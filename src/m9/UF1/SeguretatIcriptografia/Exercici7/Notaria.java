/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici7;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author PC-Casa
 */
public class Notaria {

    public static void main(String[] args) throws Exception {
        
        System.out.print("Comprovant signatura de l’arxiu missatge...");
        
        // Guardem la clau publica a una variable
        byte[] keyBytes = Files.readAllBytes(Paths.get("clauPublica"));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey clauPublica = kf.generatePublic(spec);

        // Agafem el missatge i la firma i les guardem a una variable
        byte[] missatge = Files.readAllBytes(Paths.get("missatge"));

        byte[] signatura = Files.readAllBytes(Paths.get("firma_missatge"));
        
        // Comprobem si el missatge s'ha modificat o no segorns la firma
        boolean validacio = false;

        java.security.Signature signer = java.security.Signature.getInstance("SHA256withRSA");
        signer.initVerify(clauPublica);
        signer.update(missatge);
        validacio = signer.verify(signatura);
        
        // Mostrem el resultat

        if (validacio) {
            System.out.print("OK");
        } else {
            System.out.print("ERROR");
        }

    }



}
