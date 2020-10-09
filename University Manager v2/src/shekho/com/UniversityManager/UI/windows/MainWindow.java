package shekho.com.UniversityManager.UI.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shekho.com.UniversityManager.DAL.Database;
import shekho.com.UniversityManager.Models.User;
import shekho.com.UniversityManager.UI.scenes.StudentListScene;
import shekho.com.UniversityManager.UI.scenes.StyledScene;
import shekho.com.UniversityManager.UI.scenes.TeacherListScene;

public class MainWindow {
    private Stage window;

    public Stage getWindow() {
        return window;
    }

    public MainWindow(User user, Database database){
        //create a new window (stage)
        window = new Stage();

        //setup the global layout
        HBox layout = new HBox();

        //the menu
        VBox menu = new VBox();
        menu.setPadding(new Insets(80,20,20,20));
        menu.setSpacing(10);
        menu.getStyleClass().add("menu");
        Button studentsButton = new Button("Students");
        studentsButton.setMinWidth(150);
        Button teachersButton = new Button("Teachers");
        teachersButton.setMinWidth(150);
        menu.getChildren().addAll(studentsButton, teachersButton);

        studentsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StudentListScene sl = new StudentListScene(user,database);
                layout.getChildren().remove(1);
                layout.getChildren().add(sl.getScene().getRoot());
            }
        });

        teachersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TeacherListScene tl = new TeacherListScene();
                layout.getChildren().remove(1);
                layout.getChildren().add(tl.getScene().getRoot());
            }
        });

        StudentListScene studentListScene = new StudentListScene(user,database);
        layout.getChildren().addAll(menu,studentListScene.getScene().getRoot());


        Scene mainScene = new StyledScene(layout);

        window.setTitle("University Management");
        window.setScene(mainScene);
    }
}
