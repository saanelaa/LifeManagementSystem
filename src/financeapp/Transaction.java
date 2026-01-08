package financeapp;

import org.bson.Document;

public class Transaction {
    private String username;
    private String type;
    private double amount;
    private String description;
    public Transaction(String username, String type, double amount, String
            description) {
        this.username = username;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public Document toDocument() {
        return new Document("username", username)
                .append("Type", type)
                .append("Amount", amount)
                .append("Description", description);
    }
    public String getUsername() { return  username; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
}
