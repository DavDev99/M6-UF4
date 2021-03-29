/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF3PersistènciaenBDnativesXML.Examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

/**
 *
 * @author David
 */
public class ExamenUF3 {

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
        int consulta = -1;
        Scanner teclado = new Scanner(System.in);

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

        col = DatabaseManager.getCollection(URI, usu, usuPass);
        if (col == null) {
            System.out.println("*** LA COL·LECCIÓ NO EXISTEIX ***");
        } else {

            while (consulta != 0) {

                System.out.print("1. Consulta 1:  \n"
                        + "2. Consulta 2: \n"
                        + "3. Consulta 3: \n"
                        + "4. Consulta 4: \n"
                        + "5. Consulta 5: \n"
                        + "6. Consulta 6: \n"
                        + "0. Sortir \n");

                consulta = teclado.nextInt();
                teclado.nextLine();

                if (consulta == 1) {
                    System.out.println("Aquestes son totes les dates: ");
                    consulta1();
                    System.out.println("");
                } else if (consulta == 2) {
                    System.out.println("Dia amb més nous casos: ");
                    consulta2();
                    System.out.println("");
                } else if (consulta == 3) {
                    System.out.println("Dia amb més defuncions: ");
                    consulta3();
                    System.out.println("");
                } else if (consulta == 4) {
                    System.out.println("Mitjana de nous casos i defuncions: ");
                    consulta4();
                    System.out.println("");
                } else if (consulta == 5) {
                    System.out.println("");
                    consulta5();
                    System.out.println("");
                } else if (consulta == 6) {
                    System.out.println("");
                    consulta6();
                    System.out.println("");
                }

            }
        }
    }

    private static void consulta1() throws XMLDBException {

        XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet result = servei.query("/response/rows/row/data");

        //Recórrer les dades del recurs
        ResourceIterator i = result.getIterator();
        if (!i.hasMoreResources()) {
            System.out.println("LA CONSULTA NO TORNA RES");
        }

        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());

        }
    }

    private static void consulta2() throws XMLDBException {

        XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet result = servei.query(
                "let $max := max(/response/rows/row/nous_casos_diaris_confirmats)\n"
                + "let $data := /response/rows/row[nous_casos_diaris_confirmats = $max]/data\n"
                + "return concat(\"El dia: '\", $data, \"' te el record de nous contagis amb: \", $max)");

        //Recórrer les dades del recurs
        ResourceIterator i = result.getIterator();
        if (!i.hasMoreResources()) {
            System.out.println("LA CONSULTA NO TORNA RES");
        }
        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());

        }
    }

    private static void consulta3() throws XMLDBException {

        XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet result = servei.query(
                "let $max := max(/response/rows/row/defuncions_di_ries)\n"
                + "let $data := /response/rows/row[defuncions_di_ries = $max]/data\n"
                + "return concat(\"El dia: '\", $data, \"' te el record de defuncions amb: \", $max)");

        //Recórrer les dades del recurs
        ResourceIterator i = result.getIterator();
        if (!i.hasMoreResources()) {
            System.out.println("LA CONSULTA NO TORNA RES");
        }
        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());

        }
    }

    private static void consulta4() throws XMLDBException {

        XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet result = servei.query(
                "let $avgNousCasos := avg(/response/rows/row/defuncions_di_ries)\n"
                + "let $avgDefuncions := avg(/response/rows/row/nous_casos_diaris_confirmats)\n"
                + "return concat(\"La mitja de nous casos es: \", $avgNousCasos, \" La mitja de defuncions: \", $avgDefuncions)");

        //Recórrer les dades del recurs
        ResourceIterator i = result.getIterator();
        if (!i.hasMoreResources()) {
            System.out.println("LA CONSULTA NO TORNA RES");
        }
        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());

        }
    }

    private static void consulta5() throws XMLDBException {

        String dia = "";
        int nousCasos = 0;
        int defuncionsDiaries = 0;
        int totalCasosConfirmats = 0;
        int totalDeDefuncions = 0;

        try {
            System.out.println("Escriu el dia de les noves dades:");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            dia = in.readLine();

        } catch (IOException e) {
            System.out.println("Error en llegir");
            e.printStackTrace();
        }

        XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet result = servei.query("/response/rows/row[data = '" + dia + "']");

        //Recórrer les dades del recurs
        ResourceIterator i = result.getIterator();

        if (i.hasMoreResources()) {

            System.out.println("Aquest dia ja existeix");

        } else {
            try {
                System.out.println("Escriu els nous casos diaris:");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                nousCasos = Integer.parseInt(in.readLine());

                System.out.println("Escriu les defuncions diaries:");
                in = new BufferedReader(new InputStreamReader(System.in));
                defuncionsDiaries = Integer.parseInt(in.readLine());

                System.out.println("Escriu els total casos confirmats:");
                in = new BufferedReader(new InputStreamReader(System.in));
                totalCasosConfirmats = Integer.parseInt(in.readLine());

                System.out.println("Escriu els total defuncions:");
                in = new BufferedReader(new InputStreamReader(System.in));
                totalDeDefuncions = Integer.parseInt(in.readLine());

            } catch (IOException e) {
                System.out.println("Error en llegir");
                e.printStackTrace();
            }

            result = servei.query("update insert <row>"
                    + "        <data>" + dia + "</data>"
                    + "        <nous_casos_diaris_confirmats>" + nousCasos + "</nous_casos_diaris_confirmats>"
                    + "        <defuncions_di_ries>" + defuncionsDiaries + "</defuncions_di_ries>"
                    + "        <total_de_casos_confirmats>" + totalCasosConfirmats + "</total_de_casos_confirmats>"
                    + "        <total_de_defuncions>" + totalDeDefuncions + "</total_de_defuncions>"
                    + "    </row> into /response/rows");

        }
    }

    private static void consulta6() throws XMLDBException {
        String dia = "";

        try {
            System.out.println("Escriu el dia de les noves dades:");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            dia = in.readLine();

        } catch (IOException e) {
            System.out.println("Error en llegir");
            e.printStackTrace();
        }

        XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet result = servei.query("/response/rows/row[data = '" + dia + "']");

        //Recórrer les dades del recurs
        ResourceIterator i = result.getIterator();

        if (!i.hasMoreResources()) {

            System.out.println("Aquest dia no existeix");

        } else {

            result = servei.query("update delete /response/rows/row[data = '" + dia + "']");
            System.out.println("Dia eliminat correctament");
        }
    }
}
