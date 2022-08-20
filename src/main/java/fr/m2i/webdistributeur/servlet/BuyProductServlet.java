package fr.m2i.webdistributeur.servlet;

import fr.m2i.webdistributeur.Distributor;
import fr.m2i.webdistributeur.entity.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyProductServlet extends HttpServlet {

    private Distributor distributor;
    private User user;

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

        distributor = (Distributor) this.getServletContext().getAttribute("distrib");
        user = (User) request.getSession(false).getAttribute("user");

        request.setAttribute("stock", distributor.getStock());
        request.setAttribute("creditError", null);
        request.setAttribute("productError", null);
        request.setAttribute("insufficientError", null);

        this.getServletContext().getRequestDispatcher("/META-INF/customer/buyProduct.jsp").forward(request, response);
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
        user = (User) request.getSession(false).getAttribute("user");

        addCredit(request);
        chooseProduct(request);
        
        request.setAttribute("stock", distributor.getStock());
        
        this.getServletContext().getRequestDispatcher("/META-INF/customer/buyProduct.jsp").forward(request, response);
    }

    private void addCredit(HttpServletRequest request) {
        String credit = request.getParameter("credit");

        if (credit == null) {
            request.setAttribute("creditError", null);
            return;
        }

        try {
            int amount = Integer.parseInt(credit);

            if (amount < 0) {
                request.setAttribute("creditError", "Vous ne pouvez pas ajouter un monant négatif");
                return;
            }

            user.setCredit(user.getCredit() + amount);
            request.setAttribute("creditError", null);
        } catch (Exception e) {
            request.setAttribute("creditError", "Une erreur est survenue lors de l'ajout de crédit");
        }
    }

    private void chooseProduct(HttpServletRequest request) {
        String id = request.getParameter("id");

        if (id == null) {
            request.setAttribute("productError", null);
            request.setAttribute("insufficientError", null);
            return;
        }

        try {
            int productId = Integer.parseInt(id);

            if (distributor.getProduct(productId) == null) {
                request.setAttribute("productError", "Le produit demandé n'exsite pas");
                return;
            }

            if (!distributor.isEnoughCredit(productId, user.getCredit())) {
                request.setAttribute("insufficientError", "Votre crédit est insuffisant");
                return;
            }

            if (!distributor.isEnoughStock(productId)) {
                request.setAttribute("productError", "Le produit n'est plus en stock");
                return;
            }

            distributor.orderProduct(productId, user);
            request.setAttribute("productError", null);
            request.setAttribute("insufficientError", null);
        } catch (Exception e) {
            request.setAttribute("productError", "Une erreur est survenue lors de votre commande");
        }

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
