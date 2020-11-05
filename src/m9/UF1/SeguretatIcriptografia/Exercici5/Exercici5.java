/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Alumne
 */
public class Exercici5 {

    // Variables de clase
    static PrivateKey clauPrivada;
    static PublicKey clauPublica;

    public static void main(String[] args) throws Exception {
        // Variables
        Scanner teclado = new Scanner(System.in);
        String textAEncriptar;
        String desxifrat;
        String texteEncriptat;
        String rutaClauPrivada;
        String rutaClauPublica;

        // Recollir dades
        System.out.println("Text a encriptar:");
        textAEncriptar = teclado.next();
        rutaClauPrivada = "src/m9/UF1/SeguretatIcriptografia/Exercici5/clauPrivada.txt";
        rutaClauPublica = "src/m9/UF1/SeguretatIcriptografia/Exercici5/clauPublica.txt";

        // Crear i guardar claus
        generarLesClaus(512);
        guardarClauPrivada(rutaClauPrivada);
        guardarClauPublica(rutaClauPublica);

        // Encriptar
        texteEncriptat = encriptar(textAEncriptar);
        System.out.println("Text encriptat:" + texteEncriptat);

        // Desencriptar
        recullorFitxerDeLaClauPrivada(rutaClauPrivada);
        recullorFitxerDeLaClauPublica(rutaClauPublica);

        desxifrat = desencriptar(texteEncriptat);
        System.out.println("Desxifrat:" + desxifrat);

    }

    public static void generarLesClaus(int size) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(size);
        KeyPair kp = kpg.genKeyPair();

        PublicKey publicKey2 = kp.getPublic();
        PrivateKey privateKey2 = kp.getPrivate();

        clauPrivada = privateKey2;
        clauPublica = publicKey2;
    }

    public static void guardarClauPrivada(String path) throws IOException {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            out.write(clauPrivadaEnString());
            out.close();
        } catch (Exception e) {

        }
    }

    public static void guardarClauPublica(String path) {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            out.write(clauPublicaEnString());
            out.close();
        } catch (Exception e) {

        }
    }

    public static String clauPrivadaEnString() {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(clauPrivada.getEncoded());
        return deBytesAString(pkcs8EncodedKeySpec.getEncoded());
    }

    public static String clauPublicaEnString() {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(clauPublica.getEncoded());
        return deBytesAString(x509EncodedKeySpec.getEncoded());
    }

    public static String deBytesAString(byte[] b) {
        byte[] b2 = new byte[b.length + 1];
        b2[0] = 1;
        System.arraycopy(b, 0, b2, 1, b.length);
        return new BigInteger(b2).toString(36);
    }

    public static String encriptar(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, UnsupportedEncodingException, NoSuchProviderException {

        byte[] encryptedBytes;

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        encryptedBytes = cipher.doFinal(plain.getBytes());

        return deBytesAString(encryptedBytes);

    }
    
    public static String desencriptar(String result) throws NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] decryptedBytes;

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        decryptedBytes = cipher.doFinal(stringToBytes(result));
        return new String(decryptedBytes);
    }


    public static void recullorFitxerDeLaClauPublica(String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String content = readFileAsString(path);
        setClauPublicaString(content);
    }

    public static void recullorFitxerDeLaClauPrivada(String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String content = readFileAsString(path);
        setClauPrivadaString(content);
    }

    private static String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    public static byte[] stringToBytes(String s) {
        byte[] b2 = new BigInteger(s, 36).toByteArray();
        return Arrays.copyOfRange(b2, 1, b2.length);
    }

    public static void setClauPrivadaString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] encodedPrivateKey = stringToBytes(key);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        clauPrivada = privateKey;
    }

    public static void setClauPublicaString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] encodedPublicKey = stringToBytes(key);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        clauPublica = publicKey;
    }

}
