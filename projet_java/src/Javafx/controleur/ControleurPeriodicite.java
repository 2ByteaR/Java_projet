package Javafx.controleur;

import bdd.daofactory.DAOFactory;
import bdd.metier.Periodicite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleurPeriodicite implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    private DAOFactory dao = ControleurAccueil.getDAO();

    @FXML private MenuBar myMenuBar;
    @FXML private TableView<Periodicite> tblPeriodicite;
    @FXML private TableColumn<Periodicite,Integer> colPerioId;
    @FXML private TableColumn<Periodicite,String> colPerioLibelle;
    @FXML private Button btnSupprimerPeriodicite;
    @FXML private TextField textFieldLibelle;
    @FXML private Button btnModifierPeriodicite;
    @FXML private Button btnAjouterPeriodicite;

    // Permet de mettre à jour les données du tableau quand on modifie,supprime ou ajouter
    public void refreshTable(){
        this.tblPeriodicite.getItems().clear();
        this.tblPeriodicite.getItems().addAll(dao.getPeriodicteDAO().findAll());
    }

    //On va vérifier que les données sont bien conforme
    public boolean verificationDonnee() {
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

        if (!ok) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Problème");
            dialog.setHeaderText("Problème dans le libelle");
            dialog.setContentText(message);
            dialog.showAndWait();
        }

        return true;
    }

//Le menu qui permet de changer de tableau
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


    //Suprime dans la table
    @FXML
    public void supprimer(ActionEvent event){

        dao.getPeriodicteDAO().delete(tblPeriodicite.getSelectionModel().getSelectedItem());
        this.tblPeriodicite.getItems().removeAll(tblPeriodicite.getSelectionModel().getSelectedItem());
    }

    //Ajoute dans la table
    @FXML
    public void ajouter(ActionEvent event){


        if (verificationDonnee()) {
            Periodicite pe = new Periodicite(0,textFieldLibelle.getText());
            dao.getPeriodicteDAO().create(pe);
            refreshTable();
        }
    }

    //Modifier dans la table
    @FXML
    void methodeModifier(ActionEvent event) {


        if (verificationDonnee()) {
            Periodicite periodicite = tblPeriodicite.getSelectionModel().getSelectedItem();
            dao.getPeriodicteDAO().update(periodicite);
            refreshTable();
        }

    }

    //Permet de fermer l'application
    @FXML
    public void boutonFermer(ActionEvent event) {
        stage = (Stage) myMenuBar.getScene().getWindow();
        stage.close();
    }

    //Methode qui permet de vider les texte et les bouton
    @FXML
    void methodeReset(ActionEvent event) {
        this.btnAjouterPeriodicite.setDisable(false);
        this.btnModifierPeriodicite.setDisable(true);
        this.btnSupprimerPeriodicite.setDisable(true);

        this.textFieldLibelle.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colPerioLibelle.setCellValueFactory(new PropertyValueFactory<Periodicite, String>("libelle"));
        colPerioId.setCellValueFactory(new PropertyValueFactory<Periodicite, Integer>("Id"));

        this.tblPeriodicite.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.btnSupprimerPeriodicite.setDisable(newValue == null);
                    this.btnModifierPeriodicite.setDisable(newValue == null);
                    this.btnAjouterPeriodicite.setDisable(newValue != null);

                    Periodicite periodicite = tblPeriodicite.getSelectionModel().getSelectedItem();
                    if (!(periodicite == null)) {
                        this.textFieldLibelle.setText(periodicite.getLibelle());
                    }
                });

        this.tblPeriodicite.getItems().addAll(dao.getPeriodicteDAO().findAll());


    }
}
