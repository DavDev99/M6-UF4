package taller;
 
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.persistence.*;
import javax.servlet.annotation.WebServlet;
 
@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Obtain a database connection:
        EntityManagerFactory emf =
           (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
 
        try {
            
            String nom = request.getParameter("name");
            String dni = request.getParameter("dni");
            String matricula = request.getParameter("matricula");

            if (nom != null && dni != null && matricula != null) {
                em.getTransaction().begin();
                em.persist(new Client(matricula, dni, nom));
                em.getTransaction().commit();
            }

            List<Client> clientList = em.createQuery("SELECT g FROM Client g", Client.class).getResultList();
            request.setAttribute("clients", clientList);
            request.getRequestDispatcher("/clients.jsp")
                .forward(request, response);
 
        } finally {
            // Close the database connection:
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}