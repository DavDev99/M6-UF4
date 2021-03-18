/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF3PersistènciaenBDnativesXML.Exercici3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

/**
 *
 * @author David
 */
public class Activitat3 {

    //Driver per a eXist
    static String driver = "org.exist.xmldb.DatabaseImpl";
    //Col·lecció
    static Collection col = null;
    //URI col·lecció
    static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/";
    //Usuari
    static String usu = "admin";
    //Contrasenya
    static String usuPass = "admin";

    public static void main(String[] args) throws XMLDBException {

        try {
            //Carrega el driver
            Class cl = Class.forName(driver);
            //Instància de la BD
            Database database = (Database) cl.newInstance();
            //Registre del driver
            DatabaseManager.registerDatabase(database);
        } catch (Exception e) {
            System.out.println("Error en inicialitzar la base de dades eXist");
            e.printStackTrace();
        }

        System.out.println("Escriu un departament:");
        String s = null;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            s = in.readLine();
        } catch (IOException e) {
            System.out.println("Error en llegir");
            e.printStackTrace();
        }

        int departament = Integer.parseInt(s);

        col = DatabaseManager.getCollection(URI, usu, usuPass);
        if (col == null) {
            System.out.println("*** LA COL·LECCIÓ NO EXISTEIX ***");
        }
        XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet result = servei.query("for $em in /EMPLEADOS/EMP_ROW[DEPT_NO=" + departament + "] return $em");

        //Recórrer les dades del recurs
        ResourceIterator i;
        i = result.getIterator();
        if (!i.hasMoreResources()) {
            System.out.println("LA CONSULTA NO TORNA RES");
        }
        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());

        }
        //S'esborra

        insereixdep();
        modificardep();
        esborradep();
        col.close();

    }

    public static void insereixdep() throws XMLDBException {
        int numDep = 0;
        String nameDep = "";
        String localitatDep = "";

        col = DatabaseManager.getCollection(URI, usu, usuPass);

        XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");

        try {
            System.out.println("Escriu el departament a crear:");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            numDep = Integer.parseInt(in.readLine());

        } catch (IOException e) {
            System.out.println("Error en llegir");
            e.printStackTrace();
        }

        ResourceSet result = servei.query("for $em in /departamentos/DEP_ROW[DEPT_NO =" + numDep + "] return $em");
        //Recórrer les dades del recurs
        ResourceIterator i;
        i = result.getIterator();

        if (i.hasMoreResources()) {

            System.out.println("El departament ja existeix");

        } else {
            try {
                System.out.println("Escriu un nom de departament:");

                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                nameDep = in.readLine();

                System.out.println("Escriu una localitat per al departament:");

                in = new BufferedReader(new InputStreamReader(System.in));
                localitatDep = in.readLine();

            } catch (IOException e) {
                System.out.println("Error en llegir");
                e.printStackTrace();
            }

            result = servei.query("update insert <DEP_ROW>"
                    + "        <DEPT_NO>" + numDep + "</DEPT_NO>"
                    + "        <DNOMBRE>" + nameDep + "</DNOMBRE>"
                    + "        <LOC>" + localitatDep + "</LOC>"
                    + "    </DEP_ROW> into /departamentos");

        }
    }

    public static void modificardep() throws XMLDBException {
        int numDep = 0;
        int numToChange = 0;
        String nameDep = "";
        String localitatDep = "";
        col = DatabaseManager.getCollection(URI, usu, usuPass);
        XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");

        try {
            System.out.println("Escriu el departament a modificar:");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            numToChange = Integer.parseInt(in.readLine());

        } catch (IOException e) {
            System.out.println("Error en llegir");
            e.printStackTrace();
        }

        ResourceSet result = servei.query("for $em in /departamentos/DEP_ROW[DEPT_NO =" + numToChange + "] return $em");
        //Recórrer les dades del recurs
        ResourceIterator i;
        i = result.getIterator();

        if (!i.hasMoreResources()) {

            System.out.println("El departament no existeix");

        } else {

            try {
                System.out.println("Escriu un codi departament:");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                numDep = Integer.parseInt(in.readLine());

                System.out.println("Escriu un nom de departament:");

                in = new BufferedReader(new InputStreamReader(System.in));
                nameDep = in.readLine();

                System.out.println("Escriu una localitat per al departament:");

                in = new BufferedReader(new InputStreamReader(System.in));
                localitatDep = in.readLine();

            } catch (IOException e) {
                System.out.println("Error en llegir");
                e.printStackTrace();
            }

            result = servei.query("update replace /departamentos/DEP_ROW[DEPT_NO = " + numToChange + "] with "
                    + "<DEP_ROW>"
                    + "   <DEPT_NO>" + numDep + "</DEPT_NO>"
                    + "   <DNOMBRE>" + nameDep + "</DNOMBRE>"
                    + "   <LOC>" + localitatDep + "</LOC>"
                    + "</DEP_ROW>");
        }

    }

    public static void esborradep() throws XMLDBException {
        int numDep = 0;
        col = DatabaseManager.getCollection(URI, usu, usuPass);
        XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");

        try {
            System.out.println("Escriu el departament a eliminar:");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            numDep = Integer.parseInt(in.readLine());

        } catch (IOException e) {
            System.out.println("Error en llegir");
            e.printStackTrace();
        }

        ResourceSet result = servei.query("for $em in /departamentos/DEP_ROW[DEPT_NO =" + numDep + "] return $em");
        //Recórrer les dades del recurs
        ResourceIterator i;
        i = result.getIterator();

        if (!i.hasMoreResources()) {

            System.out.println("El departament no existeix");

        } else {

            result = servei.query("update delete /departamentos/DEP_ROW[DEPT_NO = " + numDep + "]");
        }

    }
}
