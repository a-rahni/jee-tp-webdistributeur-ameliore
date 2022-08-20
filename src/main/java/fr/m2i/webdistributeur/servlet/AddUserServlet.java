package fr.m2i.webdistributeur.servlet;

import fr.m2i.webdistributeur.dao.UserDao;
import fr.m2i.webdistributeur.entity.Role;
import fr.m2i.webdistributeur.entity.User;
import fr.m2i.webdistributeur.utils.UserDb;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserServlet extends HttpServlet {

    private UserDb userDb;
    private UserDao userDao;
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userDb = (UserDb) this.getServletContext().getAttribute("userDb");
        userDao = (UserDao) this.getServletContext().getAttribute("userDao");
        this.getServletContext().getRequestDispatcher("/META-INF/admin/addUser.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userDb = (UserDb) this.getServletContext().getAttribute("userDb");
        userDao = (UserDao) this.getServletContext().getAttribute("userDao");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (email == null || password == null || role == null) {
            request.setAttribute("error", "Tous les champs doivent être renseignés");
            this.getServletContext().getRequestDispatcher("/META-INF/admin/addUser.jsp").forward(request, response);
            return;
        }

        int id = userDb.getUsers().isEmpty() ? 1 :
                userDb.getUsers().get(userDb.getUsers().size() - 1).getId() + 1;

        User user = new User(id, Role.valueOf(role), email, password);

        User created = userDao.create(user);
        
        if (created == null) {
            request.setAttribute("error", "Une erreur est survenue lors de la création de l'utilisateur");
            this.getServletContext().getRequestDispatcher("/META-INF/admin/addUser.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/admin/HandleUserServlet");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
