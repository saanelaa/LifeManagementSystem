package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class KorisnikManager {
    private MongoCollection<Document> usersCollection;

    public KorisnikManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        usersCollection = db.getCollection("users");
    }

    public boolean register(Korisnik korisnik) {
        Document existingUser = usersCollection.find(
                new Document("username", korisnik.getUsername())
        ).first();

        if (existingUser != null) {
            return false;
        }

        usersCollection.insertOne(korisnik.toDocument());
        return true;
    }

    public Korisnik login(String username, String password) {
        Document doc = usersCollection.find(
                new Document("username", username)
                        .append("password", password)
        ).first();

        if (doc == null) {
            return null;
        }

        return new Korisnik(
                doc.getString("username"),
                doc.getString("password"),
                doc.getString("theme")
        );
    }

    public void updateKorisnik(Korisnik korisnik) {

        usersCollection.updateOne(
                new Document("username", korisnik.getUsername()),
                new Document("$set", new Document()
                        .append("password", korisnik.getPassword())
                        .append("theme", korisnik.getTheme())
                )
        );
    }
}
