package shekho.com;

import java.io.Serializable;

public class Task implements Serializable {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        completed = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
