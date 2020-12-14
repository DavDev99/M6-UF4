/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF2PersistenciaenBDBDORiBDOO.Exercici1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class Exercici1 {

    static Connection connection = null;
    static Scanner teclado;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        teclado = new Scanner(System.in);
        int opcio = -1;
        String nom = "";
        String dni = "";
        String dataNaixement = "";
        String adresaPostal = "";
        String sexe = "";
        int codiPostal = 0;
        String poblacio;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercici1_m6", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Bucle principal del menu
        while (opcio != 0) {

            try {
                System.out.println("Que vols fer? \n"
                        + "1. Crear un alumne \n"
                        + "2. Modificar alumne \n"
                        + "3. Eliminar alumne \n"
                        + "4. Crear Poblacio \n"
                        + "5. Modificar Poblacio \n"
                        + "6. Eliminar Poblacio \n"
                        + "7. Consultar alumne per DNI \n"
                        + "8. Consultar alumnes \n"
                        + "9. Consultar poblacio per codi posal \n"
                        + "10. Consultar poblacions \n"
                        + "0. sortir \n");

                opcio = teclado.nextInt();
                teclado.nextLine();

                // Creacio del alumne
                if (opcio == 1) {
                    try {

                        System.out.println("Introdueix un nom");
                        nom = teclado.nextLine();

                        System.out.println("Introdueix un DNI");
                        dni = teclado.nextLine();

                        System.out.println("Introdueix data de naixement (exemple: 1999-03-22)");
                        dataNaixement = teclado.nextLine();

                        System.out.println("Introdueix adreça postal");
                        adresaPostal = teclado.nextLine();

                        System.out.println("Introdueix sexe");
                        sexe = teclado.nextLine();

                        System.out.println("Introdueix codi postal");
                        codiPostal = teclado.nextInt();

                        inseriAlumnes(nom, dni, dataNaixement, adresaPostal, sexe, codiPostal);
                        
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Les dades introduides no son valides");
                    }
                } else if (opcio == 2) {
                    // Modificar alumne
                    System.out.println("Introdueix el DNI del alumne");
                    dni = teclado.nextLine();

                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `alumnes` WHERE DNI = '" + dni + "'");

                    if (rs.next()) {
                        nom = rs.getString("nom");
                        dataNaixement = rs.getString("data_naixement");
                        adresaPostal = rs.getString("adreça_postal");
                        sexe = rs.getString("sexe");
                        codiPostal = rs.getInt("codi_postal");
                    }
                    String aux = "";
                    int aux2 = 0;

                    System.out.println("Introdueix un nom:[" + nom + "]");
                    aux = teclado.nextLine();
                    if (aux.length() != 0) {
                        nom = aux;
                    }

                    System.out.println("Introdueix data de naixement (exemple: 1999-03-22):[" + dataNaixement + "]");
                    aux = teclado.nextLine();
                    if (aux.length() != 0) {
                        dataNaixement = aux;
                    }

                    System.out.println("Introdueix adreça postal:[" + adresaPostal + "]");
                    aux = teclado.nextLine();
                    if (aux.length() != 0) {
                        adresaPostal = aux;
                    }

                    System.out.println("Introdueix sexe:[" + sexe + "]");
                    aux = teclado.nextLine();
                    if (aux.length() != 0) {
                        sexe = aux;
                    }

                    System.out.println("Introdueix codi postal:[" + codiPostal + "]");
                    aux = teclado.nextLine();

                    if (aux.length() != 0) {
                        aux2 = Integer.parseInt(aux);
                        if (aux2 != codiPostal) {
                            codiPostal = aux2;
                        }
                    }

                    modificarAlumnes(nom, dni, dataNaixement, adresaPostal, sexe, codiPostal);
                    
                    System.out.println("Alumne modificat correctament");
                } else if (opcio == 3) {
                    // Eliminar alumne
                    System.out.println("Introdueix el DNI del alumne");
                    dni = teclado.nextLine();

                    eliminarAlumnes(dni);

                } else if (opcio == 4) {
                    System.out.println("Introdueix un nom de poblacio");
                    poblacio = teclado.nextLine();

                    try {

                        System.out.println("Introdueix un codi_postal");
                        codiPostal = teclado.nextInt();
                        teclado.nextLine();

                        inseriPoblacio(poblacio, codiPostal);
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Les dades introduides no son valides");
                    }
                } else if (opcio == 5) {
                    try {

                        System.out.println("Introdueix un codi_postal");
                        codiPostal = teclado.nextInt();
                        teclado.nextLine();

                        System.out.println("Introdueix un nom de poblacio");
                        poblacio = teclado.nextLine();

                        modificarPoblacio(codiPostal, poblacio);
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Les dades introduides no son valides");
                    }
                } else if (opcio == 6) {
                    try {
                        System.out.println("Introdueix un codi_postal");
                        codiPostal = teclado.nextInt();
                        teclado.nextLine();
                        eliminarPoblacio(codiPostal);
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Les dades introduides no son valides");
                    }

                } else if (opcio == 7) {

                    System.out.println("Introdueix el DNI del alumne");
                    dni = teclado.nextLine();

                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `alumnes` WHERE DNI = '" + dni + "'");

                    if (rs.next()) {
                        nom = rs.getString("nom");
                        System.out.println("Nom: " + nom);

                        dni = rs.getString("DNI");
                        System.out.println("Nom: " + dni);

                        dataNaixement = rs.getString("data_naixement");
                        System.out.println("Data naixement: " + dataNaixement);

                        adresaPostal = rs.getString("adreça_postal");
                        System.out.println("Adreça posatal: " + adresaPostal);

                        sexe = rs.getString("sexe");
                        System.out.println("Sexe: " + sexe);

                        codiPostal = rs.getInt("codi_postal");
                        System.out.println("Codi Posatal: " + codiPostal);

                        System.out.println("");
                    } else {
                        System.out.println("Id incorrecta");
                        System.out.println("");

                    }

                } else if (opcio == 8) {

                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `alumnes`");

                    while (rs.next()) {
                        nom = rs.getString("nom");
                        System.out.print("Nom: " + nom);

                        dni = rs.getString("DNI");
                        System.out.print("\t DNI: " + dni);

                        dataNaixement = rs.getString("data_naixement");
                        System.out.print("\t Data naixement: " + dataNaixement);

                        adresaPostal = rs.getString("adreça_postal");
                        System.out.print("\t Adreça posatal: " + adresaPostal);

                        sexe = rs.getString("sexe");
                        System.out.print("\t Sexe: " + sexe);

                        codiPostal = rs.getInt("codi_postal");
                        System.out.println("\t Codi Posatal: " + codiPostal);

                    }
                    System.out.println("");
                } else if (opcio == 9) {

                    System.out.println("Introdueix el codi posal de la poblacio:");
                    codiPostal = teclado.nextInt();
                    teclado.nextLine();

                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `poblacions` WHERE codi_postal = " + codiPostal);

                    if (rs.next()) {
                        poblacio = rs.getString("poblacio");
                        System.out.print("Poblacio: " + poblacio);

                        codiPostal = rs.getInt("codi_postal");
                        System.out.println("\t Codi Posal: " + codiPostal);
                        System.out.println("");
                    } else {
                        System.out.println("Codi posal incorrecte");
                        System.out.println("");

                    }

                } else if (opcio == 10) {

                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `poblacions`");

                    while (rs.next()) {
                        poblacio = rs.getString("poblacio");
                        System.out.print("Poblacio: " + poblacio);

                        codiPostal = rs.getInt("codi_postal");
                        System.out.println("\t Codi Posal: " + codiPostal);
                    }
                    System.out.println("");
                } else {
                    connection.close();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Sol numeros del 0 al 10");
            }
        }

    }

    private static void inseriAlumnes(String nom, String dni, String dataNaixement, String adresaPostal, String sexe, int codiPostal) {

        Statement stmt = null;

        try {

            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT codi_postal FROM `poblacions` WHERE codi_postal = " + codiPostal);

            if (rs.next()) {
                stmt = connection.createStatement();
                stmt.execute("INSERT INTO alumnes (nom, DNI, data_naixement, adreça_postal, sexe, codi_postal) VALUES ('" + nom + "','" + dni + "','" + dataNaixement + "','" + adresaPostal + "','" + sexe + "'," + codiPostal + ")");

                System.out.println("Alumne inserit correctament");
                System.out.println("");

            } else {
                System.out.println("Codi postal no valid");
                System.out.println("");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void modificarAlumnes(String nom, String dni, String dataNaixement, String adresaPostal, String sexe, int codiPostal) {

        Statement stmt = null;
        try {

            stmt = connection.createStatement();
            stmt.execute("UPDATE alumnes SET nom= '" + nom
                    + "',data_naixement = '" + dataNaixement
                    + "', adreça_postal = '" + adresaPostal
                    + "', sexe = '" + sexe
                    + "', codi_postal = " + codiPostal
                    + " WHERE DNI = '" + dni + "'");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void eliminarAlumnes(String dni) throws SQLException {

        Statement stmt = null;
        
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DNI FROM `alumnes` WHERE DNI = '" + dni + "'");

        if (rs.next()) {

            try {
                stmt = connection.createStatement();
                stmt.execute("DELETE FROM `alumnes` WHERE DNI = '" + dni + "'");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Alumne no existeix o el DNI no es correcte");
            } finally {
                try {
                    stmt.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            System.out.println("DNI no valid");
            System.out.println("");

        }
    }

    private static void inseriPoblacio(String poblacio, int codiPostal) {
        Statement stmt = null;
        try {

            stmt = connection.createStatement();
            stmt.execute("INSERT INTO poblacions (poblacio, codi_postal ) VALUES ('" + poblacio + "'," + codiPostal + ")");

            System.out.println("Poblacio inserida correctament");
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void eliminarPoblacio(int codi_postal) {

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(codi_postal) FROM `alumnes` WHERE codi_postal = " + codi_postal);

            if (rs.next()) {
                String count = rs.getString(1);

                System.out.println("Si borres aquesta poblacio tambe borraras " + count + " alumnes, estas segur?[Si]");
                String resposta = teclado.nextLine();

                if (!resposta.equalsIgnoreCase("no")) {
                    stmt = connection.createStatement();
                    stmt.execute("DELETE FROM `poblacions` WHERE codi_postal = " + codi_postal + "");

                    System.out.println("Poblacio eliminada correctament");
                }else{
                    System.out.println("Poblacio no sera eliminada");
                }

            } else {

                System.out.println("Si borres aquesta poblacio tambe borraras 0 alumnes, estas segur?[Si]");
                String resposta = teclado.nextLine();

                if (!resposta.equalsIgnoreCase("no")) {
                    stmt = connection.createStatement();
                    stmt.execute("DELETE FROM `poblacions` WHERE codi_postal = " + codi_postal + "");
                    System.out.println("Poblacio eliminada correctament");
                }else{
                    System.out.println("Poblacio no sera eliminada");
                }

            }


            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void modificarPoblacio(int codiPostal, String poblacio) throws SQLException {
        
        Statement stmt = null;
        
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT codi_postal FROM `poblacions` WHERE codi_postal = '" + codiPostal + "'");

        if (rs.next()) {
            try {

            stmt = connection.createStatement();
            stmt.execute("UPDATE poblacions SET poblacio = '" + poblacio
                    + "' WHERE codi_postal = " + codiPostal);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    stmt.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Codi postal no valid");
            System.out.println("");

        }
    }
}
