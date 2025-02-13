package shekho.com.UniversityManager.UI.scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import shekho.com.UniversityManager.DAL.Database;
import shekho.com.UniversityManager.Models.Grade;
import shekho.com.UniversityManager.Models.Student;
import shekho.com.UniversityManager.Models.User;
import shekho.com.UniversityManager.UI.dialogs.GradeAddDialog;
import shekho.com.UniversityManager.UI.dialogs.StudentAddDialog;

import java.util.ArrayList;
import java.util.List;

public class StudentListScene {

    private Scene scene;
    private ObservableList<Student>students;
    private ObservableList<Grade> grades;
    private Student student;

    public Scene getScene() {
        return scene;
    }

    public StudentListScene(User user,Database database){


        if(user instanceof Student){
             student = (Student)user;
        }


        //Initialize data
        students = FXCollections.observableArrayList(database.getStudents());
        grades = FXCollections.observableArrayList(new ArrayList<>());

        // Setup the layout. 2 tables next to eachother with forms below
        // students on the left, grades on the right
        HBox layout = new HBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(20);

        // The student pane
        VBox studentPane = new VBox();

        Label heading = new Label();
        heading.setText("Students");
        heading.getStyleClass().add("header");

        // Setting up the student table view
        TableView<Student> studentTableView = new TableView<>();
        studentTableView.setEditable(true);
        studentTableView.getSelectionModel().setCellSelectionEnabled(false);
        studentTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(130);
        emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

        TableColumn groupCol = new TableColumn("Group");
        groupCol.setMinWidth(100);
        groupCol.setCellValueFactory(new PropertyValueFactory<Student, String>("group"));

        TableColumn<Student, String> coachCol = new TableColumn<>("Coach");
        coachCol.setMinWidth(150);
        coachCol.setCellValueFactory(s ->new SimpleStringProperty(s.getValue().getGroup().getCoach().fullName()));
        //coachCol.setCellValueFactory(new PropertyValueFactory<Student, String>("coachName"));

        studentTableView.getColumns().addAll(firstNameCol, lastNameCol, emailCol, groupCol, coachCol);
        studentTableView.setItems(students);

        // Adding the buttons for the student
        HBox studentMenu = new HBox();
        studentMenu.setPadding(new Insets(20,0,0,0));
        studentMenu.setSpacing(10);

        Button addStudentButton = new Button("Add Student");
        Button editStudentButton = new Button("Edit Student");
        Button deleteStudentButton = new Button("Delete Student");
        studentMenu.getChildren().addAll(addStudentButton, editStudentButton, deleteStudentButton);

        addStudentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StudentAddDialog studentAddDialog = new StudentAddDialog(database);
                studentAddDialog.getStage().showAndWait();
                if(studentAddDialog.getStudent() != null){
                    students.add(studentAddDialog.getStudent());
                }
            }
        });


        // On to the right side of the screen, the grades pane
        VBox gradePane = new VBox();

        Label gradeheading = new Label();
        gradeheading.setText("Grades");
        gradeheading.getStyleClass().add("header");

        // Setting up the grades table view
        TableView<Grade> gradeTableView = new TableView<>();
        gradeTableView.setEditable(true);
        gradeTableView.getSelectionModel().setCellSelectionEnabled(false);
        gradeTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn courseCol = new TableColumn("Course");
        courseCol.setMinWidth(150);
        courseCol.setCellValueFactory(new PropertyValueFactory<Student, String>("course"));

        TableColumn gradeCol = new TableColumn("Grade");
        gradeCol.setMinWidth(120);
        gradeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("grade"));

        gradeTableView.getColumns().addAll(courseCol, gradeCol);
        gradeTableView.setItems(grades);


       /* studentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> observableValue, Student student, Student t1) {
                grades.clear();
                grades.addAll(t1.getGrades());
            }
        });*/

        studentTableView.setRowFactory(stv ->{
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if(event.getClickCount() == 2 && !row.isEmpty()){
                    grades.clear();
                    student = row.getItem();
                    grades.addAll(student.getGrades());

                    gradeTableView.refresh();
                }
            });

            return row;
        });

        HBox gradeMenu = new HBox();
        gradeMenu.setPadding(new Insets(20,0,0,0));
        gradeMenu.setSpacing(10);

        Button addGradeButton = new Button("Add Grade");
        Button editGradeButton = new Button("Edit Grade");
        Button deleteGradeButton = new Button("Delete Grade");
        gradeMenu.getChildren().addAll(addGradeButton, editGradeButton, deleteGradeButton);


        addGradeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GradeAddDialog gaw = new GradeAddDialog();
                gaw.getStage().initModality(Modality.APPLICATION_MODAL);
                gaw.getStage().showAndWait();

                if(gaw.getGrade() != null && student != null){
                    grades.add(gaw.getGrade());
                    student.getGrades().add(gaw.getGrade());
                }
            }
        });

        gradePane.getChildren().addAll(gradeheading, gradeTableView, gradeMenu);
        studentPane.getChildren().addAll(heading, studentTableView, studentMenu);


        // Adding the student and grade panes to the global layout
        layout.getChildren().addAll(studentPane, gradePane);

        // Creating a new scene
        scene = new Scene(layout);
    }
}
