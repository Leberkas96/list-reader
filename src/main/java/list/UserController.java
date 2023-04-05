package list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;


public class UserController implements Initializable {
    private ListRegister listRegister = ListRegister.getRegister();
    @FXML
    private TableView<List> tableView;
    @FXML
    private TableColumn<List, String> contents;
    @FXML
    private TableColumn<List, Boolean> check;
    @FXML
    public Button refreshButton;
    @FXML
    public Button searchButton;
    @FXML
    private TextField searchField;

    ObservableList<List> observableList = FXCollections.observableArrayList(listRegister.getList());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contents.setCellValueFactory(new PropertyValueFactory<>("contentsProperty"));
        check.setCellValueFactory(new PropertyValueFactory<>("checkProperty"));
        tableView.setItems(observableList);
        tableView.refresh();
    }

    /**
     * Once the Filter-Button is clicked, when a number is entered with an operator selected, only the qualifying years will be shown.
     */
    @FXML
    protected void onFilter(ActionEvent event) {
        System.out.println("onFilter method active");
        contents.getCellValueFactory();
        observableList = FXCollections.observableArrayList(listRegister.getList());
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

    private Window findParentWindow(ActionEvent event) {
        return ((Node) event.getTarget()).getScene().getWindow();
    }
}
