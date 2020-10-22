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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Exercici6 {

    private static final String PELIS_XML_FILE = "src/m6/UF1PersistenciaEnFitxers/Exercici6/Pelis.xml";
    
    Pelis pelis[] = new Pelis[3];

    public static void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, SAXException {

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
           
        crearObjectes();
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
        pelis.setPeli(ArrayPelis);

        return pelis;
    }
    
     //Generem objectes java
    private static void crearObjectes() throws ParserConfigurationException, SAXException, IOException {

        // Creo una instancia de DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Creo un documentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Obtengo el documento, a partir del XML
        Document documento = builder.parse(new File("src/m6/UF1PersistenciaEnFitxers/Exercici6/Pelis.xml"));

        Element nodeArrel = documento.getDocumentElement();
        NodeList childrens = nodeArrel.getChildNodes();
        
        String any = "";
        String situacio = "";
        String titol = "";
        int idfilm = 0;
        int prioritat = 0;
        Peli[] arrayPelis = new Peli[3];
        int contador = 0;
        
        // For de tots els fills de la arrel
        for (int i = 0; i < childrens.getLength(); i++) {

            // Si el fill es un film i te fills busquem per els fills
            if (childrens.item(i).hasChildNodes() && childrens.item(i).getNodeType() == Node.ELEMENT_NODE) {
                NodeList child = childrens.item(i).getChildNodes();
                
                // Recorrem el element peli
                for (int j = 0; j < child.getLength(); j++) {
                    
                    // Si es un node element guardem cada dada a la seva variable
                    if (child.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        
                        if (child.item(j).getNodeName().equals("ANY")) {
                            
                            any = child.item(j).getTextContent();
                            
                        } else if (child.item(j).getNodeName().equals("IDFILM")) {
                            
                            idfilm = Integer.parseInt(child.item(j).getTextContent());
                            
                        } else if (child.item(j).getNodeName().equals("PRIORITAT")) {
                            
                            prioritat = Integer.parseInt(child.item(j).getTextContent());
                            
                        } else if (child.item(j).getNodeName().equals("SITUACIO")) {
                            
                            situacio = child.item(j).getTextContent();
                            
                        } else if (child.item(j).getNodeName().equals("TITOL")){
                            
                            titol = child.item(j).getTextContent();
                        } 
                    }
                    //
                }
                Peli peli = new Peli(idfilm, prioritat, titol, situacio, any);
                arrayPelis[contador] = peli;
                contador++;
            }
        }
        
        Pelis pelis = new Pelis();
        pelis.setPeli(arrayPelis);
        
        Peli[] monstarObjectes = pelis.getPeli();
        
        System.out.println("*********** OBJECTES JAVA DESDE XML ***********");
        
        System.out.println(pelis.toString());
        
        for (int i = 0; i < monstarObjectes.length; i++) {
            System.out.println(monstarObjectes[i].toString());
        }

    }
}
