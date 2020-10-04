package UI;

import DAL.Database;
import Logic.Login;
import Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.time.LocalDate;


public class LoginScreen extends Application {
    public static void main(String[] args){launch(args);}

    @Override
    public void start(Stage window) throws Exception {

        /*User user = new User("deldar","shekho12","Deldar","Shekho", LocalDate.of(1990,1,1));
        Home home = new Home(user);*/
        Login login = new Login();

        try{
            window.setTitle("University Project");
            window.getIcons().add(new Image(getClass().getResourceAsStream("universityImage.png")));
            window.setHeight(300);
            window.setWidth(500);

            BorderPane borderPane = new BorderPane();
            borderPane.setPadding(new Insets(10,50,50,50));

            //Adding hBox
            HBox hb = new HBox();
            hb.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY)));
            hb.setPadding(new Insets(20,20,20,30));

            //Adding GridPane
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(20,20,20,20));
            gridPane.setHgap(1);
            gridPane.setVgap(15);

            //Implementing Nodes for GridPane
            Label lblUserName = new Label("Username");
            final TextField txtUserName = new TextField();
            Label lblPassword = new Label("Password");
            final PasswordField pf = new PasswordField();
            Button btnLogin = new Button("Login");
            btnLogin.setPrefWidth(100);
            final Label lblMessage = new Label();

            //Adding Nodes to GridPane layout
            gridPane.add(lblUserName, 0, 0);
            gridPane.add(txtUserName, 1, 0);
            gridPane.add(lblPassword, 0, 1);
            gridPane.add(pf, 1, 1);
            gridPane.add(btnLogin, 1, 2);
            gridPane.add(lblMessage, 1, 3);

            //Reflection for gridPane
            Reflection r = new Reflection();
            r.setFraction(0.7f);
            gridPane.setEffect(r);

            //DropShadow effect
            DropShadow dropShadow = new DropShadow();
            dropShadow.setOffsetX(5);
            dropShadow.setOffsetY(5);

            //Adding text and DropShadow effect to it
            Text text = new Text("University Project");
            text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
            text.setEffect(dropShadow);

          /* Image image = new Image(new FileInputStream("D:\\universityImage.png"));
            ImageView imageView = new ImageView(image);


            //Adding image to HBox
            hb.getChildren().add(imageView);*/

            //Adding text to HBox
            hb.getChildren().add(text);

            //Add ID's to Nodes
            borderPane.setId("bp");
            gridPane.setId("root");
            btnLogin.setId("btnLogin");
            text.setId("text");

            btnLogin.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    String usernameInput = txtUserName.getText();
                    String passwordInput = pf.getText();

                    if(!usernameInput.isEmpty() && !passwordInput.isEmpty()){

                        User user = login.validateLogin(usernameInput,passwordInput);

                        if(user != null){
                            Home home = new Home(user);
                            window.close();
                        }else{
                            lblMessage.setText("The username of the password isn't correct");
                        }
                    }else{
                        lblMessage.setText("don't leave the fields empty!");
                    }




                    txtUserName.setText("");
                    pf.setText("");
                }
            });



            //Add HBox and GridPane layout to BorderPane Layout
            borderPane.setTop(hb);
            borderPane.setCenter(gridPane);

            //Adding BorderPane to the scene and loading CSS
            Scene scene = new Scene(borderPane);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("resources/css/login.css").toExternalForm());
            window.setScene(scene);

            window.show();
        }catch (Exception e){

            System.out.println(e.getMessage());
        }

    }
}
