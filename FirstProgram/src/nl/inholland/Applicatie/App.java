package nl.inholland.Applicatie;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {

        window.setTitle("Login screen");

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        Label userLabel = new Label("Username:");
        GridPane.setConstraints(userLabel, 0, 0);

        TextField userInput = new TextField();
        userInput.setPromptText("Username");
        GridPane.setConstraints(userInput, 1, 0);
        System.out.println(userInput.getText());

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        TextField userPassword = new TextField();
        userPassword.setPromptText("Password");
        GridPane.setConstraints(userPassword, 1, 1);
        System.out.println(userInput.getText());


        Button loginButton = new Button("Log in");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setVisible(false);

        //create a string property
        StringProperty passwordFieldProperty = userPassword.textProperty();

        Label visiblePassword = new Label();
        GridPane.setConstraints(visiblePassword, 0, 3);
        visiblePassword.textProperty().bind(passwordFieldProperty);


        //listening to changes

        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

               String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";

               if(newValue.matches(regex)){
                   loginButton.setVisible(true);
               }
            }
        });



        gridPane.getChildren().addAll(userLabel, userInput, passwordLabel, userPassword, loginButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);


        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
                window.close();
            }
        });
        window.show();
    }
}
