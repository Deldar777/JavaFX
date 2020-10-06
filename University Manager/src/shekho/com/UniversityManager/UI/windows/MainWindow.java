package shekho.com.UniversityManager.UI.windows;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {
    private Stage window;

    public Stage getWindow() {
        return window;
    }

    public MainWindow(){
        window = new Stage();

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



    }
}
