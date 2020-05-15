/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5asss;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Abu Yasser
 */
public class AddCourseController implements Initializable {

    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtCourseId;
    @FXML
    private TextField txtLabel;
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    
    EntityManagerFactory emf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 this.emf = Persistence.createEntityManagerFactory("Ch5AsssPU");

        // TODO
    }    

    @FXML
    private void addButtonHandle(ActionEvent event) {
          Registration r = new Registration();
          Integer courseid  = Integer.parseInt(this.txtCourseId.getText());
          Integer studentid  = Integer.parseInt(this.txtStudentId.getText());
        
         r.setCourseid(courseid);
         r.setStudentid(studentid);
         String semester = this.txtLabel.getText();
         r.setSemester(semester);
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void backButtonHandle(ActionEvent event) throws IOException {
         Pane addCourse = FXMLLoader.load(getClass().getResource("TableViewPane.fxml"));
          Ch5Asss.scene =  new Scene(addCourse);
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(Ch5Asss.scene);
          window.show();
    }
 
}
