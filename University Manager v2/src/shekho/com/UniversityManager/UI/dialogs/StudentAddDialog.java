package shekho.com.UniversityManager.UI.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shekho.com.UniversityManager.DAL.Database;
import shekho.com.UniversityManager.Models.Group;
import shekho.com.UniversityManager.Models.Student;

public class StudentAddDialog {

    private Stage stage;

    public Student getStudent() {
        return student;
    }

    private Student student;

    public Stage getStage() {
        return stage;
    }

    public StudentAddDialog(Database database){

        stage = new Stage();

        ObservableList<Group> groups =
                FXCollections.observableArrayList(
                        database.getGroups()
                );

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(20);

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last name");
        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");

        ComboBox groupBox = new ComboBox(groups);
        groupBox.setPromptText("Group");

        HBox buttons = new HBox();

        Button addStudentButton = new Button("Add Student");
        addStudentButton.setDefaultButton(true);
        Button cancelButton = new Button("Cancel");
        buttons.getChildren().addAll(addStudentButton, cancelButton);

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        addStudentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                student = new Student(firstNameField.getText(),lastNameField.getText(),firstNameField.getText(),lastNameField.getText(),emailField.getText(), (Group)groupBox.getValue());
                stage.close();
            }
        });

        buttons.setSpacing(10);

        layout.getChildren().addAll(firstNameField, lastNameField, emailField, groupBox, buttons);

        Scene mainScene = new Scene(layout);

        stage.setMinWidth(250);
        stage.setTitle("Add student");
        stage.setScene(mainScene);
    }


}
