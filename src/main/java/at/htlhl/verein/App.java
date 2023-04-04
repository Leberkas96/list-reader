package at.htlhl.verein;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    private static Scene scene;
    public static final String APP_NAME = "Vereinsregister";

    @Override
    public void start(Stage stage) {
        //ActionEvent event = null;
        try {
            scene = new Scene(loadFXML("User-view"), 1024, 768);
            stage.setTitle(APP_NAME);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(App.APP_NAME);
            alert.setHeaderText("Loading Failed");
            alert.setContentText("An error occurred during the loading of the Scene");
            alert.showAndWait();
        }
    }

    protected static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("at.htlhl.verein." + fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"), resourceBundle);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        //Locale.setDefault(Locale.GERMAN);
        launch();
    }
}
