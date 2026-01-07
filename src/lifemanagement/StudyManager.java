package lifemanagement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class StudyManager {
    private MongoCollection<Document> collection;

    public StudyManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("study_unosi");
    }

    public void addUnos(StudyUnos unos) {
        collection.insertOne(unos.toDocument());
    }

    public List<StudyUnos> getUnosiZaKorisnika(String username) {
        List<StudyUnos> list = new ArrayList<>();

        for (Document d : collection.find(new Document("username", username))) {
            list.add(new StudyUnos(
                    d.getString("username"),
                    d.getDouble("sati")
            ));
        }
        return list;
    }

    public double getProsjekUcenja(String username) {
        List<StudyUnos> list = getUnosiZaKorisnika(username);
        if (list.isEmpty()) return 0;

        double suma = 0;
        for (StudyUnos u : list) {
            suma += u.getSati();
        }
        return suma / list.size();
    }
}
