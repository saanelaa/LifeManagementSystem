package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MoodManager {

    private MongoCollection<Document> moodCollection;

    public MoodManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        moodCollection = db.getCollection("mood_unosi");
    }

    public void addUnos(MoodUnos unos) {
        moodCollection.insertOne(unos.toDocument());
    }

    public List<MoodUnos> getUnosiZaKorisnika(String username) {
        List<MoodUnos> list = new ArrayList<>();

        for (Document d : moodCollection.find(new Document("username", username))) {
            list.add(new MoodUnos(
                    d.getString("username"),
                    d.getInteger("mood"),
                    d.getString("datum")
            ));
        }

        return list;
    }
}
