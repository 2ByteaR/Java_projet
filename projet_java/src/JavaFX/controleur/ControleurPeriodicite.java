package JavaFX.controleur;

import BDD.DAOFactory.DAOFactory;
import BDD.DAOFactory.Persistance;
import BDD.Métier.Periodicite;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleurPeriodicite implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    private int id;
    @FXML private MenuBar myMenuBar;
    @FXML private TableView<Periodicite> tblPeriodicite;
    @FXML private TableColumn<Periodicite,Integer> colPerioId;
    @FXML private TableColumn<Periodicite,String> colPerioLibelle;
    @FXML private Button btnSupprimer;
    @FXML private TextField textFieldLibelle;
    @FXML private Label labelErreur;
    @FXML private Button btnAjouter;

    public void refreshTable(){
        this.tblPeriodicite.getItems().clear();
        this.tblPeriodicite.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MYSQL).getPeriodicteDAO().findAll());
    }


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

    @FXML
    public void supprimer(ActionEvent event){

        DAOFactory.getDAOFactory(Persistance.MYSQL).getPeriodicteDAO().delete(tblPeriodicite.getSelectionModel().getSelectedItem());
        this.tblPeriodicite.getItems().removeAll(tblPeriodicite.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void ajouter(ActionEvent event){
        labelErreur.setText("");
        String message = "";
        boolean ok = true;

        if (textFieldLibelle.getText().trim().equalsIgnoreCase("")){
            message = "Veuillez saisir libelle";
            ok = false;
        }
        else if (textFieldLibelle.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")){
            message = message + " Veuillez saisir que des lettres";
            ok = false;
        }

        if (ok) {
            Periodicite pe = new Periodicite(0,textFieldLibelle.getText());
            DAOFactory.getDAOFactory(Persistance.MYSQL).getPeriodicteDAO().create(pe);
            refreshTable();
        }else {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Problème");
            dialog.setHeaderText("Problème dans le libelle");
            dialog.setContentText(message);
            dialog.showAndWait();
        }
    }

    @FXML
    void methodeModifier(ActionEvent event) {
        labelErreur.setText("");
        String message = "";
        boolean ok = true;

        if (textFieldLibelle.getText().trim().equalsIgnoreCase("")){
            message = "Veuillez saisir libelle";
            ok = false;
        }
        else if (textFieldLibelle.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")){
            message = message + " Veuillez saisir que des lettres";
            ok = false;
        }

        if (ok) {
            Periodicite pe = new Periodicite(this.id,textFieldLibelle.getText());
            DAOFactory.getDAOFactory(Persistance.MYSQL).getPeriodicteDAO().update(pe);
        }else {
            labelErreur.setText(message);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colPerioLibelle.setCellValueFactory(new PropertyValueFactory<Periodicite, String>("libelle"));
        colPerioId.setCellValueFactory(new PropertyValueFactory<Periodicite, Integer>("Id"));

        this.tblPeriodicite.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MYSQL).getPeriodicteDAO().findAll());


    }


    @FXML
    void tableauSelectionner(MouseEvent event) {

            Periodicite mod = tblPeriodicite.getSelectionModel().getSelectedItem();//classe du model
            if (tblPeriodicite.isFocused() == true) {
                textFieldLibelle.requestFocus();
                this.btnAjouter.setDisable(true);
                textFieldLibelle.isFocused();
                textFieldLibelle.setText(mod.getLibelle());
                id = mod.getId();
            }
    }

    @FXML
    void tableauDeselectionner(MouseEvent event) {
            this.btnAjouter.setDisable(false);
            textFieldLibelle.setText("");
    }
}
