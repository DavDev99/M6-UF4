<%-- 
    Document   : taller
    Created on : 14-nov-2020, 18:14:51
    Author     : PC-Casa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Taller Control Page</title>
    </head>
    <body>
        <h1>INCREIBLE PROGRAMA DE GESTIO DE TALLERS SUPER RENTABLE PLS NO ME SUSPENGUIS <3</h1>
        <h3>Nou Vehicle</h3>
        <form method="POST" action="VehicleServlet">
            Model: <input type="text" name="model" />
            Matricula: <input type="text" name="matricula" />
            Problema: <input type="text" name="problema" />
            Arreglat: <input type="checkbox" value="true"> 
            <input type="submit" value="Add" />
        </form>
        
        <h3>Nou Client</h3>
        <form method="POST" action="ClientServlet">
            Nom: <input type="text" name="name" />
            DNI: <input type="text" name="dni" />
            Matricula del Cotxe: <input type="text" name="matricula" />
            <input type="submit" value="Add" />
        </form>
        
        <h3>Nou Treballador</h3>
        <form method="POST" action="TreballadorServlet">
            Nom: <input type="text" name="name" />
            DNI: <input type="text" name="dni" />
            Cotxes arreglats: <input type="number" name="arreglats" />
            <input type="submit" value="Add" />
        </form>
        

    </body>
</html>
