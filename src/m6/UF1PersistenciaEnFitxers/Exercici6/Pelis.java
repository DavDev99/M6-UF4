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
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pelis {

    private Peli[] pelis;

    public Peli[] getPelis() {
        return pelis;
    }

    public void setPelis(Peli[] pelis) {
        this.pelis = pelis;
    }



}
