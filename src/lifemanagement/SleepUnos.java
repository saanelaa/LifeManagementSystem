package lifemanagement;

import org.bson.Document;

public class SleepUnos {

    private String username;
    private double sati;
    private String datum;

    public SleepUnos(String username, double sati, String datum) {
        this.username = username;
        this.sati = sati;
        this.datum = datum;
    }

    public Document toDocument() {
        return new Document("username", username)
                .append("sati", sati)
                .append("datum", datum);
    }

    public String getUsername() {
        return username;
    }

    public double getSati() {
        return sati;
    }

    public String getDatum() {
        return datum;
    }
}