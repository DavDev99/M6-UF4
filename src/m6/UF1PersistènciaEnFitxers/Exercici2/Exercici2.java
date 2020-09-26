package m6.UF1PersistènciaEnFitxers.Exercici2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercici2 {
    
    public static ArrayList<Cotxe> recoveryCars() throws FileNotFoundException, IOException, ClassNotFoundException {
    	//
        ArrayList<Cotxe> garage = new ArrayList<>();
        //Declaració del fitxer
        File fitxer = new File("src/m6/UF1PersistènciaEnFitxers/Exercici2/cotxes.txt");
        
        if (fitxer.exists()) {
		
	        //Crea el flux d'entrada
	        FileInputStream filein = new FileInputStream(fitxer);
	        //Connectar el flux de bytes al flux de dades
	        ObjectInputStream dataInCotxes = new ObjectInputStream(filein);
	        Cotxe car = null;
	
	        try {
	            while (true){
	            	
	            	car = (Cotxe) dataInCotxes.readObject();
	            	
	            	garage.add(car);
	
	            }
	        } catch (EOFException eo) {}
	        dataInCotxes.close();//Tanca el stream d'entrada
        }
        return garage;
    }
    
    public static void saveCar(ArrayList<Cotxe> garage) throws FileNotFoundException, IOException{
        //Declaració del fitxer
        File fitxer = new File("src/m6/UF1PersistènciaEnFitxers/Exercici2/cotxes.txt");
        //Crea el flux de sortida
        FileOutputStream fileout = new FileOutputStream(fitxer, true);
        //Connectar el flux de bytes al flux de dades
        ObjectOutputStream dataOutCotxe = new ObjectOutputStream(fileout);

        // Guardem l'objecte
        for (int i = 0; i < garage.size(); i++) {
			
        	dataOutCotxe.writeObject(garage.get(i));
		}

        // Tanquem el fitxers
        dataOutCotxe.close();
        fileout.close();
    }


    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        Scanner teclado = new Scanner(System.in);
        int opcio = -1;
        int opcioBusqueda = 0;
        String marca;
        String matricula;
        String model;
        int any;
        
        ArrayList<Cotxe> garage = new ArrayList<Cotxe>();
                
        garage = recoveryCars();
        
        while(opcio != 0){
            System.out.println("Que vols fer? \n"
                    + "1. Crear un cotxe \n"
                    + "2. Buscar un cotxe per camp \n"
                    + "0. sortir \n"
                    + "");
        
            opcio = teclado.nextInt();
            teclado.nextLine();
        
            if (opcio == 1) {
                System.out.println("Quina marca es?");
                marca = teclado.nextLine();

                System.out.println("Quin model es?");
                model = teclado.nextLine();

                System.out.println("Quina matricula te?");
                matricula = teclado.nextLine();

                System.out.println("De quin any es?");
                any = teclado.nextInt();
                teclado.nextLine();
                
                Cotxe car = new Cotxe(marca, matricula, model, any);

                garage.add(car);

            }else if(opcio == 2){
                System.out.println("Opcio per la que vols buscar\n"
                        + "1. per marca,\n" 
                        + "2. per model,\n"
                        + "3. per matricula,\n"
                        + "4. per any,"
                );
                
                opcioBusqueda = teclado.nextInt();
                teclado.nextLine();
                
                if (opcioBusqueda == 1) {
                    System.out.println("Que marca busques?");
                    marca = teclado.nextLine();
                    
                    for (int i = 0; i < garage.size(); i++) {
                        if (garage.get(i).getMarca().equals(marca)) {
                            System.out.println(garage.get(i).toString());
                        }
                    }
                } else if (opcioBusqueda == 2) {
                    System.out.println("Que model busques?");
                    model = teclado.nextLine();

                    for (int i = 0; i < garage.size(); i++) {
                        if (garage.get(i).getModel().equals(model)) {
                            System.out.println(garage.get(i).toString());
                        }
                    }
                } else if (opcioBusqueda == 3) {
                    System.out.println("Que matricula busques?");
                    matricula = teclado.nextLine();

                    for (int i = 0; i < garage.size(); i++) {
                        if (garage.get(i).getMatricula().equals(matricula)) {
                            System.out.println(garage.get(i).toString());

                        }
                    }
                }else{
                    System.out.println("Que any busques?");
                    any = teclado.nextInt();
                    teclado.nextLine();
                    for (int i = 0; i < garage.size(); i++) {
                        if (garage.get(i).getAny() == any) {
                            System.out.println(garage.get(i).toString());
                        }
                    }
                }
            }

        }

            saveCar(garage);
            
    }
}
