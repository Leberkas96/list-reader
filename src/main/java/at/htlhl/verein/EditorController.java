package at.htlhl.verein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable {
    private Association association;
    @FXML
    public Button refreshButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button logoutButton;
    @FXML
    private CheckBox markedCheckBox;
    @FXML
    private Button markedButton;
    @FXML
    private ScrollBar scrollBarVert;
    @FXML
    private ScrollBar scrollBarHori;
    @FXML
    private Button newEntryButton;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<Association> tableView;
    @FXML
    private TableColumn<Association, String> uuid;
    @FXML
    private TableColumn<Association, String> number;
    @FXML
    private TableColumn<Association, String> name;
    @FXML
    private TableColumn<Association, String> location;
    @FXML
    private TableColumn<Association, String> chairman;
    @FXML
    private TableColumn<Association, String> yearOfCreation;
    @FXML
    private TableColumn<Association, Boolean> openChange;

    public EditorController() {

    }

    private AssRegister assRegister = AssRegister.getRegister();
    ObservableList<Association> observableList = FXCollections.observableArrayList(assRegister.getVereinList());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uuid.setCellValueFactory(new PropertyValueFactory<>("uuidProperty"));
        number.setStyle("-fx-alignment: CENTER-RIGHT;");
        yearOfCreation.setStyle("-fx-alignment: CENTER-RIGHT;");
        name.setCellValueFactory(new PropertyValueFactory<>("nameProperty"));
        number.setCellValueFactory(new PropertyValueFactory<>("numberProperty"));
        location.setCellValueFactory(new PropertyValueFactory<>("locationProperty"));
        chairman.setCellValueFactory(new PropertyValueFactory<>("chairmanProperty"));
        yearOfCreation.setCellValueFactory(new PropertyValueFactory<>("yearOfCreationProperty"));
        openChange.setCellValueFactory(new PropertyValueFactory<>("openChangeProperty"));
        tableView.setItems(observableList);
    }

    /**
     * When the Mark-Button is clicked, the method will set the boolean openChange of the association to true
     */
    @FXML
    protected void onMarkAction(ActionEvent event) throws IOException {
        ObservableList<Association> observableList = FXCollections.observableArrayList(assRegister.getVereinList());
        if (!observableList.get(tableView.getSelectionModel().getFocusedIndex()).getOpenChangeProperty()) {
            observableList = FXCollections.observableArrayList(assRegister.getVereinList());
            tableView.setItems(observableList);
            observableList.get(tableView.getSelectionModel().getFocusedIndex()).setOpenChangeProperty(true);
            CsvManager.setData(tableView.getSelectionModel().getFocusedIndex(), true, assRegister.getVereinList());
            assRegister.loadRegister();
            uuid.getCellValueFactory();
            observableList = FXCollections.observableArrayList(assRegister.getVereinList());
            tableView.setItems(observableList);
            tableView.refresh();
        } else {
            observableList = FXCollections.observableArrayList(assRegister.getVereinList());
            tableView.setItems(observableList);
            observableList.get(tableView.getSelectionModel().getFocusedIndex()).setOpenChangeProperty(false);
            CsvManager.setData(tableView.getSelectionModel().getFocusedIndex(), false, assRegister.getVereinList());
            assRegister.loadRegister();
            uuid.getCellValueFactory();
            observableList = FXCollections.observableArrayList(assRegister.getVereinList());
            tableView.setItems(observableList);
            tableView.refresh();
        }
    }
    @FXML
    protected void onLogout(ActionEvent event) throws IOException {
        App.setRoot("User-view");
    }

    @FXML
    protected void onNewEntry(ActionEvent event) throws IOException {
        App.setRoot("Form-view");
    }

    static boolean markedObjects;

    public void showMarkedList(ActionEvent event) throws IOException {
        if (markedObjects) {
            assRegister.loadRegister();
            uuid.getCellValueFactory();
            ObservableList<Association> observableList = FXCollections.observableArrayList(assRegister.getVereinList());
            tableView.setItems(observableList);
            tableView.refresh();
            markedObjects = false;
        } else {
            uuid.getCellValueFactory();
            observableList = FXCollections.observableArrayList(assRegister.getVereinList());
            tableView.setItems(observableList);
            for (int i = 0; i < observableList.size(); i++) {
                if (!(observableList.get(i).getOpenChangeProperty())) {
                    observableList.remove(i);
                    i--;
                }
                tableView.refresh();
            }
            markedObjects = true;
        }

    }

    @FXML
    protected void onRefreshButton(ActionEvent event) throws IOException {
        assRegister.loadRegister();
        uuid.getCellValueFactory();
        ObservableList<Association> observableList = FXCollections.observableArrayList(assRegister.getVereinList());
        tableView.setItems(observableList);
    }

    @FXML
    protected void onFilterUUIDAction(ActionEvent event) {
        uuid.getCellValueFactory();
        observableList = FXCollections.observableArrayList(assRegister.getVereinList());
        tableView.setItems(observableList);

        String uuid = searchField.textProperty().getValue();

        for (int i = 0; i < observableList.size(); i++) {
            if (!(observableList.get(i).getUuidProperty().contains(uuid))) {
                observableList.remove(i);
                i--;
            }
        }
        tableView.refresh();
    }
}
