package com.example;

import java.io.File;
import java.io.IOException;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class UserInput{
    private final int MAX_GUESSES = 6;
    private int guesses = 0;
    private int count = 0;

    
    public static void checkText(TextField str){
        UnaryOperator<TextFormatter.Change> filter = c ->{
            if(c.getText().matches(".{1}")){
                c.setText(c.getText().toUpperCase());
                c.setRange(0, str.getText().length());
                return c;
            }else if (c.getText().isEmpty()){
                return c;
            }
            return null;
        };
        str.setTextFormatter(new TextFormatter<String>(filter));
    }

    public void actionEvent(TextField str, Label[] stackLabel){
        

        for(int i = 0; i< stackLabel.length; i++){
            if(str.getText().equals(stackLabel[i].getText())){
                stackLabel[i].setId("visible-text");
                break;
                
            }else{
                count++;
            }
        }

        if(count > 0) guesses++;
        str.setText("");

    
        
        
    }

    public Image changeImg(){

        File file = new File("src/main/java/com/example/imgs/hangman/" + String.valueOf(guesses) + ".png");
        System.out.println(file);
        Image HangmanImg = new Image(file.toURI().toString());
        return HangmanImg;

    }

    public String getText(String enumString){
        String str = "";
        for(int i=0; i <= enumString.length()-1; i++){
            str += enumString.charAt(i);
        }

        return str;
    }

    
}
    
