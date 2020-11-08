/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici6;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author david
 */
public class Desencriptacio {

    private static SecretKeySpec secretKey;

    public static void main(String[] args) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchPaddingException {

        // Llegim la clau privada
        byte[] keyBytes = Files.readAllBytes(Paths.get("src/m9/UF1/SeguretatIcriptografia/Exercici6/clauPrivada.txt"));

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey clauPrivada = kf.generatePrivate(spec);

        // Recollim la clau i la desencriptem
        byte[] clauEncriptada = Files.readAllBytes(Paths.get("src/m9/UF1/SeguretatIcriptografia/Exercici6/ZZZ_clau_encriptada.txt"));
        String clauDesencriptada = new String(desencriptarClau(clauEncriptada, clauPrivada));

        // Llegim el missatge encriptat
        byte[] missatgeEncriptat = Files.readAllBytes(Paths.get("src/m9/UF1/SeguretatIcriptografia/Exercici6/ZZZ_missatge_encriptat.txt"));
        
        // Desencriptem el missatge
        String missatge = new String(decrypt(missatgeEncriptat, clauDesencriptada));
        System.out.println(missatge);

    }

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            byte[] key = myKey.getBytes("UTF-8");
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

    public static String decrypt(byte[] strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static String desencriptarClau(byte[] clauEncriptada, PrivateKey clauPrivada) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada, cipher.getParameters());
        byte[] plainBytesDecrypted = cipher.doFinal(clauEncriptada);
        return new String(plainBytesDecrypted, "UTF8");
    }

}
