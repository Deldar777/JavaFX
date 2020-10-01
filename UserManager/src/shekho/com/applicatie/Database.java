package shekho.com.applicatie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users;

    public Database(){
        users = new ArrayList<>();
        users.add(new User("Jan","Bakker", LocalDate.of(1980,5,22)));
        users.add(new User("Ryan","Erfmann", LocalDate.of(2000,1,1)));
        users.add(new User("Piet","Paulsma", LocalDate.of(1970,4,12)));
    }

    public List<User> getUsers() {
        return users;
    }
}
