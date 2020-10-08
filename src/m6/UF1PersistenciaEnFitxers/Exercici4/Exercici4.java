/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF1PersistenciaEnFitxers.Exercici4;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
public class Exercici4 {
 
    public static boolean nousElements(Element node){
        
        
        System.out.println("Node name: " + node.getNodeName());
        
        System.out.println("Node Value: " + node.getNodeValue());

        
        if (node.hasAttributes()) {
             NamedNodeMap attributes = node.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.println("-- Attribute: " + attributes.item(i).getNodeName() + ", with value: " + attributes.item(i).getTextContent());
            }
        }
        if (node.hasChildNodes()) {
            NodeList fills = node.getChildNodes();
            for (int i = 0; i < fills.getLength(); i++) {
                Node nodo = fills.item(i);
                
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
  
                    nousElements((Element)nodo);
                }
            }        
        }

        
        return false;
    }
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
 
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
 
            // Obtengo el documento, a partir del XML
            Document documento = builder.parse(new File("pelis.xml"));
 
            Element nodeArrel = documento.getDocumentElement();

            nousElements(nodeArrel);
    }
 
}