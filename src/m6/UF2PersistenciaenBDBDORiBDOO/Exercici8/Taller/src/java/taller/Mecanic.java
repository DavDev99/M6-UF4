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

public class Mecanic extends Persona{
    
    Date diaContractat;
    int cotxesArreglats;

    public Mecanic(Date diaContractat, int cotxesArreglats, Long id, String dni, String nomComplet) {
        super(id, dni, nomComplet);
        this.diaContractat = diaContractat;
        this.cotxesArreglats = cotxesArreglats;
    }

    public Date getDiaContractat() {
        return diaContractat;
    }

    public void setDiaContractat(Date diaContractat) {
        this.diaContractat = diaContractat;
    }

    public int getCotxesArreglats() {
        return cotxesArreglats;
    }

    public void setCotxesArreglats(int cotxesArreglats) {
        this.cotxesArreglats = cotxesArreglats;
    }
    
    
}
