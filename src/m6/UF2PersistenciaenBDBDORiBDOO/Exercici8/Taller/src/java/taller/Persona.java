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



public class Persona{
    String dni;
    String nomComplet;

    public Persona() {
    }

    public Persona(String dni, String nomComplet) {
        this.dni = dni;
        this.nomComplet = nomComplet;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    
    
}
