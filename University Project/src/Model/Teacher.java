package Model;

import java.time.LocalDate;

public class Teacher extends User{
    public Teacher(String username, String password, String firstName, String lastName, LocalDate birthday) {
        super(username, password, firstName, lastName, birthday);
        accessLevel = AccesLevel.Editor;
    }
}
