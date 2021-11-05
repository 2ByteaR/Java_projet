package JavaFX.controleur;

import BDD.DAOFactory.DAOFactory;
import BDD.DAOFactory.Persistance;
import BDD.Métier.Abonnement;
import BDD.Métier.Client;
import BDD.Métier.Periodicite;
import BDD.Métier.Revue;
import javafx.collections.FXCollections;
import javafx.css.converter.StringConverter;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ControleurAbonnement implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private int id ;
    @FXML
    private MenuBar myMenuBar;
    @FXML
    private TableView<Abonnement> tblAbonnement ;
    @FXML
    private TableColumn<Abonnement,Date> colAbonnementDebut;

    @FXML
    private TableColumn<Abonnement,Date> colAbonnementFin;

    @FXML
    private TableColumn<Abonnement,Integer> colAbonnementId;

    @FXML
    private TableColumn<Abonnement,Integer> colAbonnementClient;

    @FXML private TableColumn<Abonnement,Integer> colAbonnementRevue;

    private String Newligne=System.getProperty("line.separator");

    @FXML private DatePicker dateDebTextAbonnement;

    @FXML private DatePicker dateFinTextAbonnement;

    @FXML private ChoiceBox<Revue> cbxRevueAbonnemement;

    @FXML private Button btnAjouterAbonnement;

    @FXML private Button btnModifierAbonnement;

    @FXML private Button btnSupprimerAbonnement;

    @FXML private ChoiceBox<Client> cbxClientAbonnement;




    public void refreshTable(){
        tblAbonnement.getItems().clear();
        this.tblAbonnement.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MYSQL).getAbonnementDAO().findAll());

    }

    public boolean verifAbonnement(LocalDate dateDeb,LocalDate dateFin){

        String message = "";
        boolean ok = true;

        if (dateDeb == null || dateFin == null){
            message = "Veuillez saisir des dates"+Newligne;
            ok = false;
        }else if (dateDeb.isAfter(dateFin)){
            message = "Veuillez saisir une date de début valide"+Newligne;
            ok = false;
        }
        if (cbxClientAbonnement.getValue() == null){
            message = message + "Veuillez saisir un Abonnement"+Newligne;
            ok = false;
        }
        if (cbxRevueAbonnemement.getValue() == null){
            message = message + "Veuillez saisir une Revue"+Newligne;
            ok = false;
        }

        if (!ok){
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Problème");
            dialog.setHeaderText("Problème de saisie");
            dialog.setContentText(message);
            dialog.showAndWait();
        }
        return ok ;
    }

    public void switchToAppAccueil(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("../vue/AppAccueil.fxml"));
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
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        cbxClientAbonnement.setItems(FXCollections.observableArrayList(dao.getClientDAO().findAll()));
        cbxRevueAbonnemement.setItems(FXCollections.observableArrayList(dao.getRevueDAO().findAll()));


        colAbonnementId.setCellValueFactory(new PropertyValueFactory<Abonnement,Integer>("Id_abonnement"));
        colAbonnementDebut.setCellValueFactory(new PropertyValueFactory<Abonnement,Date>("Date_deb"));
        colAbonnementFin.setCellValueFactory(new PropertyValueFactory<Abonnement,Date>("Date_fin"));
        colAbonnementRevue.setCellValueFactory(new PropertyValueFactory<Abonnement,Integer>("Id_revue"));
        colAbonnementClient.setCellValueFactory(new PropertyValueFactory<Abonnement,Integer>("Id_client"));

        this.tblAbonnement.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MYSQL).getAbonnementDAO().findAll());

        this.tblAbonnement.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    this.btnSupprimerAbonnement.setDisable(newValue == null);
                    this.btnModifierAbonnement.setDisable(newValue == null);
                    this.btnAjouterAbonnement.setDisable(newValue != null);

                    Abonnement abonnement = tblAbonnement.getSelectionModel().getSelectedItem();
                    if (!(abonnement == null)) {
                        cbxRevueAbonnemement.getSelectionModel().select((Revue) DAOFactory.getDAOFactory(Persistance.MYSQL).getRevueDAO().getById(abonnement.getId_revue()));
                        cbxClientAbonnement.getSelectionModel().select((Client) DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(abonnement.getId_client()));
                    }
                });
    }

    @FXML
    void methodeAjouter(ActionEvent event) {

        if (verifAbonnement(dateDebTextAbonnement.getValue(),dateFinTextAbonnement.getValue())){

            Revue revue = cbxRevueAbonnemement.getValue();
            Client client = cbxClientAbonnement.getValue();
            Date dateFin = Date.from(dateFinTextAbonnement.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date dateDeb = Date.from(dateFinTextAbonnement.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

            DAOFactory.getDAOFactory(Persistance.MYSQL).getAbonnementDAO().create(new Abonnement(0,dateDeb,dateFin, revue.getId_revue(),client.getId_client()));

            refreshTable();
        }


    }

    @FXML
    void methodeModifier(ActionEvent event) {
        if (verifAbonnement(dateDebTextAbonnement.getValue(),dateFinTextAbonnement.getValue())){

            Revue revue = cbxRevueAbonnemement.getValue();
            Client client = cbxClientAbonnement.getValue();
            Date dateFin = Date.from(dateFinTextAbonnement.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date dateDeb = Date.from(dateFinTextAbonnement.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

            DAOFactory.getDAOFactory(Persistance.MYSQL).getAbonnementDAO().update(new Abonnement(this.id,dateDeb,dateFin, revue.getId_revue(),client.getId_client()));

            refreshTable();
        }
    }

    @FXML
    void methodeSupprimer(ActionEvent event) {
        DAOFactory.getDAOFactory(Persistance.MYSQL).getAbonnementDAO().delete(tblAbonnement.getSelectionModel().getSelectedItem());
        refreshTable();

    }


}
