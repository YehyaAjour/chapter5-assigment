/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5asss;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abu Yasser
 */
public class Ch5Asss extends Application{

   static Scene scene;
   
    public static void main(String[] args) {
       launch(args);
               
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Parent root =  FXMLLoader.load(getClass().getResource("TableViewPane.fxml"));
        scene =  new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
       
    
}
}