package com.shekho.converter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.text.DecimalFormat;


public class CurrencyApp extends Application {

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage window) throws Exception {
        window.show();
        window.setTitle("Currency Converter");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setHgap(10);
        gridPane.setVgap(20);

        Label amountEuro = new Label("Amount €:");
        amountEuro.setPrefWidth(80.0);
        GridPane.setConstraints(amountEuro,0,0);

        TextField amountTextField = new TextField();
        amountTextField.setPromptText("Amount");
        GridPane.setConstraints(amountTextField,1,0);
        amountTextField.setPrefWidth(160.0);


        Button convertButton = new Button("Convert Euro to Dollar");
        convertButton.setPrefWidth(160.0);
        GridPane.setConstraints(convertButton,1,1);

        Label amountDollar = new Label("Amount $:");
        GridPane.setConstraints(amountDollar,0,2);

        Label amountOutput = new Label();
        GridPane.setConstraints(amountOutput,1,2);

        gridPane.getChildren().addAll(amountEuro,amountTextField,convertButton,amountDollar,amountOutput);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);




        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String userInput = amountTextField.getText();
                double amountInEuro = 0;

                try{
                    if(userInput.isEmpty()){
                        amountOutput.setText("Empty");
                    }else{
                        amountInEuro = Double.parseDouble(userInput);
                        double amountInDollar = amountInEuro * 1.18;
                        amountOutput.setText(new DecimalFormat("##.##").format(amountInDollar));
                    }
                }catch (Exception e){
                    amountOutput.setText("Invalid input");
                }

            }
        });


    }
}
