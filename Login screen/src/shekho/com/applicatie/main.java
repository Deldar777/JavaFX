package shekho.com.applicatie;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class main extends Application {
    public static void start(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Login Screen");
        window.getIcons().add(new Image(getClass().getResourceAsStream("image.png")));
        window.show();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(40));

        gridPane.setVgap(20);
        gridPane.setHgap(20);

        Label labelUsername = new Label("Username: ");
        GridPane.setConstraints(labelUsername,0,0);

        Label labelPassword = new Label("Password");
        GridPane.setConstraints(labelPassword,0,1);

        TextField textFieldUsername = new TextField();
        textFieldUsername.setPromptText("Username");
        GridPane.setConstraints(textFieldUsername,1,0);

        TextField textFieldPassword = new TextField();
        textFieldPassword.setPromptText("password");
        GridPane.setConstraints(textFieldPassword,1,1);

        Button buttonLogin = new Button("Log in");
        buttonLogin.setPrefWidth(80);
        GridPane.setConstraints(buttonLogin,1,2);
        buttonLogin.setVisible(false);

        StringProperty stringPropertyPasswordField = textFieldPassword.textProperty();


        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";


        stringPropertyPasswordField.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                if (s.matches(regex)){
                    buttonLogin.setVisible(true);
                }else{
                    buttonLogin.setVisible(false);
                }
            }
        });


        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainWindow mainWindow = new MainWindow(textFieldUsername.getText());
                window.close();
            }
        });



        gridPane.getChildren().addAll(labelPassword,labelUsername,textFieldPassword,textFieldUsername,buttonLogin);
        Scene scene  = new Scene(gridPane);
        window.setScene(scene);
    }
}
