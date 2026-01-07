package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class SleepManager {
    private MongoCollection<Document> collection;

    public SleepManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("sleep_unosi");
    }

    public void addUnos(SleepUnos unos) {
        collection.insertOne(unos.toDocument());
    }

    public List<SleepUnos> getUnosiZaKorisnika(String username) {
        List<SleepUnos> list = new ArrayList<>();

        for (Document d : collection.find(new Document("username", username))) {
            list.add(new SleepUnos(
                    d.getString("username"),
                    d.getDouble("sati")
            ));
        }
        return list;
    }

    public double getProsjekSpavanja(String username) {
        List<SleepUnos> list = getUnosiZaKorisnika(username);
        if (list.isEmpty()) return 0;

        double suma = 0;
        for (SleepUnos u : list) {
            suma += u.getSati();
        }
        return suma / list.size();
    }
}
