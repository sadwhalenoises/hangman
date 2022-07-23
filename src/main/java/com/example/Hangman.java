package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.shape.Box;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

/**
 * JavaFX App
 */
public class Hangman extends Application {

    private static Scene scene;
    private static Label titleLabel;
    private static Label testLabel;

    @Override
    public void start(Stage stage) throws IOException {
        titleLabel = new Label("Hangman");
        testLabel = new Label("test");
        titleLabel.setId("title");
        stage.setTitle("Test Hangman Title");


        BorderPane rootNode = new BorderPane();
        rootNode.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.TOP_CENTER);
        rootNode.setCenter(addFBox());
        rootNode.setBottom(addCharacters());


        File file = new File("src/main/java/com/example/css/styles.css");
        Scene HangmanScene = new Scene(rootNode, 800, 800);
        HangmanScene.getStylesheets().add(file.toURI().toString());
        stage.setScene(HangmanScene);
        stage.show();
    }

    public FlowPane addFBox() throws FileNotFoundException, NoSuchFileException{
        FlowPane fpane = new FlowPane();
        fpane.setAlignment(Pos.CENTER);
        try {

            File file = new File("src/main/java/com/example/imgs/hangman/base.png");
            if(file.exists()){
            Image HangmanImg = new Image(file.toURI().toString());
            ImageView baseHangmanImage = new ImageView(HangmanImg);
            fpane.getChildren().add(baseHangmanImage);
            }else{
                System.out.println("file doesn't exist");
            }
            
        } catch (Exception e) {
            System.out.println("Could not find image");
        }
        return fpane;
        

    }

    public FlowPane addCharacters(){
        String str = "test";
        Label[] labels = new Label[str.length()];
        FlowPane charBorders = new FlowPane(10, 10);

        for(int i = 0; i <=str.length()-1; i++){
            labels[i] = new Label("t");
            labels[i].getStyleClass().add("letters");
            charBorders.getChildren().add(labels[i]);
            FlowPane.setMargin(labels[i], new Insets(2,2,250,2));
        }
        charBorders.setAlignment(Pos.CENTER);

        return charBorders;
    }


    public enum Element {
        H("Hydrogen"),
        HE("Helium"),
        // ...
        NE("Neon");
    
        public final String label;
    
        private Element(String label) {
            this.label = label;
        }
        
        
    }

    

    public static void main(String[] args) {
        launch();
        System.out.println(Element.HE.label);
    }

}