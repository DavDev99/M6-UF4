/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author PC-Casa
 */

@Entity
public class Vehicle implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    Long id;
    String model;
    String matricula;
    String problema;
    boolean estaArreglat;

    public Vehicle(String model, String matricula, String problema, boolean estaArreglat) {
        this.model = model;
        this.matricula = matricula;
        this.problema = problema;
        this.estaArreglat = estaArreglat;
    }

    public Vehicle() {
    }


    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + ", model=" + model + ", matricula=" + matricula + ", problema=" + problema + ", estaArreglat=" + estaArreglat + '}';
    }


    
}
