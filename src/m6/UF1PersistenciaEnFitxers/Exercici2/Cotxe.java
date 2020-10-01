
package m6.UF1PersistenciaEnFitxers.Exercici2;
import java.io.Serializable;

public class Cotxe implements Serializable {

    //Implementa la interfície Serializable

    private String marca;
    private String matricula;
    private String model;
    private int any;

    //constructor amb parametres
    public Cotxe(String marca, String matricula, String model ,int any) {
        //per no confondre el parametre amb el camp de variable
        this.marca = marca;
        this.matricula = matricula;
        this.model = model;
        this.any = any;
    }
    
    // toString per a mostrar les dades de cada cotxe
    public String toString() {
        return "Cotxe{" + "marca=" + marca + ", matricula=" + matricula + ", model=" + model + ", any=" + any + '}';
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