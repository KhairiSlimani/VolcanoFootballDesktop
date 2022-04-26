/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.user;

import Entities.User;
import Gui.AlertsController;
import Services.UserService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class AddUserController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private JFXTextField tfUsername;
    @FXML
    private JFXPasswordField tfPassword;
    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXTextField tfPrenom;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXComboBox<?> tfRole;
    
    private FlowPane flowPane;
    private JFXDialog dialog;
    private StackPane container;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList roles = FXCollections.observableArrayList();
        roles.add("Admin");
        roles.add("Client");
        tfRole.setItems(roles);
        tfRole.getSelectionModel().selectFirst();
    }    

    @FXML
    private void AddU(ActionEvent event) {
        
        boolean control = true;
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        String email = tfEmail.getText();
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String role = tfRole.getSelectionModel().getSelectedItem().toString();
        
        if(username.length()==0 || password.length() ==0 || email.length() == 0 || nom.length() == 0 || prenom.length() == 0){
            AlertsController.get().Alert(".","Erreur","Veuillez entrer toutes les informations nécessaires!");
            control = false;
        }
        else if(username.length()<4) {
            AlertsController.get().Alert(".","Erreur","Le username doit faire au moins 4 caractères!");            
            control = false;
        }
        else if(password.length()<4) {
            AlertsController.get().Alert(".","Erreur","Le mot de passe doit faire au moins 4 caractères!");            
            control = false;
        }
        else if (!(Pattern.matches("^[A-Za-z0-9_.]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", email))){
            AlertsController.get().Alert(".","Erreur","L'email n'est pas valide!");            
            control = false;
        }
        
        if(control == true)
        {
            User u = new User(username, password, nom, prenom, email, role, "token", 1);
            UserService us = new UserService();
            boolean test = us.Register(u);
            
            if(test == true)
            {
                tfUsername.clear();
                tfPassword.clear();
                tfEmail.clear();
                tfNom.clear();
                tfPrenom.clear();
                flowPane.getChildren().clear();
                List<User> list = us.AfficherUsers();
                try {
                    for (int i = 0; i < list.size(); i++) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserItem.fxml"));
                        Pane pane = loader.load();
                        UserItemController controller = loader.getController();
                        controller.ItemInfos(list.get(i), flowPane, dialog , container);
                        flowPane.getChildren().add(pane);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }


            }
        }
    }
    
    public Button getCloseButton(){
        return this.closeButton;
    }
    
    public void setInfos(FlowPane fp, JFXDialog d, StackPane c){
        dialog = d;
        container = c;
        flowPane=fp;
    }

    
}
