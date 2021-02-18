/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import static attendance.FXMLDocumentController.x;
import java.awt.event.MouseEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mdsad
 */
public class Attendance extends Application {
    
    public static double x, y;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        scene.setOnMousePressed((event) -> {
            x = stage.getX() - event.getSceneX();
            y = stage.getY() - event.getSceneY();
        });
        scene.setOnMouseDragged((event) -> {
            stage.setX(event.getSceneX() + x);
            stage.setY(event.getSceneY() + y);
        });
//        scen.setOnMousePressed(((event) -> {
//        } event1
//        
//            ) -> {
//                   
//        });
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
