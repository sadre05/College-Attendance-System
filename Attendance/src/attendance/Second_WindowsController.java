/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import dataBaseActivity.CurdOprationAgainstDatabase;
import database.connection.MySQL_Coonection;
import static database.connection.MySQL_Coonection.ConnectDb;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import showAlerts.GetMessegeAsAlert;

/**
 * FXML Controller class
 *
 * @author mdsad
 */
public class Second_WindowsController implements Initializable {

    @FXML
    private Button idOfSubmit_BTN1;
    public String query;
    @FXML
    private TextField idOfTextFieldOfTeacherName;

    @FXML
    private TextField idOfSubject_ID;

    @FXML
    private TableView<Getter_Setter> idOfTable_View;

    @FXML
    private TableColumn<Getter_Setter, Integer> idOf_Roll_No;

    @FXML
    private TableColumn<Getter_Setter, String> idOf_Name_Strudet;

    @FXML
    private TableColumn<Getter_Setter, String> idOf_Gender;

    @FXML
    private TableColumn<Getter_Setter, String> id_Of_Department;

    @FXML
    private TableColumn<Getter_Setter, String> id_Of_Present_and_Absent;
    //@FXML
//    private TextField idOfTextFieldOfTeacherName;
//
//    @FXML
//    private TextField idOfSubject_ID;
    @FXML
    private Button idOfSubmit_BTN;
    ObservableList<Getter_Setter> listS;

    @FXML
    void actionOnSubmit_BTN(ActionEvent event) {
        if (!(idOfSubject_ID.getText().trim().equals("")) && !(idOfTextFieldOfTeacherName.getText().trim().equals(""))) {

            System.err.println("Submited successfully");
            listS.forEach((Getter_Setter get) -> {
                if (get.getPresent_Absent().isSelected()) {
                    // System.err.println(get.getPresent_Absent().isSelected());
                    int rollanumber = get.getRoll_number();
                    String subjectId = idOfSubject_ID.getText().trim();
                    String teacherName = idOfTextFieldOfTeacherName.getText().trim();
                    String key = get.getkeys_dept_year_sem_sec();
                    String stdName = get.getStudent_name();
                    String gen = get.getGender();
                    String sem = "1";
                    String dept = get.getDepartment();
                    try {
                        if (checkDataExist(rollanumber, subjectId)) {
                            System.err.println("True");
                            updateAttendance(rollanumber, subjectId, key);

                        } else {
                            System.err.println("false");
                            String insertDataQuery = "insert into student_attedance_analysis(roll_number,student_name,gender,semester,department,keys_dept_year_sem_sec,teacher_name,subject_id,total_class,attendance) "
                                    + "values('" + rollanumber + "','" + stdName + "','" + gen + "','" + sem + "','" + dept + "','" + key + "','" + teacherName + "','" + subjectId + "','1','1'" + ")";
                            insertAttendance(insertDataQuery);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Second_WindowsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    //********************************************************************************//
                    

                    int roll = get.getRoll_number();
                    String subjectId = idOfSubject_ID.getText().trim();
                    //***********************************************************************************//
                    int toBeUpdateTotalAttendace = 1, toBeUpdateTotalClass = 1;
                    Connection connectionOfDataBase = MySQL_Coonection.ConnectDb();
                    int countOfTotalClass = 0;
                    try {
                        countOfTotalClass = CurdOprationAgainstDatabase.selectDataFromDatabase("select * from student_attedance_analysis where roll_number='" + roll + "' and subject_id='" + subjectId + "'", "total_class");
                    } catch (SQLException ex) {
                        Logger.getLogger(Second_WindowsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String updated_query = "update  student_attedance_analysis set total_class=" + (toBeUpdateTotalClass + countOfTotalClass) + " where roll_number='" + roll + "' and subject_id='" + subjectId + "'";
                    System.err.println("  " + updated_query);
                    PreparedStatement ps = null;
                    try {
                        ps = connectionOfDataBase.prepareStatement(updated_query);
                    } catch (SQLException ex) {
                        Logger.getLogger(Second_WindowsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        int executeUpdate = ps.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(Second_WindowsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            listS.clear();
        } else {
            GetMessegeAsAlert.getCondidatesNotFoundWarning("Please enter TeacherName and SubjectId", "Input Error");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            listS = MySQL_Coonection.getDataStudent();
        } catch (SQLException ex) {
            Logger.getLogger(Second_WindowsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("This is a sencode Ui window init");
        idOf_Roll_No.setCellValueFactory(new PropertyValueFactory<>("roll_number"));
        idOf_Name_Strudet.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        idOf_Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        id_Of_Department.setCellValueFactory(new PropertyValueFactory<>("department"));

        id_Of_Present_and_Absent.setCellValueFactory(new PropertyValueFactory<>("Present_Absent"));

        idOfTable_View.setItems(listS);
    }

    @FXML
    void actionExitUiTwo(ActionEvent event) {
        //  System.exit(0);
        Stage stage = (Stage) idOfSubmit_BTN1.getScene().getWindow();

        // do what you have to do
        listS.clear();
        stage.close();
    }

    private boolean checkDataExist(int roll, String subjId) throws SQLException {
        Connection connectionOfDataBase = MySQL_Coonection.ConnectDb();
        String query = "select * from student_attedance_analysis where roll_number='" + roll + "' and "
                + "subject_id='" + subjId + "'";
        System.err.println("query to check: " + query);
        PreparedStatement ps = connectionOfDataBase.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        return rs.next() == true;

    }

    public void updateAttendance(int roll, String subjectId, String key) throws SQLException {
        int toBeUpdateTotalAttendace = 1, toBeUpdateTotalClass = 1;
        Connection connectionOfDataBase = MySQL_Coonection.ConnectDb();
        int countOfTotalAttendance = CurdOprationAgainstDatabase.selectDataFromDatabase("select * from student_attedance_analysis where roll_number='" + roll + "' and subject_id='" + subjectId + "'", "attendance");
        int countOfTotalClass = CurdOprationAgainstDatabase.selectDataFromDatabase("select * from student_attedance_analysis where roll_number='" + roll + "' and subject_id='" + subjectId + "'", "total_class");
        System.err.println(countOfTotalAttendance + "-----------------" + countOfTotalClass);

        String updated_query = "update  student_attedance_analysis set attendance=" + (toBeUpdateTotalAttendace + countOfTotalAttendance) + ", total_class=" + (toBeUpdateTotalClass + countOfTotalClass) + " where roll_number='" + roll + "' and subject_id='" + subjectId + "'";
        System.err.println("update query: " + updated_query);
        PreparedStatement ps = connectionOfDataBase.prepareStatement(updated_query);
        ps.executeUpdate();
    }

    public void insertAttendance(String insertQuery) throws SQLException {
        System.err.println("insert query: " + insertQuery);
        Connection connect = MySQL_Coonection.ConnectDb();
        PreparedStatement ps = connect.prepareStatement(insertQuery);
        ps.executeUpdate();
    }

}
