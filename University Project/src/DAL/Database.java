package DAL;

import Model.Manager;
import Model.Student;
import Model.Teacher;
import Model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users;

    public Database(){
        users = new ArrayList<>();
        createUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    public void createUsers(){
        Manager manger = new Manager("deldar","deldar12","Deldar","Shekho", LocalDate.of(1989,10,13));
        Teacher teacher1 = new Teacher("david","david12","David","Taylor", LocalDate.of(1965,6,15));
        Teacher teacher2 = new Teacher("sophey","sophey12","Sophey","Anderson",LocalDate.of(1987,6,1));
        Teacher teacher3 = new Teacher("james","james12","James","Jordon",LocalDate.of(1956,3,19));
        Teacher teacher4 = new Teacher("susan","susan12","Susan","jackson",LocalDate.of(1978,12,25));
        Teacher teacher5 = new Teacher("mary","mary12","Mary","Lay",LocalDate.of(1971,9,4));
        Student student1 = new Student("emma","emma12","Emma","Smith",LocalDate.of(1997,4,12),"IT-02-A",54,50,66,54);
        Student student2 = new Student("jack","brown12","Jack","Brown",LocalDate.of(1997,4,12),"IT-02-A",72,68,43,95);
        Student student3 = new Student("michael","garcia12","Michael","Garcia",LocalDate.of(1999,1,11),"IT-02-A",45,71,43,95);
        Student student4 = new Student("john","john12","John","Miller",LocalDate.of(2001,10,27),"IT-02-A",72,68,43,55);
        Student student5 = new Student("lisa","lisa12","Lisa","Jones",LocalDate.of(2000,4,29),"IT-02-A",62,70,88,95);
        Student student6 = new Student("linda","linda12","Linda","Martinez",LocalDate.of(2002,1,17),"IT-02-A",72,58,43,45);
        Student student7 = new Student("richard","richard","Richard","Davis",LocalDate.of(1997,9,22),"IT-02-A",78,78,43,25);
        Student student8 = new Student("mark","mark12","Mark","Lopez",LocalDate.of(1996,12,9),"IT-02-A",100,58,77,95);
        Student student9 = new Student("debora","debora","Debora","Hernandez",LocalDate.of(1995,2,25),"IT-02-A",48,68,43,45);
        Student student10 = new Student("rick","rick12","Rick","Moore",LocalDate.of(2000,3,16),"IT-02-A",72,68,47,44);

        users.add(manger);
        users.add(teacher1);
        users.add(teacher2);
        users.add(teacher3);
        users.add(teacher4);
        users.add(teacher5);
        users.add(student1);
        users.add(student2);
        users.add(student3);
        users.add(student4);
        users.add(student5);
        users.add(student6);
        users.add(student7);
        users.add(student8);
        users.add(student9);
        users.add(student10);
    }
}
