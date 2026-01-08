package financeapp;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class TransactionManager {

    private final MongoCollection<Document> collection;

    public TransactionManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        if (db == null) {
            throw new IllegalStateException(
                    "MongoDatabase je NULL – MongoDBConnection.getDatabase() ne radi"
            );
        }

        collection = db.getCollection("transactions");

        if (collection == null) {
            throw new IllegalStateException(
                    "MongoCollection je NULL – kolekcija 'transactions' ne postoji"
            );
        }
    }

    public void addTransaction(Transaction t) {
        collection.insertOne(t.toDocument());
    }

    public ArrayList<Transaction> getTransactionsForUser(String username) {

        ArrayList<Transaction> list = new ArrayList<>();

        MongoCursor<Document> cursor =
                collection.find(new Document("username", username)).iterator();

        while (cursor.hasNext()) {
            Document d = cursor.next();
            String type = d.getString("Type");
            String description = d.getString("Description");

            Double amountObj = d.getDouble("Amount");
            double amount = amountObj != null ? amountObj : 0.0;

            if (type == null) continue;

            list.add(new Transaction(
                    username,
                    type,
                    amount,
                    description
            ));
        }
        return list;
    }
    public double getTotalIncome(String username) {
        double total = 0;
        for (Transaction t : getTransactionsForUser(username)) {
            if ("Prihod".equalsIgnoreCase(t.getType())) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getTotalExpense(String username) {
        double total = 0;
        for (Transaction t : getTransactionsForUser(username)) {
            if ("Rashod".equalsIgnoreCase(t.getType())) {
                total += t.getAmount();
            }
        }
        return total;
    }
}
