package shekho.com.UniversityManager.DAL;

import shekho.com.UniversityManager.Models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<User> users = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();
    public List<Group> getGroups() { return groups; }

    public Database(){
        createUsers();
    }




    public void createUsers(){
        Manager manger = new Manager("deldar","deldar12","Deldar","Shekho","willem.wiltenburg@inholland.nl");

        Teacher david = new Teacher("david","david12","David","Taylor","willem.wiltenburg@inholland.nl",5000);
        Teacher teacher2 = new Teacher("sophey","sophey12","Sophey","Anderson","willem.wiltenburg@inholland.nl",5000);
        Teacher teacher3 = new Teacher("james","james12","James","Jordon","willem.wiltenburg@inholland.nl",5000);
        Teacher teacher4 = new Teacher("susan","susan12","Susan","jackson","willem.wiltenburg@inholland.nl",5000);
        Teacher teacher5 = new Teacher("mary","mary12","Mary","Lay","willem.wiltenburg@inholland.nl",5000);

        // groups
        Group inf2a = new Group("INF2A");
        inf2a.setCoach(david);
        groups.add(inf2a);

        /*Student student1 = new Student("emma","emma12","Emma","Smith","willem.wiltenburg@inholland.nl",inf2a);
        student1.getGrades().add(new Grade("Java Fundamentals", 10));
        student1.getGrades().add(new Grade("Maths", 7));


        Student student2 = new Student("jack","brown12","Jack","Brown","willem.wiltenburg@inholland.nl",inf2a);
        student2.getGrades().add(new Grade("English", 8));
        student2.getGrades().add(new Grade("Programming 1", 9));

        Student student3 = new Student("michael","garcia12","Michael","Garcia","willem.wiltenburg@inholland.nl",inf2a);
        student3.getGrades().add(new Grade("English", 8));
        student3.getGrades().add(new Grade("Programming 1", 9));

        Student student4 = new Student("john","john12","John","Miller","willem.wiltenburg@inholland.nl",inf2a);
        student4.getGrades().add(new Grade("English", 8));
        student4.getGrades().add(new Grade("Programming 1", 9));

        Student student5 = new Student("lisa","lisa12","Lisa","Jones","willem.wiltenburg@inholland.nl",inf2a);
        student5.getGrades().add(new Grade("English", 8));
        student5.getGrades().add(new Grade("Programming 1", 9));*/


        users.add(manger);
        users.add(david);
        users.add(teacher2);
        users.add(teacher3);
        users.add(teacher4);
        users.add(teacher5);

        /*users.add(student1);
        users.add(student2);
        users.add(student3);
        users.add(student4);
        users.add(student5);*/
    }

    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        for (User u:users
        ) {
            if(u instanceof Student){
                students.add((Student)u);
            }
        }

        return students;
    }

    public List<Teacher> getTeachers(){
        List<Teacher> teachers = new ArrayList<>();
        for (User u:users
        ) {
            if(u instanceof Teacher){
                teachers.add((Teacher) u);
            }
        }
        return teachers;
    }

    public User validateLogin(String username, String password){

        User user;

        for (User u:users
        ) {

            if (u.getUsername().equals(username)){
                user = u;
                if (user.getPassword().equals(password))
                    return user;
            }
        }

        return null;
    }

}
