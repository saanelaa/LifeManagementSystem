package lifemanagement;

import org.bson.Document;

public class MoodUnos {
    private String username;
    private int mood;
    private String datum;

    public MoodUnos(String username, int mood, String datum) {
        this.username = username;
        this.mood = mood;
        this.datum = datum;
    }

    public String getUsername() {
        return username;
    }

    public int getMood() {
        return mood;
    }

    public String getDatum() {
        return datum;
    }

    public Document toDocument() {
        return new Document("username", username)
                .append("mood", mood)
                .append("datum", datum);
    }
}
