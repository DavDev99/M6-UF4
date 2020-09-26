package m6.UF1PersistènciaEnFitxers.Exercici1;
import java.io.*;

public class VeureInfo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	File path = new File(args[0]);
    	
    	if (path.exists()) {
    		
    		if (path.isFile()) {
    			
    			if (path.isHidden()) {
					System.out.println("ES UN FITXER OCULT");
				}
    			
    			 System.out.println("INFORMACIÓ SOBRE EL FITXER");

    				 System.out.println("Nom del fitxer : "+path.getName());
    				 System.out.println("Ruta           : "+path.getPath());
    				 System.out.println("Ruta absoluta  : "+path.getAbsolutePath());
    				 System.out.println("Es pot escriure: "+path.canRead());
    				 System.out.println("Es pot llegir  : "+path.canWrite());
    				 System.out.println("Grandaria      : "+path.length());
    				 System.out.println("Es un directori: "+path.isDirectory());
    				 System.out.println("Es un fitxer   : "+path.isFile());

			} else if(path.isDirectory()) {
	            System.out.println("Fitxers al directori: ");	
	            
	            // Usem el metpde list per a guardar els noms dels arxius a un array
	            String[] arxius = path.list();
	            
	            // Treiem per pantalla els noms dels arxius
	            for (int i = 0; i<arxius.length; i++){
	    			if (arxius[i].substring(0,1).equals(".")) {
						System.out.println("ES UN FITXER OCULT");
					}
	                    System.out.println(arxius[i]);
	            }
			}
    		

		}else {
			System.out.println("Ruta no valida");
		}
    	

    }
    
}
