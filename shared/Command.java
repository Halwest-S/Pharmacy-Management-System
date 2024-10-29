package shared;

import java.io.Serializable;

public class Command implements Serializable {
    private String action;
    private Object data;
    private String entityType; // "EMPLOYEE", "ITEM", "SALE", "RECOVERY"

    public Command(String action, String entityType, Object data) {
        this.action = action;
        this.entityType = entityType;
        this.data = data;
    }

    // Getters and setters
    public String getAction() { return action; }
    public Object getData() { return data; }
    public String getEntityType() { return entityType; }
}
