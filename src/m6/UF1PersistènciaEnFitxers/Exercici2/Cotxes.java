/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF1PersistènciaEnFitxers.Exercici2;

import java.io.Serializable;

public class Cotxes implements Serializable {

    //Implementa la interfície Serializable

    private String marca;
    private String matricula;
    private String model;
    private int any;

    //constructor amb paràmetres
    public Cotxes(String marca, String matricula, String model ,int any) {
        //per no confondre el paràmetre amb el camp de variable
        this.marca = marca;
        this.matricula = matricula;
        this.model = model;
        this.any = any;

    }

    
    
    // getters i setters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }
    


}