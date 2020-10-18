/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF1PersistenciaEnFitxers.Exercici5;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author PC-Casa
 */
public class Exercici5_2 {

    public static void crearElement(Document documento, Scanner teclado, Element nodeArrel) {
        // Nou element
        Element elemento = documento.createElement("FILM");

        nodeArrel.appendChild(elemento);

        // Li donem valor als nodes 
        Element idFilm = documento.createElement("IDFILM");
        System.out.println("Introdueix el valor de IDFILM");
        idFilm.setTextContent(teclado.nextLine());
        elemento.appendChild(idFilm);

        Element prioritat = documento.createElement("PRIORITAT");
        System.out.println("Introdueix el valor de PRIORITAT");
        prioritat.setTextContent(teclado.nextLine());
        elemento.appendChild(prioritat);

        Element titol = documento.createElement("TITOL");
        System.out.println("Introdueix el valor de TITOL");
        titol.setTextContent(teclado.nextLine());
        elemento.appendChild(titol);

        Element situacio = documento.createElement("SITUACIO");
        System.out.println("Introdueix el valor de SITUACIO");
        situacio.setTextContent(teclado.nextLine());
        elemento.appendChild(situacio);
    }

    private static void modificarElement(Document documento, Scanner teclado) {
        // Demanem la id
        System.out.println("Introdueix que el ID del node que vols modificar: ");
        String id = teclado.nextLine();

        // Busquem per la id
        Element elemento = buscarId(documento, id);

        // Elegim el atribut o posem un de nou
        System.out.println("Introdueix el nom del atribut que vols modificar o crear: ");
        String attr = teclado.nextLine();

        // Dades del atribut i asignarlo
        Attr newAttr = documento.createAttribute(attr);

        System.out.println("Introdueix el valor del atribut");
        newAttr.setValue(teclado.nextLine());
        elemento.setAttributeNode(newAttr);

    }

    private static void eliminarElement(Document documento, Scanner teclado, Element nodeArrel) {
        // Demanem la id
        System.out.println("Introdueix que el ID del node que vols modificar: ");
        String id = teclado.nextLine();

        // Busquem per la id
        Element elemento = buscarId(documento, id);
        
        nodeArrel.removeChild(elemento);

    }

    // Metode per a buscar per id
    private static Element buscarId(Document documento, String id) {
        Element nodeArrel = documento.getDocumentElement();
        NodeList childrens = nodeArrel.getChildNodes();
        
        // For de tots els fills de la arrel
        for (int i = 0; i < childrens.getLength(); i++) {
            
            // Si el fill es un film i te fills busquem per els fills
            if (childrens.item(i).hasChildNodes() && childrens.item(i).getNodeName().equals("FILM")) {
                NodeList child = childrens.item(i).getChildNodes();
                
                
                for (int j = 0; j < child.getLength(); j++) {
                    // Si el fill se diu IDFILM i te el mateix valor que la id es el que busquem
                    if (child.item(j).getNodeName().equals("IDFILM") && child.item(j).getTextContent().equals(id)) {
                        return (Element) childrens.item(i);
                    }
                }
            }
        }
        
        return nodeArrel;
    }

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {

        // Creo una instancia de DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Creo un documentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Obtengo el documento, a partir del XML
        Document documento = builder.parse(new File("src/m6/UF1PersistenciaEnFitxers/Exercici5/pelis.xml"));

        int opcio = -1;
        Scanner teclado = new Scanner(System.in);
        Element nodeArrel = documento.getDocumentElement();

        //buscarId(nodeArrel);
        while (opcio != 0) {
            System.out.println("Que vols fer? \n"
                    + "1. Crear un node \n"
                    + "2. Modificar un node \n"
                    + "3. Eliminar un node \n"
                    + "0. sortir \n");

            opcio = teclado.nextInt();
            teclado.nextLine();

            if (opcio == 1) {

                crearElement(documento, teclado, nodeArrel);
            } else if (opcio == 2) {

                modificarElement(documento, teclado);
            } else if (opcio == 3) {

                eliminarElement(documento, teclado, nodeArrel);
            }
        }
        
        
        // Quan tanquem el programa es guarden els canvis
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(new File("src/m6/UF1PersistenciaEnFitxers/Exercici5/pelis.xml"));

        transformer.transform(source, result);
    }

}
