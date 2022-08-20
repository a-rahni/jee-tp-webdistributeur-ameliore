package fr.m2i.webdistributeur.entity;

// Cette classe doit énumérer les roles indiqué dans le sujet
public enum Role {
    CUSTOMER("customer"),
    PROVIDER("customer", "provider"),
    ADMIN("customer", "provider", "admin");

    private String[] uris;

    Role(String... uris) {
        this.uris = uris;
    }

    public String[] getUris() {
        return uris;
    }

    public static boolean checkUri(Role role, String uri) {
        if (uri == null) {
            return false;
        }

        String cleaned = uri.substring(0, uri.indexOf('/'));

        for (String roleUri : role.getUris()) {
            if (roleUri.equals(cleaned)) {
                return true;
            }
        }

        return false;
    }
}
