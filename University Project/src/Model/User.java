package Model;

import java.time.LocalDate;
import java.time.Period;

public class User {

    public String firstname;
    public String lastname;
    public String username;
    protected String password;
    protected int id;
    public AccesLevel accessLevel;
    public LocalDate birthday;
    public LocalDate currentDate;
    public int age;

    private static int counter = 0;

    public int setId(){
        counter++;
        return  counter;
    }

    public User(String username, String password, String firstName, String lastName, LocalDate birthday){
        this.username = username;
        this.password = password;
        this.firstname = firstName;
        this.lastname = lastName;
        this.birthday = birthday;
        currentDate = LocalDate.now();
        id = setId();
        age = getAge();
    }

    public int getId(){
        return id;
    }

    public boolean checkUsername(String input){return input.equals(username);}
    public boolean checkPassword(String input){
        return input.equals(password);
    }

    public boolean changePassword(String currentPassword,String newPassword){
        if(currentPassword.equals(newPassword))
            return false;
        password = newPassword;
        return true;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String fullName(){
        return firstname +" "+ lastname;
    }

    public int getAge(){
        if((birthday != null) && (currentDate != null)){
            return Period.between(birthday,currentDate).getYears();
        }
        else {
            return 0;
        }
    }

    public boolean canEdit(){
        return accessLevel == AccesLevel.Editor || accessLevel == AccesLevel.Admin;
    }
}
