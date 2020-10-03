package Model;

import DAL.Database;

import java.time.LocalDate;
import java.time.Period;

public class User {

    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected int id;
    protected AccesLevel accessLevel;
    public LocalDate birthday;
    public LocalDate currentDate;

    private static int counter = 0;

    public int setId(){
        counter++;
        return  counter;
    }

    public User(String username, String password, String firstName, String lastName, LocalDate birthday){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        currentDate = LocalDate.now();
        id = setId();
    }

    public int getId(){
        return id;
    }

    public boolean checkPassword(String input){
        return input.equals(password);
    }

    public boolean changePassword(String currentPassword,String newPassword){
        if(currentPassword.equals(newPassword))
            return false;
        password = newPassword;
        return true;
    }


    public String fullName(){
        return firstName +" "+ lastName;
    }

    public int getAge(){
        if((birthday != null) && (currentDate != null)){
            return Period.between(birthday,currentDate).getYears();
        }
        else {
            return 0;
        }
    }
}
