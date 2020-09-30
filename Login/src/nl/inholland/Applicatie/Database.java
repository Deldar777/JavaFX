package nl.inholland.Applicatie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<ToDoList> tasks = new ArrayList<>();


    public Database(){
        tasks.add(new ToDoList("Feed the cat",false));
        tasks.add(new ToDoList("study",false));
        /*people.add(new Person("Piet","pauls", LocalDate.of(1977,02,4)));
        people.add(new Person("Jan","Paul", LocalDate.of(1990,02,4)));
        people.add(new Person("Bakker","Paulsma", LocalDate.of(1980,02,4)));*/
    }

    public List<ToDoList> getTasks() {
        return tasks;
    }
}
