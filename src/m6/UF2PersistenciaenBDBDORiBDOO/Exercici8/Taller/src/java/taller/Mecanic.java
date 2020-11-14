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

@Entity
public class Mecanic extends Persona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    Long id;
    Date diaContractat;
    int cotxesArreglats;

    public Mecanic(int cotxesArreglats, String dni, String nomComplet) {
        super(dni, nomComplet);
        this.diaContractat = new Date(System.currentTimeMillis());
        this.cotxesArreglats = cotxesArreglats;
    }

    public Mecanic() {
    }

    @Override
    public String toString() {
        return "Mecanic{" + "id=" + id + ", diaContractat=" + diaContractat + ", cotxesArreglats=" + cotxesArreglats + '}';
    }

    
    
}
