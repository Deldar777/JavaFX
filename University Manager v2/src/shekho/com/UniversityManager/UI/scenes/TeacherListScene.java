package shekho.com.UniversityManager.UI.scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import shekho.com.UniversityManager.DAL.Database;
import shekho.com.UniversityManager.Models.Teacher;
import javafx.scene.control.TableView;
import shekho.com.UniversityManager.Models.User;


public class TeacherListScene {

    private Scene scene;

    private TableView<Teacher> table = new TableView<>();
    private Database db;

    public Scene getScene() {
        return scene;
    }

    public TeacherListScene(){

        db = new Database();
        ObservableList<Teacher> teachers = FXCollections.observableArrayList(db.getTeachers());

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));

        Label heading = new Label();
        heading.setText("Teachers");
        heading.getStyleClass().add("header");

        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("firstname"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(220);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("lastname"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(250);
        emailCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("email"));

        TableColumn groupCol = new TableColumn("Salary");
        groupCol.setMinWidth(250);
        groupCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("salary"));

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, groupCol);
        table.setItems(teachers);


        HBox studentMenu = new HBox();
        studentMenu.setPadding(new Insets(20,0,0,0));
        studentMenu.setSpacing(10);

        Button addTeacherButton = new Button("Add Teacher");
        Button editTeacherButton = new Button("Edit Teacher");
        Button deleteTeacherButton = new Button("Delete Teacher");
        studentMenu.getChildren().addAll(addTeacherButton, editTeacherButton, deleteTeacherButton);

        layout.getChildren().addAll(heading, table, studentMenu);

        scene = new StyledScene(layout);

    }
}
