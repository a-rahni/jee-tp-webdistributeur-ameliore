package fr.m2i.webdistributeur.servlet;

import fr.m2i.webdistributeur.Distributor;
import fr.m2i.webdistributeur.Product;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProductServlet extends HttpServlet {

    private Distributor distributor;

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

        String id = request.getParameter("id");
        String modify = request.getParameter("modify");
        String delete = request.getParameter("delete");

        if (id == null || "".equals(id) || (modify == null && delete == null)) {
            response.sendRedirect(request.getContextPath() + "/provider/HandleProductServlet");
            return;
        }

        Product item = distributor.getProduct(Integer.parseInt(id));

        if (item == null) {
            response.sendRedirect(request.getContextPath() + "/provider/HandleProductServlet");
            return;
        }

        if (delete != null) {
            distributor.getStock().remove(item);
            response.sendRedirect(request.getContextPath() + "/provider/HandleProductServlet");
            return;
        }
        
        request.setAttribute("item", item);

        this.getServletContext().getRequestDispatcher("/META-INF/provider/updateProduct.jsp").forward(request, response);
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
        distributor = (Distributor) this.getServletContext().getAttribute("distrib");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");

        if (id == null || name == null || quantity == null || quantity.isEmpty() || price == null || price.isEmpty()) {
            request.setAttribute("error", "Tous les champs doivent être renseignés");
            this.getServletContext().getRequestDispatcher("/META-INF/provider/updateProduct.jsp").forward(request, response);
            return;
        }

        Integer intPrice = Integer.parseInt(price);
        Integer intQuantity = Integer.parseInt(quantity);

        if (intPrice < 1 || intQuantity < 1) {
            request.setAttribute("error", "Vueillez insérer des données valides");
            this.getServletContext().getRequestDispatcher("/META-INF/provider/addProduct.jsp").forward(request, response);
            return;
        }

        Integer idProduct = Integer.parseInt(id);
        Product product = new Product(idProduct, name, intPrice, intQuantity);

        for (int i = 0; i < distributor.getStock().size(); i++) {
            Product productToUpdate = distributor.getStock().get(i);

            if (idProduct == productToUpdate.getId()) {
                distributor.getStock().remove(productToUpdate);
                distributor.getStock().add(product);
            }
        }
        response.sendRedirect(request.getContextPath() + "/provider/HandleProductServlet");
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
