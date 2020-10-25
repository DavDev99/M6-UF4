package m9.UF1.SeguretatIcriptografia.Exercici4;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author david
 */
public class Exercici4 {
    //VARIABLES	

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        String clau;
        String contrasenya;

        
        System.out.println("Clau per a encriptar:");
        clau = teclado.nextLine();

        System.out.println("Texte a encriptar:");
        contrasenya = teclado.nextLine();

        SecretKey sKey = passwordKeyGeneration(clau);

     

        //ECB
        byte[] contrasenyaBytes = contrasenya.getBytes();
        byte[] contrasenyaEncriptadaBytes = encryptData(sKey, contrasenyaBytes);

        // Mostrar valor

        String contrasenyaEncriptada = new String(contrasenyaEncriptadaBytes);
        System.out.println("Contrasenya encriptada:" + contrasenyaEncriptada);

    }

    //ENCRIPTAR
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

    //AES
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
