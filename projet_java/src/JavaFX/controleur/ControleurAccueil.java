package JavaFX.controleur;

import BDD.DAOFactory.DAOFactory;
import BDD.DAOFactory.Persistance;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class ControleurAccueil {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DAOFactory DAO;
    @FXML
    private RadioButton radioListeMemoire;

    @FXML
    private RadioButton radioMySql;

    @FXML
    void getDaoMethode(ActionEvent event) {
        if (radioListeMemoire.isSelected()){
            DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }else if (radioMySql.isSelected()){
            DAOFactory.getDAOFactory(Persistance.MYSQL);
        }
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

}

