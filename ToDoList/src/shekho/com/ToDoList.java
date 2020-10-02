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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;

import java.io.*;


public class ToDoList extends Application {

    @Override
    public void start(Stage window) throws Exception {

        Database database = new Database();
        ObservableList<Task> tasks = FXCollections.observableArrayList(database.getTasks());

        window.setTitle("To Do List");
        window.getIcons().add(new Image(getClass().getResourceAsStream("image.png")));
        window.setMinWidth(250);

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(255,0,0),CornerRadii.EMPTY,Insets.EMPTY)));
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


        saveItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try(FileOutputStream fos = new FileOutputStream(new File("items.dat"));
                    ObjectOutputStream oos = new ObjectOutputStream(fos))
                    {

                        for (Task task:tasks
                             ) {
                            oos.writeObject(task);
                        }

                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

       loadItem.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               try(ObjectInputStream ois = new ObjectInputStream(
                       new FileInputStream(new File("items.dat"))))
               {
                   while (true){

                       try{
                           Task t = (Task)ois.readObject();
                           tasks.add(t);
                       }catch (EOFException eofe){
                           break;
                       }
                   }
               }catch (FileNotFoundException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }catch (ClassNotFoundException cnfe){
                   cnfe.printStackTrace();
               }
           }
       });

        TableView tableView = new TableView();

        TableColumn<Task,String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Task,String> completed = new TableColumn<>("Completed");
        completed.setCellValueFactory(new PropertyValueFactory<>("completed"));

        tableView.getColumns().addAll(description,completed);
        tableView.setItems(tasks);


        HBox hBox = new HBox();
        hBox.setBackground(new Background(new BackgroundFill(Color.CHOCOLATE,CornerRadii.EMPTY,Insets.EMPTY)));
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
