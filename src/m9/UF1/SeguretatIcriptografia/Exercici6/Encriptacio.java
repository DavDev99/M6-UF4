/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici6;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author david
 */
public class Encriptacio {

    private static SecretKeySpec secretKey;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Scanner teclado = new Scanner(System.in);

        // Nom de la clau publica
        System.out.println("Introdueix el nom de la clau publica");
        String nomFitxer = "src/m9/UF1/SeguretatIcriptografia/Exercici6/" + teclado.nextLine();
        File clauPublicaFitxer = new File(nomFitxer);

        // Si la clau exixsteix
        if (clauPublicaFitxer.exists()) {
            // Llegim els bytes
            byte[] keyBytes = Files.readAllBytes(Paths.get(nomFitxer));

            // guardem la clau publica a una variable
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey clauPublica = kf.generatePublic(spec);

            // Encriptem la clau apartir de una contrasenya
            final String secretKey = "increibleContrasenya";
            byte[] clauEncriptada = encriptarClauPublicaAmbContrasenya(clauPublica, secretKey);

            // Guardem la clau encriptada
            guardarADocuments("ZZZ_clau_encriptada.txt", clauEncriptada);

            // Guardem la frase que volem encriptar
            System.out.println("Introdueix la frase a encriptar");
            String texteAEncriptar = teclado.nextLine();

            byte[] texteEncriptat = encrypt(texteAEncriptar, secretKey);
            guardarADocuments("ZZZ_missatge_encriptat.txt", texteEncriptat);

        }

    }

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            byte[] key;
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(String strToEncrypt, String contrasenya) {
        try {
            // Transformem la contrasenya super secreta a una SecretKeySpec
            setKey(contrasenya);

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encode(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static byte[] encriptarClauPublicaAmbContrasenya(PublicKey clauPublica, String contrasenya) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);

        // Posem la contrasenya al cipher
        byte[] input = contrasenya.getBytes();
        cipher.update(input);

        // Encriptem el text i el retornem
        byte[] cipherText = cipher.doFinal();
        return cipherText;
    }

    public static void guardarADocuments(String nomFitxer, byte[] clauEncriptada) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("src/m9/UF1/SeguretatIcriptografia/Exercici6/" + nomFitxer));
            dos.write(clauEncriptada);
            dos.flush();
            dos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
