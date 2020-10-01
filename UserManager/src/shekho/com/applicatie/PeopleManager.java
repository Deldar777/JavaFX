package shekho.com.applicatie;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class PeopleManager extends Application {

    public static void start(String[] chain){
        launch(chain);
    }
    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("People Manager");
        window.getIcons().add(new Image(getClass().getResourceAsStream("managerImage.png")));
        window.setMinWidth(250);

        Database db = new Database();
        ObservableList<User> users = FXCollections.observableArrayList(db.getUsers());


        VBox layout = new VBox();
        layout.setPadding(new Insets(10));

        TableView tableView = new TableView();

        TableColumn<String,User> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        TableColumn<String,User> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        TableColumn<String,User> birthDateColumn = new TableColumn<>("Birth Date");
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        tableView.getColumns().addAll(firstNameColumn,lastNameColumn,birthDateColumn);
        tableView.setItems(users);

        TextField textFieldFirstname = new TextField();
        textFieldFirstname.setPromptText("First name");
        TextField textFieldLastname = new TextField();
        textFieldLastname.setPromptText("Last name");
        DatePicker textFieldBirthDate = new DatePicker();
        textFieldBirthDate.setPromptText("Birth date");

        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");

       addButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               users.add(new User(textFieldFirstname.getText(),textFieldLastname.getText(),textFieldBirthDate.getValue()));
           }
       });

       deleteButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               ObservableList<User> selectedUsers = tableView.getSelectionModel().getSelectedItems();
               users.removeAll(selectedUsers);
           }
       });

        HBox form = new HBox();
        form.setPadding(new Insets(10));
        form.setSpacing(10);
        form.getChildren().addAll(textFieldFirstname,textFieldLastname,textFieldBirthDate,addButton,deleteButton);


        layout.getChildren().add(tableView);
        layout.getChildren().add(form);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}
