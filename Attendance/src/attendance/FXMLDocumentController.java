/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mdsad
 */
public class FXMLDocumentController implements Initializable {

    static double x, y;
    @FXML
    private AnchorPane next;

    @FXML
    private Label label;

    @FXML
    private ComboBox<String> idOfComboBoxOfDept;

    @FXML
    private ComboBox<String> idOfComboBoxOfYear;

    @FXML
    private ComboBox<String> idOfComboBoxOfSem;

    @FXML
    private ComboBox<String> idOfComboBoxOfSec;

    @FXML
    private Button idOfNextBtnOfClass;
    ArrayList<String> OptionsOfDept = new ArrayList<>();
    ArrayList<String> OptionsOfYear = new ArrayList<>();
    ArrayList<String> OptionsOfSem = new ArrayList<>();
    ArrayList<String> OptionsOfSec = new ArrayList<>();

    @FXML
    void actionofNextBTN(ActionEvent event) {
        if ((idOfComboBoxOfDept.getValue() != null) && (idOfComboBoxOfSec.getValue() != null) && (idOfComboBoxOfSem.getValue() != null)
                && (idOfComboBoxOfYear.getValue() != null)) {
            String dept = idOfComboBoxOfDept.getValue();
            String sec = idOfComboBoxOfSec.getValue();
            String sem = idOfComboBoxOfSem.getValue();
            String year = idOfComboBoxOfYear.getValue();
            String keys = dept + "_" + year + "_" + sem + "_" + sec;
            String query = "select *from master_student where keys_dept_year_sem_sec='" + keys + "'";
            Constants.query = query;
            System.out.println(query);

            try {

                FXMLLoader fxmlLoder = new FXMLLoader(getClass().getResource("Second_Windows.fxml"));
                Parent root1 = (Parent) fxmlLoder.load();
                Stage stage = new Stage();
                stage.setTitle("HELLO");
                Scene scen = new Scene(root1);

                stage.setScene(scen);

                stage.initStyle(StageStyle.UNDECORATED);
              
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Allert");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        OptionsOfDept.addAll(Arrays.asList(new String[]{"CSE", "IT", "ECE", "ME", "CIVIL", "AIE"}));
        idOfComboBoxOfDept.getItems().addAll(OptionsOfDept);
        OptionsOfYear.addAll(Arrays.asList(new String[]{"1", "2", "3", "4"}));
        idOfComboBoxOfYear.getItems().addAll(OptionsOfYear);
        OptionsOfSem.addAll(Arrays.asList(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}));
        idOfComboBoxOfSem.getItems().addAll(OptionsOfSem);
        OptionsOfSec.addAll(Arrays.asList(new String[]{"1", "2"}));
        idOfComboBoxOfSec.getItems().addAll(OptionsOfSec);

    }
     @FXML
    void actionOnexit(ActionEvent event) {
        System.exit(0);
    }

}
