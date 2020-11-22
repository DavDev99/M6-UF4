/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6.UF2PersistenciaenBDBDORiBDOO.Exercici1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author david
 */
public class Exercici1 {

    public static void main(String[] args) {
        Connection connection = null;
        Statement insertStmt = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercici1_m6", "root", "");

            /*insertStmt = connection.createStatement();
            insertStmt.execute("INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,STAT_CD) VALUES (1,'Lokesh','Gupta',5)");
            insertStmt.execute("INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,STAT_CD) VALUES (2,'howtodoinjava','com',5)");*/
            selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery("SELECT ID,nom FROM alumnes");
            
            while (rs.next()) {
                System.out.println(rs.getString(1));    //First Column
                System.out.println(rs.getString(2));    //Second Column
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                selectStmt.close();
                insertStmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
