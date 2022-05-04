/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Gui.produit.ProduitsController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khairi
 */
public class DashboardController implements Initializable {
    
    Pane view;
    
    @FXML
    private Text sessionName;
    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox dashboardBox;
    @FXML
    private HBox usersBox;
    @FXML
    private HBox produitsBox;
    @FXML
    private HBox commandesBox;
    @FXML
    private VBox SideBar;
    @FXML
    private HBox favorisBox;
    @FXML
    private Text cartSize;
    @FXML
    private Text commandesText;
    @FXML
    private Text commandesText11;
    @FXML
    private HBox joueurBox;
    @FXML
    private Text joueur;
    @FXML
    private HBox commandesBox1;
    @FXML
    private HBox EquipeBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sessionName.setText(SessionManager.get().getUsername());
        if(SessionManager.get().getRole().equals("Client")){
            SideBar.getChildren().remove(usersBox);
            commandesText.setText("Mes Commandes");
        }
        SetCartSize();
        //cartSize.setText("test");
    }   
    
    public void SetCartSize() {
        cartSize.setText(String.valueOf(SessionManager.get().CartSize()));
    }    


    @FXML
    private void Logout(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("user/login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        SessionManager.get().DeleteCart();
    }

    @FXML
    private void OpenUsers(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("user/users.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    private void OpenProduits(MouseEvent event) throws IOException {
        /*
        view = new FXMLLoader().load(getClass().getResource("produit/produits.fxml"));
        borderPane.setCenter(view);
        */
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("produit/produits.fxml"));
        view=loader.load();
        ProduitsController controller = loader.getController();
        controller.CartSize(cartSize);
        borderPane.setCenter(view);
        


    }

    @FXML
    private void OpenCommandes(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("commande/commandes.fxml"));
        borderPane.setCenter(view);

    }
    
    @FXML
    private void OpenFavoris(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("favori/favoris.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    private void OpenCart(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("commande/cart.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    private void OpenProfil(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("user/profile.fxml"));
        borderPane.setCenter(view);

    }
    @FXML
    private void OpenJoueur(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("joueur/Joueur.fxml"));
        borderPane.setCenter(view);

    }
    @FXML
    private void OpenEquipe(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("equipe/Equipe.fxml"));
        borderPane.setCenter(view);

    }
    @FXML
    private void OpenStatEquipe(MouseEvent event) throws IOException {
        view = new FXMLLoader().load(getClass().getResource("equipe/statequipe.fxml"));
        borderPane.setCenter(view);

    }
    
    

}