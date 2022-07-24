package com.example;

import java.io.IOException;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.FlowPane;

public class UserInput{
    
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

    public void actionEvent(ActionEvent e, Label[] stackLabel){
        String letters = "";

        for(int i = 0; i< stackLabel.length-1; i++){
            letters += stackLabel[i].getText();
        }

        Object o = e.getSource();
        
        if(letters.contains((CharSequence) o)){
            stackLabel[letters.lastIndexOf((String) o)].setId("visible-text");
        }
    }
}
    
