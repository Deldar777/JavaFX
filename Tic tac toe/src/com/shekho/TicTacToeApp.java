package com.shekho;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class TicTacToeApp extends Application {

    private boolean turnX = true;
    private boolean playable = true;
    private Tile[][] board = new Tile[3][3];
    private List<combo> combos = new ArrayList<>();
    private Pane root = new Pane();

    private Parent createContent(){

        root.setPrefSize(600,600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j*200);
                tile.setTranslateY(i*200);

                root.getChildren().add(tile);
                board[j][i] = tile;
            }
        }

        //Horizontal
        for (int h = 0; h < 3; h++) {
            combos.add(new combo(board[0][h],board[1][h],board[2][h]));
        }

        //Vertical
        for (int v = 0; v < 3; v++) {
            combos.add(new combo(board[v][0],board[v][1],board[v][2]));
        }

        //Diagonal
        //Diagonal opposite
        combos.add(new combo(board[0][0],board[1][1],board[2][2]));
        combos.add(new combo(board[2][0],board[1][1],board[0][2]));

        return root;
    }

    public static void main(String[] args){launch(args);}
    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Tic tac toe");
        window.getIcons().add(new Image(getClass().getResourceAsStream("image.png")));
        window.setScene(new Scene(createContent()));
        window.show();
    }

    private void checkState(){

        for (combo combo:combos
             ) {
            if(combo.isCompelete()){
                playable =false;
                playWinAnimation(combo);
                break;
            }
        }
    }

    private void playWinAnimation(combo combo){
        Line line = new Line();
        line.setStartX(combo.tiles[0].getCenterX());
        line.setStartY(combo.tiles[0].getCenterY());
        line.setEndX(combo.tiles[0].getCenterX());
        line.setEndY(combo.tiles[0].getCenterY());

        root.getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.endXProperty(),combo.tiles[2].getCenterX()),
                new KeyValue(line.endYProperty(),combo.tiles[2].getCenterY())));
        timeline.play();
    }

    private class combo{
        private Tile[] tiles;
        public combo(Tile...tiles){
            this.tiles = tiles;
        }
        public boolean isCompelete(){
            if(tiles[0].getValue().isEmpty())
                return false;

            return (tiles[0].getValue().equals(tiles[1].getValue()))
                    && tiles[0].getValue().equals(tiles[2].getValue());
        }
    }

    private class Tile extends StackPane{
        private Text text = new Text();
        public Tile(){
            Rectangle border = new Rectangle(200,200);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(150));
            setAlignment(Pos.CENTER);
            getChildren().addAll(border,text);

            setOnMouseClicked(event ->{
                if(!playable)
                    return;
                if(event.getButton() == MouseButton.PRIMARY){
                    if(!turnX)
                        return;
                    drawX();
                    turnX = false;
                    checkState();
                }else if(event.getButton() == MouseButton.SECONDARY){
                    if(turnX)
                        return;
                    drawO();
                    turnX = true;
                    checkState();
                }
            });
        }

        public double getCenterX(){
            return getTranslateX() + 100;
        }

        public double getCenterY(){
            return getTranslateY() + 100;
        }
        public String getValue(){
            return text.getText();
        }
        private void drawX(){
            text.setText("X");
        }

        private void drawO(){
            text.setText("O");
        }
    }
}
