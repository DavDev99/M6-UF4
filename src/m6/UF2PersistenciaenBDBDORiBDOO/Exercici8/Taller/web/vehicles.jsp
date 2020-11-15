<%-- 
    Document   : taller
    Created on : 14-nov-2020, 18:14:51
    Author     : PC-Casa
--%>

<%@page import="taller.Vehicle"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Taller Control Page</title>
    </head>
    <body>
        <h3>Nou Vehicle</h3>
        <form method="POST" action="VehicleServlet">
            Model: <input type="text" name="model" />
            Matricula: <input type="text" name="matricula" />
            Problema: <input type="text" name="problema" />
            <input type="submit" value="Add" />
        </form>
        <hr>
        <ol> <%
            @SuppressWarnings("unchecked") 
            List<Vehicle> vehicles = (List<Vehicle>)request.getAttribute("vehicles");
            for (Vehicle vehicle : vehicles) { %>
                <li> <%= vehicle %> </li> <%
            } %>
        </ol>
        <hr>
        <a href="taller.jsp"> Tornar al menu </a>

    </body>
</html>
