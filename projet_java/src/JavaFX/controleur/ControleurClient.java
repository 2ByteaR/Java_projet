package JavaFX.controleur;

import BDD.DAOFactory.DAOFactory;
import BDD.DAOFactory.Persistance;
import BDD.Métier.Client;
import BDD.Métier.Periodicite;
import BDD.Métier.Revue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleurClient implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    MenuBar myMenuBar;
    @FXML
    TableView<Client> tblClient;
    @FXML
    private TableColumn<Client,String> colClientAdresse;

    @FXML
    private TableColumn<Client,Integer> colClientId;

    @FXML
    private TableColumn<Client,String> colClientNom;

    @FXML
    private TableColumn<Client,String> colClientPrenom;




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

    public void switchToAppPeriodicite(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/AppPeriodicite.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void boutonFermer(ActionEvent event) {
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        colClientPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("Prenom"));
        colClientNom.setCellValueFactory(new PropertyValueFactory<Client, String>("Nom"));
        colClientId.setCellValueFactory(new PropertyValueFactory<Client, Integer>("Id_client"));
        colClientAdresse.setCellValueFactory(new PropertyValueFactory<Client, String>("CreationAdresse"));

        this.tblClient.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().findAll());
    }
}
