/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;

/**
 *
 * @author david
 */
public class Exercici4_noDual {

    public static void main(String[] args) throws IOException {

        Scanner teclado = new Scanner(System.in);
        String clau;
        String contrasenya;
        String nomFitxer;
        String cadena;

        String contingutEncriptat = null;

        System.out.println("Clau per a encriptar:");
        clau = teclado.nextLine();

        System.out.println("Contingut del fitxer:");
        contrasenya = teclado.nextLine();

        System.out.println("Nom del fitxer:");
        nomFitxer = teclado.nextLine();

        // Escriure fitxer sense encriptar
        FileWriter fitxerSenseEncriptar = new FileWriter("src/m9/UF1/SeguretatIcriptografia/Exercici4/" + nomFitxer + "_X.txt");
        fitxerSenseEncriptar.write(contrasenya + "\n");
        fitxerSenseEncriptar.close();

        // Generar clau secreta
        SecretKey sKey = passwordKeyGeneration(clau);

        // Llegir fitxer sense encriptar
        FileReader llegirFitxer = new FileReader("src/m9/UF1/SeguretatIcriptografia/Exercici4/" + nomFitxer + "_X.txt");
        BufferedReader llegir = new BufferedReader(llegirFitxer);

        while ((cadena = llegir.readLine()) != null) {

            // Encriptar
            contingutEncriptat = new String(encryptData(sKey, cadena.getBytes()));
        }

        // Escriure valors encriptats
        FileWriter escriureFitxerEncriptat = new FileWriter("src/m9/UF1/SeguretatIcriptografia/Exercici4/" + nomFitxer + "_Y.txt");
        escriureFitxerEncriptat.write(contingutEncriptat + "\n");
        escriureFitxerEncriptat.close();

        // Llegir fitxer encriptat
        FileReader FitxerEncriptat = new FileReader("src/m9/UF1/SeguretatIcriptografia/Exercici4/" + nomFitxer + "_Y.txt");
        BufferedReader llegirFitxerEncriptat = new BufferedReader(FitxerEncriptat);
        System.out.println("Fitxer encriptat:");

        while ((cadena = llegirFitxerEncriptat.readLine()) != null) {
            System.out.println(cadena);

        }

        //ARXIU DESENCRIPTAT
        FileReader f1 = new FileReader("src/m9/UF1/SeguretatIcriptografia/Exercici4/" + nomFitxer + "_Y.txt");
        BufferedReader b1 = new BufferedReader(f1);
        System.out.println("Fitxer desencriptat:");

        while ((cadena = b1.readLine()) != null) {

            // Desencriptar
                String dadesDelArixiuDesencriptat = new String(decryptData(sKey, cadena.getBytes()));
                System.out.println(dadesDelArixiuDesencriptat);

        }

    }

    // Encriptar
    public static byte[] encryptData(SecretKey sKey, byte[] data) {
        byte[] encryptedData = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            encryptedData = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return encryptedData;
    }

    // Desencriptar
    public static byte[] decryptData(SecretKey sKey, byte[] data) {
        byte[] encryptedData = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, sKey);
            encryptedData = cipher.doFinal(data);

        } catch (Exception ex) {
            System.err.println("Error desxifrant les dades: " + ex);
        }
        return encryptedData;
    }

    public static SecretKey passwordKeyGeneration(String text) {
        SecretKeySpec sKey = null;
        int keySize = 128;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {

                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize / 8);
                sKey = new SecretKeySpec(key, "AES");

            } catch (Exception ex) {
                System.err.println("Error al generar la clau." + ex);
            }
        }
        return sKey;
    }

}
