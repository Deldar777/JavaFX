package shekho.com.UniversityManager.Models;

import java.time.LocalDate;

public class Manager extends User{


    public Manager(String username, String password, String firstname, String lastname, String email) {
        super(username, password, firstname, lastname, email);
        accessLevel = AccessLevel.Admin;
    }
}
