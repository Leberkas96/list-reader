package list;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class App extends Application {
    private static Scene scene;
    public static final String APP_NAME = "List-Reader";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = findFXMLLoader("User-view");
            scene = new Scene(fxmlLoader.load(), 1024, 768);
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


    public static FXMLLoader findFXMLLoader(String fxml) throws IOException {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public static void setRoot(String fxml) {
        try {
            scene.setRoot(findFXMLLoader(fxml).load());
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
