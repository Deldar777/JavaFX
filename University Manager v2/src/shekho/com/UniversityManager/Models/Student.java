package shekho.com.UniversityManager.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends User{

    private Group group;
    private List<Grade> grades = new ArrayList<>();
    //private String coachName;


    public Student(String username, String password, String firstname, String lastname, String email, Group group) {
        super(username, password, firstname, lastname, email);
        this.group = group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public List<Grade> getGrades() {
        return grades;
    }
}
