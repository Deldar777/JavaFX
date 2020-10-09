package shekho.com.UniversityManager.UI.dialogs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import shekho.com.UniversityManager.Models.Grade;
import shekho.com.UniversityManager.Models.Student;
import shekho.com.UniversityManager.Models.User;

public class GradeAddDialog {

    private Grade grade;

    public Grade getGrade() {
        return grade;
    }

    private Stage stage;


    public Stage getStage() {
        return stage;
    }

    public GradeAddDialog(){
        stage = new Stage();

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(20);

        TextField courseField = new TextField();
        courseField.setPromptText("Course");
        TextField gradeField = new TextField();
        gradeField.setPromptText("Grade");

        HBox buttons = new HBox();

        Button addGradeButton = new Button("Add Grade");
        addGradeButton.setDefaultButton(true);
        Button cancelButton = new Button("Cancel");
        buttons.getChildren().addAll(addGradeButton, cancelButton);

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        addGradeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                grade = new Grade(courseField.getText(),Double.valueOf(gradeField.getText()));
            }
        });


        buttons.setSpacing(10);

        layout.getChildren().addAll(courseField, gradeField, buttons);

        Scene mainScene = new Scene(layout);

        stage.setMinWidth(250);
        stage.setTitle("Add grade");
        stage.setScene(mainScene);
    }
}
