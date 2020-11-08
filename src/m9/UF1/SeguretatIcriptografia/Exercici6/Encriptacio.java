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
    private static byte[] key;

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

       

        }

    }

  
}
