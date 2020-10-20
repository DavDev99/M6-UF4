/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF1PersistenciaEnFitxers.Exercici6;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
@XmlRootElement(name = "FILM")

//@XmlAccessorType(XmlAccessType.FIELD) // This line was added
class Peli {

    private int IDFILM;
    private int PRIORITAT;
    private String TITOL;
    private String SITUACIO;
    private String ANY;

    //@XmlElement(name="IDFILM")        
    public int getIDFILM() {
        return IDFILM;
    }

    public void setIDFILM(int IDFILM) {
        this.IDFILM = IDFILM;
    }
 //@XmlElement(name="PRIORITAT")
    public int getPRIORITAT() {
        return PRIORITAT;
    }

    public void setPRIORITAT(int PRIORITAT) {
        this.PRIORITAT = PRIORITAT;
    }
 //@XmlElement(name="TITOL")
    public String getTITOL() {
        return TITOL;
    }

    public void setTITOL(String TITOL) {
        this.TITOL = TITOL;
    }
 //@XmlElement(name="SITUACIO")
    public String getSITUACIO() {
        return SITUACIO;
    }

    public void setSITUACIO(String SITUACIO) {
        this.SITUACIO = SITUACIO;
    }
 //@XmlElement(name="ANY")
    public String getANY() {
        return ANY;
    }

    public void setANY(String ANY) {
        this.ANY = ANY;
    }

}
