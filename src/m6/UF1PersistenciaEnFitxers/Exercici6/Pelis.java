/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF1PersistenciaEnFitxers.Exercici6;

/**
 *
 * @author Alumne
 */
import java.util.Arrays;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement

public class Pelis {

    private Peli[] pelis;

    public Pelis(Peli[] peli) {
        this.pelis = peli;
    }

    public Pelis() {
    }

    public void setPeli(Peli[] pelis) {
        this.pelis = pelis;
    }
    
    public Peli[] getPeli() {
        return pelis;
    }

    public String toString() {
        return "Pelis{" + "peli=" + Arrays.toString(pelis) + '}';
    }
    
    
}
