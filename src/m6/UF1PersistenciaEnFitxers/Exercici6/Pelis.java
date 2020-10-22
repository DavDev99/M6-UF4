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

    private Peli[] peli;

    public Pelis(Peli[] peli) {
        this.peli = peli;
    }

    public Pelis() {
    }

    public void setPeli(Peli[] pelis) {
        this.peli = pelis;
    }
    
    public Peli[] getPeli() {
        return peli;
    }

    public String toString() {
        return "Pelis{" + "peli=" + Arrays.toString(peli) + '}';
    }
    
    
}
