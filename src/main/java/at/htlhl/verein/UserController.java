package at.htlhl.verein;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;


public class UserController implements Initializable {
    private AssRegister assRegister = AssRegister.getRegister();


    @FXML
    public Button refreshButton;

    public Button searchButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox comb;

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
    private TableColumn<Association, Integer> yearOfCreation;

    ObservableList<Association> observableList = FXCollections.observableArrayList(assRegister.getVereinList());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("<=", "<", "=", ">=", ">");
        comb.setItems(list);
        comb.getSelectionModel().selectFirst();

        uuid.setCellValueFactory(new PropertyValueFactory<>("uuidProperty"));
        name.setCellValueFactory(new PropertyValueFactory<>("nameProperty"));
        number.setCellValueFactory(new PropertyValueFactory<>("numberProperty"));
        location.setCellValueFactory(new PropertyValueFactory<>("locationProperty"));
        chairman.setCellValueFactory(new PropertyValueFactory<>("chairmanProperty"));
        yearOfCreation.setCellValueFactory(new PropertyValueFactory<>("yearOfCreationProperty"));
        tableView.setItems(observableList);
        tableView.refresh();
    }

    /**
     * When Login-Button is clicked and the password is correct, the view changes to Editor-view.fxml.
     * If the password is incorrect an error message will appear.
     */
    @FXML
    protected void onLogin(ActionEvent event) throws IOException {
        if (passwordTextField.textProperty().getValue().equals("L")) {
            App.setRoot("Editor-view");
            EditorController.markedObjects = false;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(findParentWindow(event));
            alert.setTitle(App.APP_NAME);
            ResourceBundle resourceBundle = ResourceBundle.getBundle("at.htlhl.verein.Misc");
            alert.setHeaderText(resourceBundle.getString("pwErrorAlert.contentText"));
            alert.setContentText(resourceBundle.getString("pwErrorAlert.headerText"));
            alert.showAndWait();
        }
    }

    /**
     * Once the Filter-Button is clicked, when a number is entered with an operator selected, only the qualifying years will be shown.
     */
    @FXML
    protected void onFilterYearAction(ActionEvent event) {
        System.out.println("Servas1");
                System.out.println("Servas2");
                uuid.getCellValueFactory();
                observableList = FXCollections.observableArrayList(assRegister.getVereinList());
                tableView.setItems(observableList);
                String operator = comb.getSelectionModel().getSelectedItem().toString();
                if (searchField.textProperty().getValue() != "") {
                    int number = Integer.parseInt(searchField.textProperty().getValue());
                    switch (operator) {
                        case "<":
                            showLessThan(number);
                            break;
                        case "<=":
                            showLessEqualsThan(number);
                            break;
                        case "=":
                            showEquals(number);
                            System.out.println(observableList.size());
                            break;
                        case ">=":
                            showGreaterEquals(number);
                            break;
                        case ">":
                            showGreaterThan(number);
                    }


        }
        tableView.refresh();
    }

    private void showGreaterThan(int number) {
        for (int i = 0; i < observableList.size(); i++) {
            if (!(observableList.get(i).getYearOfCreationProperty() > number)) {
                observableList.remove(i);
                i--;
            }
        }
    }

    private void showGreaterEquals(int number) {
        for (int i = 0; i < observableList.size(); i++) {
            if (!(observableList.get(i).getYearOfCreationProperty() >= number)) {
                observableList.remove(i);
                i--;
            }
        }
    }

    private void showEquals(int number) {
        for (int i = 0; i < observableList.size(); i++) {
            if (!(observableList.get(i).getYearOfCreationProperty() == number)) {
                observableList.remove(i);
                i--;
            }
        }
    }

    private void showLessEqualsThan(int number) {
        for (int i = 0; i < observableList.size(); i++) {
            if (!(observableList.get(i).getYearOfCreationProperty() <= number)) {
                observableList.remove(i);
                i--;
            }
        }
    }

    private void showLessThan(int number) {
        for (int i = 0; i < observableList.size(); i++) {
            if (!(observableList.get(i).getYearOfCreationProperty() < number)) {
                observableList.remove(i);
                i--;
            }
        }
    }

    /**
     * When the Refresh-Button is clicked, the register gets loaded and all filters are reset.
     */
    @FXML
    protected void onRefreshButton(ActionEvent event) throws IOException {
        assRegister.loadRegister();
        uuid.getCellValueFactory();
        ObservableList<Association> observableList = FXCollections.observableArrayList(assRegister.getVereinList());
        tableView.setItems(observableList);
    }

    private Window findParentWindow(ActionEvent event) {
        return ((Node) event.getTarget()).getScene().getWindow();
    }
}
