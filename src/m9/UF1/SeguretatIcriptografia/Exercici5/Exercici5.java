/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF1.SeguretatIcriptografia.Exercici5;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
        rutaClauPrivada = "/src/m9/UF1/SeguretatIcriptografia/Exercici5/clauPrivada.txt";
        rutaClauPublica = "/src/m9/UF1/SeguretatIcriptografia/Exercici5/clauPublica.txt";

        // Crear i guardar claus
        genKeyPair(512);
        guardarClauPrivada(rutaClauPrivada);
        guardarClauPublica(rutaClauPublica);
        
        // Encriptar
        
        texteEncriptat = Encrypt(textAEncriptar);
        System.out.println("Text encriptat:" + texteEncriptat );

    }

    public static void genKeyPair(int size) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

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
            out.write(getClauPrivadaString());
            out.close();
        } catch (Exception e) {

        }
    }

    public static void guardarClauPublica(String path) {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            out.write(getClauPublicaString());
            out.close();
        } catch (Exception e) {

        }
    }

    public static String getClauPrivadaString() {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(clauPrivada.getEncoded());
        return bytesToString(pkcs8EncodedKeySpec.getEncoded());
    }

    public static String getClauPublicaString() {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(clauPublica.getEncoded());
        return bytesToString(x509EncodedKeySpec.getEncoded());
    }

    public static String bytesToString(byte[] b) {
        byte[] b2 = new byte[b.length + 1];
        b2[0] = 1;
        System.arraycopy(b, 0, b2, 1, b.length);
        return new BigInteger(b2).toString(36);
    }
    
    public static String Encrypt(String plain) throws NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeyException,IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, UnsupportedEncodingException, NoSuchProviderException {

        byte[] encryptedBytes; 

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        encryptedBytes = cipher.doFinal(plain.getBytes());

        return bytesToString(encryptedBytes);

    }

}
