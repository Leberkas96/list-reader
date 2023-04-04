package at.htlhl.verein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.UUID;

public class FormController {
    @FXML
    private TextField numberField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField chairmanField;
    @FXML
    private TextField yearOfCreationField;
    @FXML
    private TextField openChange;
    @FXML
    private Button saveButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button cancelButton;
    private AssRegister model;

    UUID uuid = UUID.randomUUID();

    private AssRegister assRegister = AssRegister.getRegister();
    ObservableList<Association> observableList = FXCollections.observableArrayList(assRegister.getVereinList());

    public FormController() {

    }

    /**
     * When the Save-Button is clicked, the association gets written to the csv
     */
    @FXML
    protected void onSaveAction(ActionEvent event) throws IOException {
        // Erzeuge neue Association
        Association newAss;

        try {
            newAss = new Association(UUID.randomUUID(), Integer.parseInt(numberField.getText()), nameField.getText(), locationField.getText(), chairmanField.getText(), Integer.parseInt(yearOfCreationField.getText()), false);
            CsvManager.addData(assRegister.getVereinList(), newAss);
        } catch (NumberFormatException nfex) {
            // Alert werfen (Vereinsnummer oder Jahr der Entstehung ist keine Zahl)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(App.APP_NAME);
            alert.setHeaderText("Creation Failed");
            alert.setContentText("Associationnumber or YearOfCreation is not a number");
            alert.showAndWait();
        }
        App.setRoot("Editor-view");
    }

    @FXML
    protected void onCancelAction(ActionEvent event) throws IOException {
        App.setRoot("Editor-view");
    }

    @FXML
    protected void onLogout(ActionEvent event) throws IOException {
        App.setRoot("User-view");
    }
}