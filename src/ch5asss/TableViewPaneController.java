/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5asss;

import com.mysql.cj.xdevapi.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Abu Yasser
 */
public class TableViewPaneController implements Initializable {

    @FXML
    private TextField txtFieldID;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldMajor;
    @FXML
    private TextField txtFieldGrade;
    @FXML
    private TableView<Students> tableView;
    @FXML
    private TableColumn<Students, Integer> tcID;
    @FXML
    private TableColumn<Students, String> tcName;
    @FXML
    private TableColumn<Students, String> tcMajor;
    @FXML
    private TableColumn<Students, Double> tcGrade;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonAddCourse;
    @FXML
    private Button sqlStatement;
    private EntityManagerFactory emf;
    /**
     * Initializes the controller class.
     */
   static  List<Integer> studentsIds = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
              tcID.setCellValueFactory(new PropertyValueFactory("id"));
         tcName.setCellValueFactory(new PropertyValueFactory("name"));
         tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
         tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
         this.emf = Persistence.createEntityManagerFactory("Ch5AsssPU");
         tableView.getSelectionModel().selectedItemProperty().addListener(
                event-> showSelectedStudent() );
            this.showStudent();
        // TODO
    }    

    
     @FXML
    private void textFieldStudentIdHandle(ActionEvent event) {
        EntityManager em = this.emf.createEntityManager();
        try{
        Students s = (Students)em.createNamedQuery("Students.findById")
                .setParameter("id", Integer.parseInt(txtFieldID.getText()))
                .getSingleResult();
        this.txtFieldName.setText(s.getName());
        this.txtFieldMajor.setText(s.getMajor());
        this.txtFieldGrade.setText(s.getGrade().toString());
        }
        catch(NoResultException ex){
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error retrieving");
        alert.setContentText("No recrds found");
        alert.showAndWait();
        
        }
        em.close();
        
       
        
    }
    @FXML
    private void addButtonHandle(ActionEvent event) {
        Students s = new Students();
        s.setName(this.txtFieldName.getText());
        s.setMajor(this.txtFieldMajor.getText());
        s.setGrade(Double.parseDouble(this.txtFieldGrade.getText()));
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        em.close();
        this.showStudent();
        this.studentsIds.add(s.getId());
    }

    @FXML
    private void deleteAddHandle(ActionEvent event) {
        
        
        EntityManager em = this.emf.createEntityManager();
        String id = this.txtFieldID.getText();
        em.getTransaction( ).begin( );
        Students student = em.find( Students.class, Integer.parseInt(id) );
        em.remove( student );
        em.getTransaction( ).commit( );
        em.close( );
           this.showStudent();
           this.resetTextField();
    }

    @FXML
    private void buttonUpdateHandle(ActionEvent event) {
        
        EntityManager em =emf.createEntityManager();
        String id = this.txtFieldID.getText();
        em.getTransaction( ).begin( );
        Students student = em.find( Students.class, Integer.parseInt(id) );
        student.setName(this.txtFieldName.getText());
        student.setMajor(this.txtFieldMajor.getText());
        student.setGrade(Double.parseDouble(this.txtFieldGrade.getText()));
        em.getTransaction().commit();
        em.refresh(student);
        em.close();
        this.showStudent();
            
    }

    @FXML
    private void addCourseHandle(ActionEvent event) throws Exception{
          Pane addCourse = FXMLLoader.load(getClass().getResource("AddCourse.fxml"));
          Ch5Asss.scene =  new Scene(addCourse);
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(Ch5Asss.scene);
          window.show();
    }

    @FXML
    private void sqlHandle(ActionEvent event) throws IOException {
          Pane addCourse = FXMLLoader.load(getClass().getResource("SqlQuery.fxml"));
          Ch5Asss.scene =  new Scene(addCourse);
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(Ch5Asss.scene);
          window.show();
    }
    
     private void showStudent(){
       EntityManager em =emf.createEntityManager();
       List<Students>  students = em.createNamedQuery("Students.findAll").getResultList();
       tableView.getItems().setAll(students);
       em.close();

    }

        private void showSelectedStudent(){
        Students student = tableView.getSelectionModel().getSelectedItem();
        if(student != null){
        txtFieldID.setText(String.valueOf(student.getId()));
        txtFieldName.setText(student.getName());
        txtFieldGrade.setText(String.valueOf(student.getGrade()));
        txtFieldMajor.setText(String.valueOf(student.getMajor()));
        }

        }
        
        private void resetTextField(){
        this.txtFieldGrade.setText("");
        this.txtFieldID.setText(" ");
        this.txtFieldName.setText("");
        this.txtFieldMajor.setText(" ");
        
       }
 
}