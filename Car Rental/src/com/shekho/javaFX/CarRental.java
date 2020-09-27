package com.shekho.javaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;


public class CarRental extends Application {

    final double PRICE_FOR_EXTRA_KILOMETER = 0.25;
    final int PRICE_PER_DAY = 45;
    final int ALLOWED_KILOMETER_PER_DAY = 100;
    final int PRICE_PER_LITER  = 2;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {

        window.setTitle("Car Rental");
        window.getIcons().add(new Image(getClass().getResourceAsStream("image.png")));
        window.show();

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(40,40,40,40));
        gridpane.setVgap(10);
        gridpane.setHgap(10);

        Label labelNumberOfDays = new Label("Number of days rented: ");
        GridPane.setConstraints(labelNumberOfDays,0,0);

        TextField textFieldNumberOfDays = new TextField();
        textFieldNumberOfDays.setPrefSize(160,10);
        GridPane.setConstraints(textFieldNumberOfDays,1,0);


        Label labelKilometersDriven = new Label("Number of kilometers driven: ");
        GridPane.setConstraints(labelKilometersDriven,0,1);

        TextField textFieldKilometersDriven = new TextField();
        textFieldKilometersDriven.setPrefSize(160,10);
        GridPane.setConstraints(textFieldKilometersDriven,1,1);


        CheckBox checkboxFuelTanked = new CheckBox("Fuel tank not full when returned");
        GridPane.setConstraints(checkboxFuelTanked,0,2);

        Label labelNumberOfLiters = new Label("Number of Liters: ");
        GridPane.setConstraints(labelNumberOfLiters,0,3);

        TextField textFieldNumberOfLiters = new TextField();
        textFieldNumberOfLiters.setPrefSize(160,10);
        GridPane.setConstraints(textFieldNumberOfLiters,1,3);

        Button buttonCalculate = new Button("Calculate Payment");
        buttonCalculate.setPrefSize(160,10);
        GridPane.setConstraints(buttonCalculate,1,4);

        Label labelAmountDue = new Label("Amount Due: ");
        GridPane.setConstraints(labelAmountDue,0,5);

        Label result = new Label();
        GridPane.setConstraints(result,1,5);

        gridpane.getChildren().addAll(labelNumberOfDays,textFieldNumberOfDays,labelKilometersDriven,textFieldKilometersDriven,checkboxFuelTanked,buttonCalculate,labelAmountDue,result,labelNumberOfLiters,textFieldNumberOfLiters);

        Scene scene = new Scene(gridpane);
        window.setScene(scene);


        buttonCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String stringNumberOfDays = textFieldNumberOfDays.getText();
                String stringKilometersDriven = textFieldKilometersDriven.getText();
                String stringNumberOFLiters = textFieldNumberOfLiters.getText();

                int numberofLiters = 0;
                int numberOfDays = 0;
                int drivenKilometers = 0;
                int extraKilometer = 0;

                double total = 0;

                try{
                    if(stringNumberOfDays.isEmpty() || stringKilometersDriven.isEmpty()){
                        result.setText("Some fields are empty!");
                    }else{
                        numberOfDays = Integer.parseInt(stringNumberOfDays);
                        drivenKilometers = Integer.parseInt(stringKilometersDriven);

                        extraKilometer += drivenKilometers - (numberOfDays * ALLOWED_KILOMETER_PER_DAY);


                        if(extraKilometer > 0){
                            total += extraKilometer * PRICE_FOR_EXTRA_KILOMETER;
                        }

                        if(checkboxFuelTanked.isSelected()){
                            numberofLiters = Integer.parseInt(stringNumberOFLiters);
                            total += numberofLiters * PRICE_PER_LITER;
                        }

                        total += numberOfDays * PRICE_PER_DAY;

                        result.setText(new DecimalFormat("##.##").format(total));
                    }

                }catch (Exception e){
                    result.setText("Invalid input!");
                }
            }
        });






    }
}
