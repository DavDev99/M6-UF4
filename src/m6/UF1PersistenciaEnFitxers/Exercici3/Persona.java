/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF1PersistenciaEnFitxers.Exercici3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getApellit() {
		return cognom;
	}

	public void setApellit(String apellit) {
		this.cognom = apellit;
	}

	public String getSegonApellit() {
		return segonCognom;
	}

	public void setSegonApellit(String segonApellit) {
		this.segonCognom = segonApellit;
	}

	public int getAnyNaixement() {
		return anyNaixement;
	}

	public void setAnyNaixement(int anyNaixement) {
		this.anyNaixement = anyNaixement;
	}
	
	public void guardarFitxa(Persona persona) throws FileNotFoundException {
		File fitxer = new File("src/m6/UF1PersistènciaEnFitxers/Exercici3/fitxes.txt");
		//Crea un flux (stream) d'arxiu d'accés aleatori per llegir
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "rw");

		//Construeix un buffer (memòria intermèdia) de strings
		StringBuffer buffer = null;
		
			//1 enter ocupa 4 bytes
			aleatoriFile.writeInt(i+1);
			
			//50 caràcters a 2bytes/caràcter 100 bytes
			buffer = new StringBuffer (persona.getNom());
			buffer.setLength(50);
			aleatoriFile.writeChars(buffer.toString());
			
			//1 enter ocupa 4 bytes
			aleatoriFile.writeInt(isbn[i]);
			
			//25 caràcters a 2bytes/caràcter 50 bytes
			buffer = new StringBuffer (autor[i]);
			buffer.setLength(25);
			aleatoriFile.writeChars(buffer.toString());
			
			//30 caràcters a 2bytes/caràcter 60 bytes
			buffer = new StringBuffer (editorial[i]);
			buffer.setLength(30);
			aleatoriFile.writeChars(buffer.toString());
			
			//1 float ocupa 4 bytes
			aleatoriFile.writeFloat((float)preu[i]);
			
			//Total 222 bytes

		aleatoriFile.close();
	}
	
	

}
