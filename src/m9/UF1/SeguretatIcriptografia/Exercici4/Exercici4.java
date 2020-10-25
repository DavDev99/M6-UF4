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
        int menu = -1;

        
        while(menu != 0){
            System.out.println("Escriu el numero segons el que vulguis fer: ");            
            System.out.println("1. Encriptar contrasenya");
            System.out.println("2. Desencriptar contrasenya");            
            System.out.println("0. Sortir");

            
            menu = teclado.nextInt();
            teclado.nextLine();
            
            if (menu == 1) {
                System.out.println("Clau per a encriptar:");
                clau = teclado.next();

                System.out.println("Texte a encriptar:");
                contrasenya = teclado.next();
            }

        }
        
      
    }

}
