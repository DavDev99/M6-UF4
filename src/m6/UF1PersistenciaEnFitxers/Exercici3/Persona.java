/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF1PersistenciaEnFitxers.Exercici3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Persona {
	private String nom;
	private String cognom;
	private String segonCognom;
	private int anyNaixement;
	
	public Persona(String nom, String cognom, String segonCognom, int anyNaixement) {
		this.nom = nom;
		this.cognom = cognom;
		this.segonCognom = segonCognom;
		this.anyNaixement = anyNaixement;
	}

        @Override
        public String toString() {
            return "Persona{" + "nom=" + nom + ", cognom=" + cognom + ", segonCognom=" + segonCognom + ", anyNaixement=" + anyNaixement + '}';
        }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

        public String getCognom() {
            return cognom;
        }

        public void setCognom(String cognom) {
            this.cognom = cognom;
        }

        public String getSegonCognom() {
            return segonCognom;
        }

        public void setSegonCognom(String segonCognom) {
            this.segonCognom = segonCognom;
        }

	public int getAnyNaixement() {
		return anyNaixement;
	}

	public void setAnyNaixement(int anyNaixement) {
		this.anyNaixement = anyNaixement;
	}
	
	public void guardarFitxa(Persona persona) throws FileNotFoundException, IOException {
		File fitxer = new File("src/m6/UF1PersistenciaEnFitxers/Exercici3/fitxes.txt");
		//Crea un flux (stream) d'arxiu d'accés aleatori per llegir
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "rw");

		//Construeix un buffer (memòria intermèdia) de strings
		StringBuffer buffer = null;
			
			//25 caràcters a 2bytes/caràcter 50 bytes
			buffer = new StringBuffer (persona.getNom());
			buffer.setLength(25);
			aleatoriFile.writeChars(buffer.toString());
                        
                        
                        //25 caràcters a 2bytes/caràcter 50 bytes
			buffer = new StringBuffer (persona.getCognom());
			buffer.setLength(25);
			aleatoriFile.writeChars(buffer.toString());
                        
                        //25 caràcters a 2bytes/caràcter 50 bytes
			buffer = new StringBuffer (persona.getSegonCognom());
			buffer.setLength(25);
			aleatoriFile.writeChars(buffer.toString());
			
			//1 enter ocupa 4 bytes
			aleatoriFile.writeInt(persona.getAnyNaixement());
			
			//Total 79 bytes

		aleatoriFile.close();
	}
        
        public static ArrayList<Persona> llegirFitxes() throws FileNotFoundException, IOException{
            ArrayList<Persona> fitxes = new ArrayList<>();
            File fitxer = new File("src/m6/UF1PersistenciaEnFitxers/Exercici3/fitxes.txt");
            
                if(fitxer.exists()){
                    
		//Crea un flux (stream) d'arxiu d'accés aleatori només lectura
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");
		
		//Apuntador s'inicialitza apuntant a l'inici del fitxer
		int apuntador = 0;
                char aux;
                char[] nomPersona = new char[25];		
                char[] cognomPersona = new char[25];		
                char[] segonCognomPersona = new char[25];		
                int anyNaixementPersona = 0;
		//Recorrer el fitxer llibres
		for (;;) {
			aleatoriFile.seek(apuntador);//Apuntar a l'inici de cada llibre al fitxer

			//Llegeix nom
			for(int i = 0; i < nomPersona.length; i++) {
				aux = aleatoriFile.readChar();
				nomPersona[i] = aux;
			}
			String nom = new String(nomPersona);
                        
                        
			//Llegeix cognom
			for(int i = 0; i < cognomPersona.length; i++) {
				aux = aleatoriFile.readChar();
				cognomPersona[i] = aux;
			}
			String cognom = new String(cognomPersona);
                        
                        //Llegeix segon cognom
			for(int i = 0; i < segonCognomPersona.length; i++) {
				aux = aleatoriFile.readChar();
				segonCognomPersona[i] = aux;
			}
			String segonCognom = new String(segonCognomPersona);
                        
			//Llegeix any
			anyNaixementPersona = aleatoriFile.readInt();
                        
			//Sortida de les dades de cada llibre
			//System.out.println("ID: "+id+"\nTítol: "+titols+"\nISBN: "+isbn+"\nAutor: "+autors+"\nEditrorial: 							"+editorials+"\nPreu: "+preu+"€\n\n");
                        
                        //Creem objecte i guardem a la fitxa
                        Persona fitxa = new Persona(nom, cognom, segonCognom, anyNaixementPersona);
                        
                        fitxes.add(fitxa);
                        //S'ha de posicionar l'apuntador a la seguent fitxa
			apuntador += 79;
			//Si coincideix on s'està apuntat amb el final del fitxer, sortim
			if(aleatoriFile.getFilePointer()==aleatoriFile.length()) break;
		}
		aleatoriFile.close();//Tancar el fitxer
            }

                return fitxes;
        }
	
	

}
