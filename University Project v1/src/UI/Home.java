package UI;

import shekho.com.universityManager.DAL.Database;
import Model.Student;
import Model.Teacher;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Home {
    private Stage window;

    public Home(User user){

        Database database = new Database();
        ObservableList<Student> studentsList = FXCollections.observableArrayList(database.getStudents());
        ObservableList<User> teachersList = FXCollections.observableArrayList(database.getTeacher());

        window = new Stage();
        window.setTitle("Welcome "+ user.fullName());
        window.getIcons().add(new Image(getClass().getResourceAsStream("universityImage.png")));
        window.setHeight(400);
        window.setWidth(800);



        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,50,50,50));



        MenuBar menuBar = new MenuBar();
        Menu dashboard = new Menu("Dashboard");
        Menu studentsMenubar = new Menu("Student");
        Menu teachersMenubar = new Menu("Teacher");
        MenuItem studentsMenuItem = new MenuItem("Students");
        MenuItem addGrade = new MenuItem("Add Grade");
        MenuItem reports = new MenuItem("Reports");
        MenuItem teachersMenuItem = new MenuItem("Teachers");
        studentsMenubar.getItems().addAll(studentsMenuItem);
        teachersMenubar.getItems().add(teachersMenuItem);
        menuBar.getMenus().addAll(dashboard,studentsMenubar,teachersMenubar);

        if(user.canEdit()){
            studentsMenubar.getItems().addAll(addGrade,reports);
        }




        studentsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TableView studentsTableView = new TableView();
                TableColumn<Student,Integer> id = new TableColumn<>("ID");
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                TableColumn<Student,String> firstname = new TableColumn<>("Firstname");
                firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
                TableColumn<Student,String> lastname = new TableColumn<>("Lastname");
                lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                TableColumn<Student,Integer> age = new TableColumn<>("Age");
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                TableColumn<Student,String> group = new TableColumn<>("Group");
                group.setCellValueFactory(new PropertyValueFactory<>("group"));

                TableColumn<Student,Integer> javaGrade  =new TableColumn<>("Java");
                javaGrade.setCellValueFactory(new PropertyValueFactory<>("java"));
                TableColumn<Student,Integer> csharpGrade  =new TableColumn<>("CSharp");
                csharpGrade.setCellValueFactory(new PropertyValueFactory<>("csharp"));
                TableColumn<Student,Integer> phpGrade  =new TableColumn<>("PHP");
                phpGrade.setCellValueFactory(new PropertyValueFactory<>("php"));
                TableColumn<Student,Integer> pythonGrade  =new TableColumn<>("Python");
                pythonGrade.setCellValueFactory(new PropertyValueFactory<>("python"));

                if(user.canEdit()){

                    studentsTableView.getColumns().addAll(id,firstname,lastname,age,group,javaGrade,csharpGrade,phpGrade,pythonGrade);
                }else{
                    studentsTableView.getColumns().addAll(id,firstname,lastname,age,group);
                }

                studentsTableView.setItems(studentsList);
                borderPane.setCenter(studentsTableView);
            }
        });

        teachersMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                TableView teachersTableView = new TableView();

                TableColumn<Teacher,Integer> id = new TableColumn<>("ID");
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                TableColumn<Teacher,String> firstname = new TableColumn<>("Firstname");
                firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
                TableColumn<Teacher,String> lastname = new TableColumn<>("Lastname");
                lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                TableColumn<Teacher,Integer> age = new TableColumn<>("Age");
                age.setCellValueFactory(new PropertyValueFactory<>("age"));

                teachersTableView.getColumns().addAll(id,firstname,lastname,age);
                teachersTableView.setItems(teachersList);
                borderPane.setCenter(teachersTableView);
            }
        });

        borderPane.setTop(menuBar);
        Scene scene = new Scene(borderPane);

        window.setScene(scene);
        window.show();
    }
}
