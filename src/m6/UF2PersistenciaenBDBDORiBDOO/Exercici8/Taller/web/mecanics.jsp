<%-- 
    Document   : taller
    Created on : 14-nov-2020, 18:14:51
    Author     : PC-Casa
--%>

<%@page import="taller.Mecanic"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Taller Control Page</title>
    </head>
    <body>
        <h3>Nou Treballador</h3>
        <form method="POST" action="MecanicServlet">
            Nom: <input type="text" name="name" />
            DNI: <input type="text" name="dni" />
            Cotxes arreglats: <input type="number" name="arreglats" />
            <input type="submit" value="Add" />
        </form>
        <ol> <%
            @SuppressWarnings("unchecked") 
            List<Mecanic> mecanics = (List<Mecanic>)request.getAttribute("mecanics");
            for (Mecanic mecanic : mecanics) { %>
                <li> <%= mecanic %> </li> <%
            } %>
        </ol>
      

    </body>
</html>
