package JavaFX.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.IOException;

public class ControleurPeriodicite {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML MenuBar myMenuBar;



    public void switchToAppAccueil(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/AppAccueil.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAppAbonnement(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/AppAbonnement.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAppRevue(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/AppRevue.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAppClient(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/AppClient.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void boutonFermer(ActionEvent event) {
        stage = (Stage) myMenuBar.getScene().getWindow();
        stage.close();
    }



}
