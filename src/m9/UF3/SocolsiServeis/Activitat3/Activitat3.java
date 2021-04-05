package m9.UF3.SocolsiServeis.Activitat3;

import java.net.*;
import java.io.*;
import java.util.*;

public class Activitat3 {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {

        String link = args[0];
        int numOfHead = Integer.valueOf(args[1]);
        String patro = args[2];
        
        try {
            String cadena;
            URL url = new URL(link);
            URLConnection connexio = url.openConnection();

            System.out.println("===============================================================");
            System.out.println("Adreça: " + connexio.getURL());
            System.out.println("===============================================================");
            System.out.println(numOfHead + " CAMPS DE CAPÇALERA AMB getHeaderFields(): ");
            
            System.out.println("===============================================================");
            System.out.println("Camps " + numOfHead + " de Capçalera");
            for (int j = 0; j < numOfHead; j++) {
                System.out.println("getHeaderField(" + j + ")=> " + connexio.getHeaderField(j));
            }
            System.out.println("===============================================================");

            System.out.println("Contingut del patro '" + patro + "' : " + url.getFile());
            BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((cadena = pagina.readLine()) != null) {

                if (cadena.contains(patro)) {
                    System.out.println(cadena);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
