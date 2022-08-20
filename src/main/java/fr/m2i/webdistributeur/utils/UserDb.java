package fr.m2i.webdistributeur.utils;

import fr.m2i.webdistributeur.entity.Role;
import fr.m2i.webdistributeur.entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserDb {

    private static UserDb _instance;
    private static String _dbUser = "root";
    private static String _dbPass = "root";

    private List<User> users = new ArrayList() {
        {
            add(new User(1, Role.ADMIN, "admin@gmail.com", "admin"));
            add(new User(2, Role.PROVIDER, "provider@gmail.com", "provider"));
            add(new User(3, Role.CUSTOMER, "user@gmail.com", "user"));
        }
    };

    private UserDb() {

    }

    public static UserDb getInstance(String dbUser, String dbPass) {

        if (!_dbUser.equals(dbUser) || !_dbPass.equals(dbPass)) {
            System.out.println("Les identifiants pour accéder à la base de donnée sont erronés");
            return null;
        }

        if (_instance == null) {
            _instance = new UserDb();
        }

        return _instance;
    }

    public User checkUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public List<User> getUsers() {
        return users;
    }
}
