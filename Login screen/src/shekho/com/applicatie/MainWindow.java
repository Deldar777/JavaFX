package shekho.com.applicatie;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {
    private Stage window;

    public MainWindow(String username){
        window = new Stage();
        window.setHeight(200);
        window.setWidth(300);

        VBox vBox = new VBox();
        Label welcomeLabel = new Label("Welcome "+ username);
        vBox.getChildren().add(welcomeLabel);
        Scene scene = new Scene(vBox);

        window.setScene(scene);
        window.show();
    }
}
