package fr.m2i.webdistributeur.dao;

import fr.m2i.webdistributeur.entity.Role;
import fr.m2i.webdistributeur.entity.User;
import fr.m2i.webdistributeur.utils.UserDb;
import java.util.List;
import java.util.logging.Logger;

public class UserDao {

    private static Logger logger = Logger.getLogger(UserDao.class.getName());
    private UserDb userDb;

    public UserDao(UserDb userDb) {
        this.userDb = userDb;
    }

    public List<User> findAll() {
        return userDb.getUsers();
    }

    public User findById(int id) {
        for (User user : userDb.getUsers()) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public User findByRole(Role role) {
        for (User user : userDb.getUsers()) {
            if (user.getRole().equals(role)) {
                return user;
            }
        }

        return null;
    }

    public User create(User user) {

        if (user == null) {
            System.out.println("create|L'utilisateur est invalide");
            return null;
        }

        if (findById(user.getId()) != null) {
            System.out.println("create|L'utilisateur avec l'id: " + user.getId() + " exist déjà");
            return null;
        }

        userDb.getUsers().add(user);

        return user;
    }

    public User update(User user) {

        if (user == null) {
            System.out.println("update|L'utilisateur est invalide");
            return null;
        }

        for (int i = 0; i < userDb.getUsers().size(); i++) {
            User userToUpdate = userDb.getUsers().get(i);

            if (user.getId() == userToUpdate.getId()) {
                userDb.getUsers().remove(userToUpdate);
                userDb.getUsers().add(user);
                return user;
            }
        }
        
        return null;
    }

    public boolean delete(User user) {
        return userDb.getUsers().remove(user);
    }
}
