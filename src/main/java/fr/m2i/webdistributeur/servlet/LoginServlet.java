package fr.m2i.webdistributeur.servlet;

import fr.m2i.webdistributeur.entity.Role;
import fr.m2i.webdistributeur.entity.User;
import fr.m2i.webdistributeur.utils.UserDb;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

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
        this.getServletContext().getRequestDispatcher("/META-INF/login.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        String password = request.getParameter("motPasse");
        UserDb userDb = (UserDb) this.getServletContext().getAttribute("userDb");
        User user = userDb.checkUser(email, password);

        if (user == null) {
            request.setAttribute("error", "email / mot de passe invalide");
            this.getServletContext().getRequestDispatcher("/META-INF/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        String redirectTo = Role.CUSTOMER.equals(user.getRole()) ? "/customer/BuyProductServlet" :
                Role.PROVIDER.equals(user.getRole()) ? "/provider/HandleProductServlet" :
                Role.ADMIN.equals(user.getRole()) ? "/admin/HandleUserServlet" :
                "/ShowProductServlet";

        response.sendRedirect(request.getContextPath() + redirectTo);
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
