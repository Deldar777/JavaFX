package shekho.com;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ToDoList extends Application {

    @Override
    public void start(Stage window) throws Exception {

        Database database = new Database();
        ObservableList<Task> tasks = FXCollections.observableArrayList(database.getTasks());

        window.setTitle("To Do List");
        window.getIcons().add(new Image(getClass().getResourceAsStream("image.png")));
        window.setMinWidth(250);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20));

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu aboutMenu = new Menu("About");
        MenuItem loadItem = new MenuItem("Load...");
        MenuItem saveItem = new MenuItem("Save...");
        MenuItem aboutItem = new MenuItem("About");
        fileMenu.getItems().addAll(loadItem,saveItem);
        aboutMenu.getItems().add(aboutItem);
        menuBar.getMenus().addAll(fileMenu,aboutMenu);

        TableView tableView = new TableView();

        TableColumn<Task,String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Task,String> completed = new TableColumn<>("Completed");
        completed.setCellValueFactory(new PropertyValueFactory<>("completed"));

        tableView.getColumns().addAll(description,completed);
        tableView.setItems(tasks);


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);

        TextField taskDescription = new TextField();
        taskDescription.setPrefWidth(120);
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(taskDescription.getText().isBlank())
                    return;
                tasks.add(new Task(taskDescription.getText()));
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<Task> selectedTasks = tableView.getSelectionModel().getSelectedItems();
                tasks.removeAll(selectedTasks);
            }
        });

        tableView.setRowFactory(tv ->{
            TableRow<Task> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    Task task = row.getItem();
                    task.setCompleted(!task.isCompleted());
                    tableView.refresh();
                }
            });
            return row;
        });

        hBox.getChildren().addAll(taskDescription,addButton,deleteButton);

        vBox.getChildren().add(menuBar);
        vBox.getChildren().add(tableView);
        vBox.getChildren().add(hBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
}
