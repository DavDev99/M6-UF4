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
import java.util.Scanner;

/**
 *
 * @author david
 */
public class Exercici1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner teclado = new Scanner(System.in);
        int opcio = -1;
        String nom;
        String dni;
        int id;
        String dataNaixement;
        String adresaPostal;
        String sexe;
        int codiPostal;
        String poblacio;

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/exercici1_m6", "root", "");
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Bucle principal del menu
        while (opcio != 0) {
            System.out.println("Que vols fer? \n"
                    + "1. Crear un alumne \n"
                    + "2. Modificar alumne \n"
                    + "3. Eliminar alumne \n"
                    + "4. Crear Poblacio \n"
                    + "0. sortir \n");

            opcio = teclado.nextInt();
            teclado.nextLine();

            // Creacio del alumne
            if (opcio == 1) {
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
                teclado.nextLine();

                System.out.println("Introdueix poblacio");
                poblacio = teclado.nextLine();

                inseriAlumnes(nom, dni, dataNaixement, adresaPostal, sexe, codiPostal);

            } else if (opcio == 2) {
                // Modificar alumne
                System.out.println("Introdueix la id del alumne");
                id = teclado.nextInt();
                teclado.nextLine();
                
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
                teclado.nextLine();

                System.out.println("Introdueix poblacio");
                poblacio = teclado.nextLine();

                modificarAlumnes(id, nom, dni, dataNaixement, adresaPostal, sexe, codiPostal);

            } else if (opcio == 3) {
                // Modificar alumne
                System.out.println("Introdueix el id del alumne a eliminar");
                id = teclado.nextInt();
                teclado.nextLine();
                eliminarAlumnes(id);

            } else if (opcio == 4) {
                System.out.println("Introdueix un nom de poblacio");
                poblacio = teclado.nextLine();

                System.out.println("Introdueix un codi_postal");
                codiPostal = teclado.nextInt();
                teclado.nextLine();

                inseriPoblacio(poblacio, codiPostal);
            }
        }

    }

    private static void inseriAlumnes(String nom, String dni, String dataNaixement, String adresaPostal, String sexe, int codiPostal) {

        Statement stmt = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercici1_m6", "root", "");

            stmt = connection.createStatement();
            stmt.execute("INSERT INTO alumnes (nom, DNI, data_naixement, adreça_postal, sexe, codi_postal, poblacio) VALUES ('" + nom + "','" + dni + "','" + dataNaixement + "','" + adresaPostal + "','" + sexe + "'," + codiPostal + ")");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void modificarAlumnes(int id, String nom, String dni, String dataNaixement, String adresaPostal, String sexe, int codiPostal) {

        Statement stmt = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercici1_m6", "root", "");

            stmt = connection.createStatement();
            stmt.execute("UPDATE alumnes SET nom= '" + nom + "',DNI = '" 
                    + dni + "',data_naixement = '" 
                    + dataNaixement + "', adreça_postal = '" 
                    + adresaPostal + "', sexe = '" 
                    + sexe + "', codi_postal = " 
                    + codiPostal + "' WHERE id = " 
                    + id);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void eliminarAlumnes(int id) {

        Statement stmt = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercici1_m6", "root", "");

            stmt = connection.createStatement();
            stmt.execute("DELETE FROM `alumnes` WHERE id = " + id +"");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void inseriPoblacio(String poblacio, int codiPostal) {
                Statement stmt = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercici1_m6", "root", "");

            stmt = connection.createStatement();
            stmt.execute("INSERT INTO poblacions (poblacio, codi_postal ) VALUES ('" + poblacio + "'," + codiPostal + ")");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
