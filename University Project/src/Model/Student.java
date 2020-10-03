package Model;

import java.time.LocalDate;

public class Student extends User{
    public String group;
    private int java;
    private int php;
    private int csharp;
    private int python;

    public void setJavaGrade(int javaGrade){
        java = javaGrade;
    }
    public int getJavaGrade(){
        return java;
    }
    public void setPHPGrade(int phpGrade){
        php = phpGrade;
    }
    public int getPHPGrade(){
        return php;
    }
    public void setPythonGrade(int pythonGrade){
        python = pythonGrade;
    }
    public int getPythonGrade(){
        return python;
    }
    public void setCSHarpGrade(int csharpGrade){
        csharp = csharpGrade;
    }
    public int getCSharpGrade(){
        return csharp;
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
