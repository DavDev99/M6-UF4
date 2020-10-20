/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF1PersistenciaEnFitxers.Exercici6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Exercici6 {

    private static final String PELIS_XML_FILE = "src/m6/UF1PersistenciaEnFitxers/Exercici6/Pelis.xml";

    public static void main(String[] args) throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance(Pelis.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        // Cridem a la funcio per omplir XML
        Pelis pelis = omplePelis();

        //Mostrem el document XML generat por la sortida estandard
        marshaller.marshal(pelis, System.out);

        FileOutputStream fos = new FileOutputStream(PELIS_XML_FILE);
        //guardem l'objecte serializat en un document XML
        marshaller.marshal(pelis, fos);
        fos.close();

        Unmarshaller unmarshaller = context.createUnmarshaller();
        //Deserialitzem a partir de un document XML
        Pelis pelisAux = (Pelis) unmarshaller.unmarshal(new File(PELIS_XML_FILE));
        System.out.println("********* Pelis carregat desde fitxer XML***************");
        //Mostrem l'objeto Java obtingut
        marshaller.marshal(pelisAux, System.out);

    }

    // generem dades de prova
    private static Pelis omplePelis() {

        int[] idFilms = {1, 2, 3};
        int[] prioritats = {100, 200, 300};
        String[] titols = {"Heeey", "Siiiiiii", "Pos eso"};
        String[] situacions = {"--", "--", "--"};
        String[] any = {"1999", "1999", "1999"};
        Peli[] ArrayPelis = new Peli[3];

        for (int i = 0; i < 3; i++) {
            ArrayPelis[i] = new Peli();
            ArrayPelis[i].setIDFILM(idFilms[i]);
            ArrayPelis[i].setPRIORITAT(prioritats[i]);
            ArrayPelis[i].setTITOL(titols[i]);
            ArrayPelis[i].setSITUACIO(situacions[i]);
            ArrayPelis[i].setANY(any[i]);
        }
        
        // Creem objecte de pelis i li donem els seus fills
        Pelis pelis = new Pelis();
        pelis.setPelis(ArrayPelis);

        return pelis;
    }
}
