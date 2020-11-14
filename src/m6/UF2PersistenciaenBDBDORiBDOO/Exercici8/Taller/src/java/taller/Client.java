/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author PC-Casa
 */
public class Client extends Persona{
    
    Date diaQueDeixaElCotxe;
    int numeroReparacions;
    String matriculaCotxe;

    public Client(Date diaQueDeixaElCotxe, int numeroReparacions, String matriculaCotxe, Long id, String dni, String nomComplet) {
        super(id, dni, nomComplet);
        this.diaQueDeixaElCotxe = diaQueDeixaElCotxe;
        this.numeroReparacions = numeroReparacions;
        this.matriculaCotxe = matriculaCotxe;
    }

    public Date getDiaQueDeixaElCotxe() {
        return diaQueDeixaElCotxe;
    }

    public void setDiaQueDeixaElCotxe(Date diaQueDeixaElCotxe) {
        this.diaQueDeixaElCotxe = diaQueDeixaElCotxe;
    }

    public int getNumeroReparacions() {
        return numeroReparacions;
    }

    public void setNumeroReparacions(int numeroReparacions) {
        this.numeroReparacions = numeroReparacions;
    }

    public String getMatriculaCotxe() {
        return matriculaCotxe;
    }

    public void setMatriculaCotxe(String matriculaCotxe) {
        this.matriculaCotxe = matriculaCotxe;
    }
    
    
    
}
