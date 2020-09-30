package nl.inholland.Applicatie;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.time.LocalDate;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {

         Database database = new Database();

        ObservableList<ToDoList> tasks = FXCollections.observableArrayList(database.getTasks());


        window.setTitle("To do list");
        window.setMinWidth(400);

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));

        TableView tableView = new TableView();


        TableColumn<ToDoList, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<ToDoList,String> completed = new TableColumn<>("Completed");
        completed.setCellValueFactory(new PropertyValueFactory<>("done"));



        tableView.getColumns().addAll(description,completed);
        tableView.setItems(tasks);



        TextField textFieldTask = new TextField();

        Button addButton = new Button("Add");
        addButton.getStyleClass().add("customButton");

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ToDoList task = new ToDoList(textFieldTask.getText(),false);
                tasks.add(task);
            }
        });




        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(textFieldTask,addButton);


        layout.getChildren().addAll(tableView,hBox);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("Resources/css/Style.css");
        window.setScene(scene);
        window.show();


        MenuBar menuBar = new MenuBar();
        Menu fileMenu= new Menu("File");
        Menu aboutMenu= new Menu("About");
        MenuItem loadItem= new MenuItem("Load...");
        MenuItem saveItem= new MenuItem("Save...");
        MenuItem aboutItem= new MenuItem("About");
        fileMenu.getItems().addAll(loadItem, saveItem);
        aboutMenu.getItems().add(aboutItem);

        loadItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });


        

        /*Database database = new Database();

        ObservableList<Person> people = FXCollections.observableArrayList(database.getPeople());

        window.setTitle("People Manager");
        window.setMinWidth(400);

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));

        TableView tableView = new TableView();

        TableColumn<Person, String> firstnameColumn = new TableColumn<>("First name");
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));

        TableColumn<Person,String> lastnameColumn = new TableColumn<>("Last name");
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Person,String> birthdayColumn = new TableColumn<>("Birthday");
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));

        tableView.getColumns().addAll(firstnameColumn,lastnameColumn,birthdayColumn);
        tableView.setItems(people);

        TextField textFieldFirstname = new TextField();
        textFieldFirstname.setPromptText("Firstname");

        TextField textFieldLastname = new TextField();
        textFieldFirstname.setPromptText("Lastname");

        DatePicker datePickerBirthday = new DatePicker();
        datePickerBirthday.setPromptText("Birth date");

        Button addButton = new Button("Add");
        addButton.getStyleClass().add("customButton");
        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("customButton");

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Person p = new Person(textFieldFirstname.getText(),textFieldLastname.getText(),datePickerBirthday.getValue());
                people.add(p);
            }
        });

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(textFieldFirstname,textFieldLastname,datePickerBirthday,addButton,deleteButton);


        layout.getChildren().addAll(tableView,hBox);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("Resources/css/Style.css");
        window.setScene(scene);
        window.show();*/




        /*window.setTitle("Login screen");

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
        window.show();*/
    }
}
