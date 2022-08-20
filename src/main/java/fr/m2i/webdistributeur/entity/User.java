package fr.m2i.webdistributeur.entity;

public class User {

    // déclarer les attributs ci-dessous

    private int id;
    private Role role;
    private String email;
    private String password;
    private int credit;

    public User() {

    }

    public User(int id, Role role, String email, String password) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public User(int id, Role role, String email, String password, int credit) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
        this.credit = credit;
    }

    // implémenter les getter / setter ci-dessous

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String motPasse) {
        this.password = motPasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
