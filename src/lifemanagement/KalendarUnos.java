package lifemanagement;

import org.bson.Document;

public class KalendarUnos {
    private String username;
    private String datum;
    private String naziv;
    private String tip;

    public KalendarUnos(String username, String datum, String naziv, String tip) {
        this.username = username;
        this.datum = datum;
        this.naziv = naziv;
        this.tip = tip;
    }

    public String getUsername() {
        return username;
    }

    public String getDatum() {
        return datum;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getTip() {
        return tip;
    }

    public Document toDocument() {
        return new Document("username", username)
                .append("datum", datum)
                .append("naziv", naziv)
                .append("tip", tip);
    }
}
