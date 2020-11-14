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
    @Entity
public class Client extends Persona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    Long id;
    
    Date diaQueDeixaElCotxe;
    String matriculaCotxe;
    

    public Client(String matriculaCotxe,String dni, String nomComplet) {
        super(dni, nomComplet);
        this.diaQueDeixaElCotxe = new Date(System.currentTimeMillis());
        this.matriculaCotxe = matriculaCotxe;
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", diaQueDeixaElCotxe=" + diaQueDeixaElCotxe + ", matriculaCotxe=" + matriculaCotxe + '}';
    }

    
    
}
