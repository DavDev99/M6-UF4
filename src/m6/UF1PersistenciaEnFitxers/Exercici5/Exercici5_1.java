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

public class Exercici5_1 {

    public static void crearElement(Document documento, Scanner teclado, Element nodeArrel) {
        // Nou element
        Element elemento = documento.createElement("row");

        nodeArrel.appendChild(elemento);

        // Li donem valor als attributs
        Attr attrId = documento.createAttribute("_id");
        System.out.println("Introdueix el atribut id");
        attrId.setValue(teclado.nextLine());
        elemento.setAttributeNode(attrId);
        elemento.setIdAttribute("_id", true);

        Attr attrUuid = documento.createAttribute("_uuid");
        System.out.println("Introdueix el atribut uuid");
        attrUuid.setValue(teclado.nextLine());
        elemento.setAttributeNode(attrUuid);

        Attr attrPosition = documento.createAttribute("_position");
        System.out.println("Introdueix el atribut position");
        attrPosition.setValue(teclado.nextLine());
        elemento.setAttributeNode(attrPosition);

        Attr attrAddress = documento.createAttribute("_address");
        System.out.println("Introdueix el atribut address");
        attrAddress.setValue(teclado.nextLine());
        elemento.setAttributeNode(attrAddress);

        // Li donem valor als nodes 
        Element numRegistre = documento.createElement("num_registre");
        System.out.println("Introdueix el valor de num_registres");
        numRegistre.setTextContent(teclado.nextLine());
        elemento.appendChild(numRegistre);

        Element codiPais = documento.createElement("codi_pa_s");
        System.out.println("Introdueix el valor de codi_pa_s");
        codiPais.setTextContent(teclado.nextLine());
        elemento.appendChild(codiPais);

        Element pais = documento.createElement("pa_s");
        System.out.println("Introdueix el valor de pa_s");
        pais.setTextContent(teclado.nextLine());
        elemento.appendChild(pais);

        Element rea = documento.createElement("rea");
        System.out.println("Introdueix el valor de rea");
        rea.setTextContent(teclado.nextLine());
        elemento.appendChild(rea);
    }

    private static void modificarElement(Document documento, Scanner teclado) {
        // Demanem la id
        System.out.println("Introdueix que el ID del node que vols modificar: ");
        String id = teclado.nextLine();

        // Busquem per la id
        Element elemento = documento.getElementById(id);

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
        Element elemento = documento.getElementById(id);

        nodeArrel.removeChild(elemento);

    }

    // Metode per a indexar les id del arxiu xml
    private static void indexarId(Element nodeArrel) {
        NodeList childrens = nodeArrel.getChildNodes();

        for (int i = 0; i < childrens.getLength(); i++) {

            if (childrens.item(i).hasChildNodes()) {

                NodeList child = childrens.item(i).getChildNodes();

                for (int x = 0; x < child.getLength(); x++) {

                    if (child.item(x).getNodeName().equals("row") && child.item(x).hasAttributes()) {

                        NamedNodeMap atributs = child.item(x).getAttributes();

                        for (int j = 0; j < atributs.getLength(); j++) {

                            if (atributs.item(j).getNodeName().equals("_id")) {

                                ((Element) child.item(x)).setIdAttribute("_id", true);

                            }

                        }

                    }
                }

            }

        }
    }

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {

        // Creo una instancia de DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Creo un documentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Obtengo el documento, a partir del XML
        Document documento = builder.parse(new File("src/m6/UF1PersistenciaEnFitxers/Exercici5/comunitatsCatalanes.xml"));

        int opcio = -1;
        Scanner teclado = new Scanner(System.in);
        Element nodeArrel = documento.getDocumentElement();

        indexarId(nodeArrel);
        
        // Reasignem el nodeArrel al real que realment conte els nodes que volem modificar
        
        if (nodeArrel.hasChildNodes()) {
            NodeList fills = nodeArrel.getChildNodes();
            for (int i = 0; i < fills.getLength(); i++) {
                Node nodo = fills.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    nodeArrel = (Element) nodo;
                    i += fills.getLength();
                }
            }
        }

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
        StreamResult result = new StreamResult(new File("src/m6/UF1PersistenciaEnFitxers/Exercici5/comunitatsCatalanes.xml"));

        transformer.transform(source, result);
    }

}
