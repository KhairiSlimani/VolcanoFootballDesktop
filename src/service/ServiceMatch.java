/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Match;
import utiles.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Packard bell
 */
public class ServiceMatch {
    Connection cnx= Maconnexion.getInstance().getCnx();
    private static ServiceMatch instance;
    
    
    
    public static ServiceMatch getInstance(){
        if(instance==null)
            instance=new ServiceMatch();
        return instance;
    }
    public void add(Match c) throws SQLException{
              try {

            String req = "INSERT INTO `match` (`nom_match`, `nom_arbitre`, `stade`,`tour`, `date` ,`image`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);
            pstm.setString(1, c.getNom_match());
            pstm.setString(2, c.getNom_arbitre());
            pstm.setString(3, c.getStade());
            pstm.setString(4, c.getTour());
            pstm.setString(5, c.getDate());
            pstm.setString(6, c.getImage());
            pstm.executeUpdate();
            System.out.println("Ajout effectué");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        
       /* String req = "INSERT INTO `match` (nom_match,nom_arbitre,stade,tour,date,image) VALUES ('"
                    + c.getNom_match()+"', '" 
                    + c.getNom_arbitre()+"', '" 
                    + c.getStade()+"', '" 
                    + c.getTour()+"', '" 
                    + c.getDate()+"', '" 
                    + c.getImage()+"', '" ;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);*/
    }
     public ObservableList<Match> getAll() throws SQLException {
     ObservableList<Match> result =  FXCollections.observableArrayList();
     String req = "SELECT * FROM `match`";
     Statement stm = cnx.createStatement();
     
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Match C = new Match();

         C.setId(rst.getInt("id"));
         C.setNom_match(rst.getString("nom_match"));
         C.setNom_arbitre(rst.getString("nom_arbitre"));
         C.setStade(rst.getString("stade"));
         C.setTour(rst.getString("tour"));
         C.setDate(rst.getString("date"));
         C.setImage(rst.getString("image"));
         
         result.add(C);
     }

     return result;
    } 
    public void updateMatch(Match C) throws SQLException{
        
        String req = "UPDATE `match` SET nom_match = ?, nom_arbitre = ?, stade = ?, tour = ?, date = ?, image = ? WHERE id= ?";
        PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, C.getNom_match());
            pst.setString(2, C.getNom_arbitre());
            pst.setString(3, C.getStade());
            pst.setString(4, C.getTour());
            pst.setString(5, C.getDate());
            pst.setString(6, C.getImage());
            pst.setInt(7, C.getId());
            pst.executeUpdate();
    }
    
    
   public void delete(String id) throws SQLException{
        String req= "DELETE FROM `match` WHERE  id= ?" ;
         PreparedStatement pst = cnx.prepareStatement(req);
         pst.setString(1, id);
         pst.executeUpdate();
        
    }



     
     
}