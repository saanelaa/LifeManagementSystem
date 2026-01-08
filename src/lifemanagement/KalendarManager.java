package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class KalendarManager {
    private MongoCollection<Document> kalendarCollection;

    public KalendarManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        kalendarCollection = db.getCollection("kalendar_unosi");
    }

    public void addUnos(KalendarUnos unos) {
        kalendarCollection.insertOne(unos.toDocument());
    }

    public List<KalendarUnos> getUnosiZaKorisnika(String username) {
        List<KalendarUnos> list = new ArrayList<>();

        for (Document d : kalendarCollection.find(new Document("username", username))) {
            list.add(new KalendarUnos(
                    d.getString("username"),
                    d.getString("datum"),
                    d.getString("naziv"),
                    d.getString("tip")
            ));
        }
        return list;
    }
}
