/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.Scanner;

/**
 *
 * @author ianlo
 */
public class Exercici8 {

    /**
     * @param args the command line arguments
     * @throws java.security.KeyStoreException
     * @throws java.io.FileNotFoundException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.cert.CertificateException
     */
    public static void main(String[] args) throws KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {

        // Variables
        KeyStore ks = KeyStore.getInstance("JCEKS");
        Scanner telcado = new Scanner(System.in);
        FileInputStream fis;
        fis = new FileInputStream("src/m9/UF1/SeguretatIcriptografia/Exercici8/clausdavidllop");
        String contrasenya = "ladesiempre", aliasClau, clauAExporta;
        char[] arrayContrasenya = contrasenya.toCharArray();
        byte[] arrayEncoded;
        int llaveALlevarse = 0;
        Key llaveexportar;

        //Agafem el key store
        ks.load(fis, arrayContrasenya);

        //Creem una enumeració de string que conté els noms dels alias
        Enumeration<String> aliasllaves = ks.aliases();

        while (aliasllaves.hasMoreElements()) {

            // Mostrem les claus
            aliasClau = aliasllaves.nextElement();
            System.out.print("Entry name: " + aliasClau + "\t\t" + "Algorithm: " + ks.getKey(aliasClau, arrayContrasenya).getAlgorithm() + "\t\t");

            arrayEncoded = ks.getKey(aliasClau, arrayContrasenya).getEncoded();
            System.out.print("Key Size: " + arrayEncoded.length + " bytes \t\t");

            if (ks.getCertificateChain(aliasClau) == null) {
                System.out.print("Certificat Expiry: No te certificat");
            } else {
                System.out.print("Certificat Expiry: " + ((X509Certificate) ks.getCertificate(aliasClau)).getNotAfter());
            }

            System.out.println("\t\tLast Modified: " + ks.getCreationDate(aliasClau));

        }

        System.out.println("Quina clau vols exportar?");
        Enumeration<String> aliasllaves2 = ks.aliases();
        clauAExporta = telcado.nextLine();

        while (aliasllaves2.hasMoreElements() && llaveALlevarse == 0) {

            aliasClau = aliasllaves2.nextElement();
            // Si el nom del alias es igual exportem el certificat
            if (aliasClau.equals(clauAExporta.trim())) {

                llaveexportar = ks.getKey(aliasClau, arrayContrasenya);
                X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(llaveexportar.getEncoded());

                FileOutputStream fos = new FileOutputStream("src/m9/UF1/SeguretatIcriptografia/Exercici8/" + aliasClau + "_clau.cer");
                System.out.println("Certificat exportat");

            }
        }
    }

}
