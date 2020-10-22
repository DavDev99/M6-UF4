/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF1PersistenciaEnFitxers.Exercici6;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Peli")
        
class Peli {

    private int IDFILM;
    private int PRIORITAT;
    private String TITOL;
    private String SITUACIO;
    private String ANY;
    
    public Peli(int IDFILM, int PRIORITAT, String TITOL, String SITUACIO, String ANY) {
        this.IDFILM = IDFILM;
        this.PRIORITAT = PRIORITAT;
        this.TITOL = TITOL;
        this.SITUACIO = SITUACIO;
        this.ANY = ANY;
    }

    public Peli() {
    }
    
    @XmlElement(name="IDFILM")        
    public int getIDFILM() {
        return IDFILM;
    }

    public void setIDFILM(int IDFILM) {
        this.IDFILM = IDFILM;
    }
    
    @XmlElement(name="PRIORITAT")
    public int getPRIORITAT() {
        return PRIORITAT;
    }

    public void setPRIORITAT(int PRIORITAT) {
        this.PRIORITAT = PRIORITAT;
    }
    
    @XmlElement(name="TITOL")
    public String getTITOL() {
        return TITOL;
    }

    public void setTITOL(String TITOL) {
        this.TITOL = TITOL;
    }
    
    @XmlElement(name="SITUACIO")
    public String getSITUACIO() {
        return SITUACIO;
    }

    public void setSITUACIO(String SITUACIO) {
        this.SITUACIO = SITUACIO;
    }
    
    @XmlElement(name="ANY")
    public String getANY() {
        return ANY;
    }

    public void setANY(String ANY) {
        this.ANY = ANY;
    }

    @Override
    public String toString() {
        return "Peli{" + "IDFILM=" + IDFILM + ", PRIORITAT=" + PRIORITAT + ", TITOL=" + TITOL + ", SITUACIO=" + SITUACIO + ", ANY=" + ANY + '}';
    }
    
    

}
