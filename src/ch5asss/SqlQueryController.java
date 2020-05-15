/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5asss;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Abu Yasser
 */
public class SqlQueryController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TableView<Students> tableView;
    @FXML
    private TableColumn<Students, Integer> idColumn;
    @FXML
    private TableColumn<Students, String> nameColumn;
    @FXML
    private TableColumn<Students, String> majorColumn;
    @FXML
    private TableColumn<Students, Double> gradeColumn;
    @FXML
    private Button selectButton;
    @FXML
    private Button updateButton;
  private EntityManagerFactory emf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               idColumn.setCellValueFactory(new PropertyValueFactory("id"));
         nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
         majorColumn.setCellValueFactory(new PropertyValueFactory("major"));
         gradeColumn.setCellValueFactory(new PropertyValueFactory("grade"));
         this.emf = Persistence.createEntityManagerFactory("Ch5AsssPU");
        // TODO
    }    

    @FXML
    private void selectButtonHandle(ActionEvent event){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery(textArea.getText(),Students.class);
        List<Students> students = query.getResultList();
        tableView.getItems().setAll(students);
         
        em.close();
    }

    @FXML
    private void updateButtonHandle(ActionEvent event) {
        

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
         Query query = em.createNativeQuery(textArea.getText());
         query.executeUpdate();
         em.getTransaction().commit();
         em.close();

    }
    
}
