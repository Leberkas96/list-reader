package list.listreader;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CsvManager {

    /**
     * "loadData" loads the contents of the csv into the ListRegister
     *
     * @return is an ArrayList of the Object List which contains the item and a boolean
     */
    public static ArrayList<List> loadData() {
        var path = Paths.get("list.csv");
        ArrayList<List> tmpList = new ArrayList<>();
        try {
            //System.out.println(App.CONFIG_DIR_PATH+"/list.csv");
            for (String line : Files.readAllLines(path)) {
                if (line.contains(";")) {
                    String[] text = line.split(";");
                    tmpList.add(new List(text[0], Boolean.parseBoolean(text[1])));
                } else {
                    tmpList.add(new List(line, false));
                }
            }

            setData(tmpList);
        } catch (IOException ioex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(App.APP_NAME);
            alert.setHeaderText("Loading Failed");
            alert.setContentText("An error occurred during the loading of the list");
            alert.showAndWait();
        }
        return tmpList;
    }

    public static void setData(ArrayList<List> assList) throws IOException {
        var writeObjects = Files.newBufferedWriter(Paths.get("list.csv"));
        for (List list : assList) {
            writeObjects.write(list.getContentsProperty() + ";" + list.getCheckProperty() + "\n");
        }
        writeObjects.close();
    }

    public static void setData(int index, boolean change, ArrayList<List> listList) throws IOException {
        var writeObjects = Files.newBufferedWriter(Paths.get("list.csv"));
        listList.get(index).setCheckProperty(change);
        for (List list : listList) {
            writeObjects.write(list.getContentsProperty() + ";" + list.getCheckBoxProperty().isSelected() + "\n");
        }
        writeObjects.close();
    }
}




