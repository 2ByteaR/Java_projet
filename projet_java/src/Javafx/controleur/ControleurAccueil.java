package Javafx.controleur;

import bdd.daofactory.DAOFactory;
import bdd.daofactory.Persistance;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleurAccueil implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static DAOFactory dao;
    @FXML
    private RadioButton radioListeMemoire;

    @FXML
    private RadioButton radioMySql;

    @FXML
    public void getDaoMethode(ActionEvent event) {
        if (radioListeMemoire.isSelected()){
            dao = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }else if (radioMySql.isSelected()){
            dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        }
    }

    public static DAOFactory getDAO(){
        return dao;
    }

    public void switchToAppRevue(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/AppRevue.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAppPeriodicite(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../vue/AppPeriodicite.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAppClient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../vue/AppClient.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAppAbonnement(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../vue/AppAbonnement.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        radioListeMemoire.setSelected(true);
    }
}

