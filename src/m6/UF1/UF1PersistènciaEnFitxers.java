package m6.UF1;
import java.io.*;

public class UF1PersistènciaEnFitxers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Fitxers al directori actual: ");
        
        // Creem l'objecte de tipus File per a obtindre tots els arxius de la carpeta on se executa el programa
        File f = new File(".");
        
        // Usem el metpde list per a guardar els noms dels arxius a un array
        String[] arxius = f.list();
        
        // Treiem per pantalla els noms dels arxius
        for (int i = 0; i<arxius.length; i++){
                System.out.println(arxius[i]);
        }
    }
    
}
