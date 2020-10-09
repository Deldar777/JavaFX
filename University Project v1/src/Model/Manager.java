package Model;

import java.time.LocalDate;

public class Manager extends User{
    public Manager(String username, String password, String firstName, String lastName, LocalDate birthday) {
        super(username, password, firstName, lastName, birthday);
        accessLevel = AccesLevel.Admin;
    }
}
