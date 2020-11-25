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
        Scanner sc = new Scanner(System.in);
        FileInputStream fis;
        fis = new FileInputStream("src/m9/UF1/SeguretatIcriptografia/Exercici8/clausdavidllop");
        String contrasenya = "123456", aliasllavevuelta, llaveaexportar;
        char[] arrayContrasenya = contrasenya.toCharArray();
        byte[] arrayEncoded;

        //Agafem el key store
        ks.load(fis, arrayContrasenya);

        //Creem una enumeració de string que conté els noms dels alias
        Enumeration<String> aliasllaves = ks.aliases();

        while (aliasllaves.hasMoreElements()) {

            aliasllavevuelta = aliasllaves.nextElement();
            System.out.print("Entry name: " + aliasllavevuelta + "\t\t" + "Algorithm: " + ks.getKey(aliasllavevuelta, arrayContrasenya).getAlgorithm() + "\t\t");

            arrayEncoded = ks.getKey(aliasllavevuelta, arrayContrasenya).getEncoded();
            System.out.print("Key Size: " + arrayEncoded.length + " bytes \t\t");

            if (ks.getCertificateChain(aliasllavevuelta) == null) {
                System.out.print("Certificat Expiry: No te certificat");
            } else {
                System.out.print("Certificat Expiry: " + ((X509Certificate) ks.getCertificate(aliasllavevuelta)).getNotAfter());
            }

            System.out.print("\t\tLast Modified: "
                    + ks.getCreationDate(aliasllavevuelta));

            System.out.println("");
        }

    }

}
