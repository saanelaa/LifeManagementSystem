package lifemanagement;

import org.bson.Document;

public class StudyUnos {
    private String username;
    private double sati;

    public StudyUnos(String username, double sati) {
        this.username = username;
        this.sati = sati;
    }

    public Document toDocument() {
        return new Document("username", username)
                .append("sati", sati);
    }

    public String getUsername() {
        return username;
    }

    public double getSati() {
        return sati;
    }
}
