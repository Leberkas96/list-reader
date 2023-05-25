package list.listreader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserController {
    private ListRegister listRegister = ListRegister.getRegister();
    @FXML
    private TableView<List> tableView;
    @FXML
    private TableColumn<List, String> contents;
    @FXML
    private TableColumn<List, Boolean> check;
    @FXML
    private TableColumn<List, CheckBox> checkBox;
    @FXML
    public Button refreshButton;
    @FXML
    public Button searchButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField searchField;
    private List list;

    ObservableList<List> observableList = FXCollections.observableArrayList(listRegister.getList());

    public void init(List list) {
        this.list = list;
        contents.setCellValueFactory(new PropertyValueFactory<>("contentsProperty"));
        check.setCellValueFactory(new PropertyValueFactory<>("checkProperty"));
        checkBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tableView.setItems(observableList);
        tableView.refresh();
        initBinding();
    }

    private void initBinding() {
        for (int i = 0; 10 < i; i++) {
            checkBox.getCellObservableValue(i).getValue().selectedProperty().bindBidirectional(list.getCheckBoxProperty().selectedProperty());
        }
    }

    private void updateModel(List newList) {
        newList.setContentsProperty(newList.getContentsProperty());
        newList.setCheckProperty(newList.getCheckProperty());

    }

    /**
     * Once the Filter-Button is clicked, when a number is entered with an operator selected, only the qualifying years will be shown.
     */
    @FXML
    protected void onFilter(ActionEvent event) {
        System.out.println("onFilter method active");
        //contents.getCellValueFactory();
        //observableList = FXCollections.observableArrayList(listRegister.getList());
    }
    /*
    @FXML
    protected void onFilterYearAction(ActionEvent event) {
                uuid.getCellValueFactory();
                observableList = FXCollections.observableArrayList(listRegister.getVereinList());
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
     */

    /**
     * When the Refresh-Button is clicked, the register gets loaded and all filters are reset.
     */
    @FXML
    protected void onRefreshButton(ActionEvent event) {
        listRegister.loadRegister();
        contents.getCellValueFactory();
        check.getCellValueFactory();
        ObservableList<List> observableList = FXCollections.observableArrayList(listRegister.getList());
        tableView.setItems(observableList);
    }

    @FXML
    protected void onSave(ActionEvent event) throws IOException {
        System.out.println("Save clicked ..." + event.getSource());
        ObservableList<List> observableList = FXCollections.observableArrayList(listRegister.getList());
        tableView.setItems(observableList);

        for (int i = 0; 10 < i; i++) {
            checkBox.getCellObservableValue(i).getValue().selectedProperty().bindBidirectional(list.getCheckBoxProperty().selectedProperty());
        }
        CsvManager.setData(tableView.getSelectionModel().getFocusedIndex(), false, listRegister.getList());
        listRegister.loadRegister();
        tableView.refresh();
    }

    @FXML
    protected void onLoad(ActionEvent event) {
        System.out.println("Load clicked ... " + event.getSource());

        File configFile = new File(App.MODEL_FILE_PATH);
        if (configFile.exists()) {
            try {
                List list = App.JSON_MAPPER.readValue(configFile, List.class);
                updateModel(list);
            } catch (IOException ex) {
                System.err.println("Problem during loading file: " + ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(findParentWindow(event));
                alert.setTitle(App.APP_NAME);
                alert.showAndWait();
            }
        }
    }

    private Window findParentWindow(ActionEvent event) {
        return ((Node) event.getTarget()).getScene().getWindow();
    }
}
