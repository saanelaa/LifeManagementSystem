package lifemanagement;

import org.bson.Document;

public class Korisnik {

    private String username;
    private String password;
    private String theme;

    public Korisnik(String username, String password, String theme) {
        this.username = username;
        this.password = password;
        this.theme = theme;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTheme() {
        return theme;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Document toDocument() {
        return new Document("username", username)
                .append("password", password)
                .append("theme", theme);
    }
}