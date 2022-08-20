package fr.m2i.webdistributeur;

import fr.m2i.webdistributeur.entity.User;
import java.util.ArrayList;
import java.util.List;

public class Distributor {

    private static Distributor _instance;
    private List<Product> _stock;

    private Distributor() {
        _stock = new ArrayList();
    }

    public static Distributor getInstance() {

        if (_instance == null) {
            _instance = new Distributor();
        }

        return _instance;
    }

    public boolean isEnoughStock(int productId) {
        Product product = getProduct(productId);
        return product != null && product.getQuantity() >= 1;
    }

    public boolean isEnoughCredit(int productId, int credit) {
        Product product = getProduct(productId);
        return product != null && credit >= product.getPrice();
    }

    public void orderProduct(int idProduit, User user) {

        if (user == null) {
            System.out.println("commanderProduit|L'utilisateur est invalide");
            return;
        }

        if (isEnoughStock(idProduit) && isEnoughCredit(idProduit, user.getCredit())) {
            Product produit = getProduct(idProduit);

            produit.setQuantity(produit.getQuantity() - 1);
            user.setCredit(user.getCredit() - produit.getPrice());
        }
    }

    public List<Product> getStock() {
        return _stock;
    }

    public void setStock(List<Product> _stock) {
        this._stock = _stock;
    }

    public Product getProduct(int id) {
        for (Product produit : _stock) {
            if (produit.getId() == id) {
                return produit;
            }
        }
        return null;
    }
    
    public void test() {
        System.out.println("test");
    }

}
