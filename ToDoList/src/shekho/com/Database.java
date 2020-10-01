package shekho.com;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public Database(){
        tasks = new ArrayList<>();
        tasks.add(new Task("Feed the cat"));
        tasks.add(new Task("Study"));
        tasks.add(new Task("Do Groceries"));
    }
}
