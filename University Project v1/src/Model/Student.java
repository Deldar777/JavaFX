package Model;

import java.time.LocalDate;

public class Student extends User{
    public String group;
    public int java;
    public int php;
    public int csharp;
    public int python;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getJava() {
        return java;
    }

    public void setJava(int java) {
        this.java = java;
    }

    public int getPhp() {
        return php;
    }

    public void setPhp(int php) {
        this.php = php;
    }

    public int getCsharp() {
        return csharp;
    }

    public void setCsharp(int csharp) {
        this.csharp = csharp;
    }

    public int getPython() {
        return python;
    }

    public void setPython(int python) {
        this.python = python;
    }

    public Student(String username, String password, String firstName, String lastName, LocalDate birthday) {
        super(username, password, firstName, lastName, birthday);
        accessLevel = AccesLevel.Basic;
        java = 0;
        php = 0;
        csharp = 0;
        python = 0;
    }

    public Student(String username, String password, String firstName, String lastName, LocalDate birthday,String group,int java,int csharp,int python,int php){
        super(username,password,firstName,lastName,birthday);
        this.group = group;
        this.java = java;
        this.csharp = csharp;
        this.python = python;
        this.php = php;
        accessLevel = AccesLevel.Basic;
    }
}
