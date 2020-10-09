package shekho.com.UniversityManager.Models;

import java.time.LocalDate;

public class Teacher extends User{
    private double salary;

    public Teacher(String username, String password, String firstname, String lastname, String email,double salary) {
        super(username, password, firstname, lastname, email);
        accessLevel = AccessLevel.Editor;
        this.salary = salary;
    }

    public String fullName(){
        return firstname +" "+ lastname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
