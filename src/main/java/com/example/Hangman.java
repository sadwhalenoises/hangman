package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.event.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * JavaFX App
 */
public class Hangman extends Application{

    private static Scene scene;
    private static Label titleLabel;
    private static Label userPrompt;
    private static Label[] stackLabel;
    private static TextField userText;
    private static String enumString;
    private static ImageView baseHangmanImage;
    private static BorderPane rootNode;
    UserInput input = new UserInput();

    @Override
    public void start(Stage stage) throws IOException {
        titleLabel = new Label("Hangman");
        titleLabel.setId("title");
        stage.setTitle("Test Hangman Title");
        enumString = input.getText(Letters.randoLetters());


        rootNode = new BorderPane();
        rootNode.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.TOP_CENTER);
        rootNode.setCenter(addFBox());
        rootNode.setBottom(addCharacters());
        rootNode.setLeft(userText());


        File file = new File("src/main/java/com/example/css/styles.css");
        Scene HangmanScene = new Scene(rootNode, 800, 600);
        HangmanScene.getStylesheets().add(file.toURI().toString());
        stage.setScene(HangmanScene);
        stage.show();
    }

    public FlowPane addFBox(){
        FlowPane fpane = new FlowPane();
        fpane.setAlignment(Pos.CENTER);
            
        baseHangmanImage = new ImageView(input.changeImg());
        fpane.getChildren().add(baseHangmanImage);
        FlowPane.setMargin(baseHangmanImage, new Insets(0,140,0,0));
        System.out.println("hi");
            
        return fpane;
        

    }

    public FlowPane addCharacters(){

        
        String vowels = "aeiou";
        stackLabel = new Label[enumString.length()];
        FlowPane charBorders = new FlowPane(10, 10);
        StackPane[] stack = new StackPane[enumString.length()];

        for(int i = 0; i <=enumString.length()-1; i++){
            stackLabel[i] = new Label(Character.toString(enumString.charAt(i)));
            if(vowels.contains(Character.toString(enumString.charAt(i)).toLowerCase())){
                stackLabel[i].setId("visible-text");
            }else{
                stackLabel[i].setId("invisible-text");
            }
            stack[i] = new StackPane();
            stack[i].getChildren().addAll(new Rectangle(50,50,Color.BLACK), stackLabel[i]);
            FlowPane.setMargin(stack[i], new Insets(50,2,250,2));
            charBorders.getChildren().add(stack[i]);
        }
        
        
        charBorders.setAlignment(Pos.CENTER);

        return charBorders;
    }

    public FlowPane userText(){
        FlowPane user = new FlowPane();
        user.setVgap(5);
        user.setOrientation(Orientation.VERTICAL);
        userText = new TextField();
        userText.setMaxWidth(30);
        userPrompt = new Label("Enter a letter to guess");
        user.getChildren().addAll(userPrompt, userText);
        UserInput.checkText(userText);
        userText.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e) {
                input.actionEvent(userText, stackLabel);
                
               rootNode.setCenter(addFBox()); 
            }
            
        });


        FlowPane.setMargin(userText, new Insets(5,0,0,20));
        FlowPane.setMargin(userPrompt, new Insets(5,0,0,20));

        
        
        
        return user;


    }


    public enum Letters {
        JAVA,
        PYTHON,
        MAVEN,
        MAIL,
        RUBY,
        GAMES,
        SHAGGY,
        GACHA,
        LEAGUE,
        MOTHER,
        FATHER,
        SISTER,
        BROTHER,
        SOFTWARE,
        GAMBLE;

    
        private static final List<Letters> LETTERS =
            Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = LETTERS.size();
        private static final Random RANDOM = new Random();

        public static String randoLetters() {
            return LETTERS.get(RANDOM.nextInt(SIZE)).toString();
        }
            
    }

    

    public static void main(String[] args) {
        launch();
    }

    

}

