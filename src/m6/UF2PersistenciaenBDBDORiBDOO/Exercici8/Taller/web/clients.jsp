<%-- 
    Document   : taller
    Created on : 14-nov-2020, 18:14:51
    Author     : PC-Casa
--%>

<%@page import="taller.Client"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Taller Control Page</title>
    </head>
    <body>
        <h3>Nou Client</h3>
        <form method="POST" action="ClientServlet">
            Nom: <input type="text" name="name" />
            DNI: <input type="text" name="dni" />
            Matricula del Cotxe: <input type="text" name="matricula" />
            <input type="submit" value="Add" />
        </form>
        <hr>
        <ol> <%
            @SuppressWarnings("unchecked") 
            List<Client> clients = (List<Client>)request.getAttribute("clients");
            for (Client client : clients) { %>
                <li> <%= client %> </li> <%
            } %>
        </ol>


    </body>
</html>
