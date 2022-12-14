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

public class UpdateUserServlet extends HttpServlet {

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

        String id = request.getParameter("id");
        String modify = request.getParameter("modify");
        String delete = request.getParameter("delete");

        if (id == null || "".equals(id) || (modify == null && delete == null)) {
            response.sendRedirect(request.getContextPath() + "/admin/HandleUserServlet");
            return;
        }

        User u = userDao.findById(Integer.parseInt(id));

        if (u == null) {
            response.sendRedirect(request.getContextPath() + "/admin/HandleUserServlet");
            return;
        }

        if (delete != null) {
            userDao.delete(u);
            response.sendRedirect(request.getContextPath() + "/admin/HandleUserServlet");
            return;
        }

        request.setAttribute("u", u);

        this.getServletContext().getRequestDispatcher("/META-INF/admin/updateUser.jsp").forward(request, response);
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

        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (id == null || email == null || password == null || role == null) {
            request.setAttribute("error", "Tous les champs doivent ??tre renseign??s");
            this.getServletContext().getRequestDispatcher("/META-INF/admin/addUser.jsp").forward(request, response);
            return;
        }

        int userId = Integer.parseInt(id);
        User user = new User(userId, Role.valueOf(role), email, password);

        User updated = userDao.update(user);

        if (updated == null) {
            request.setAttribute("error", "Une erreur est survenue lors de la cr??ation de l'utilisateur");
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
