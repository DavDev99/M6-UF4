/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller;

/**
 *
 * @author PC-Casa
 */

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Vehicle {
    
    Long id;
    String model;
    String matricula;
    String problema;
    boolean estaArreglat;

    public Vehicle(Long id, String model, String matricula, String problema, boolean estaArreglat) {
        this.id = id;
        this.model = model;
        this.matricula = matricula;
        this.problema = problema;
        this.estaArreglat = estaArreglat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public boolean isEstaArreglat() {
        return estaArreglat;
    }

    public void setEstaArreglat(boolean estaArreglat) {
        this.estaArreglat = estaArreglat;
    }
    
    
    
}
