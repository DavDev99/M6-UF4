package taller;
 
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.persistence.*;
import javax.servlet.annotation.WebServlet;
 
@WebServlet("/TreballadorServlet")
public class MecanicServlet extends HttpServlet {
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
            String arrelgats = request.getParameter("arreglats");

            if (nom != null && dni != null && arrelgats != null) {
                em.getTransaction().begin();
                em.persist(new Mecanic(Integer.parseInt(arrelgats), dni, nom));
                em.getTransaction().commit();
            }

            List<Mecanic> clientList = em.createQuery("SELECT g FROM Mecanics g", Mecanic.class).getResultList();
            request.setAttribute("mecanics", clientList);
            request.getRequestDispatcher("/mecanics.jsp")
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