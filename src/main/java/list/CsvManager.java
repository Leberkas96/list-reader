package list;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CsvManager {

    /**
     * "loadData" ladet die Daten der csv in das AssRegister hinein
     *
     * @return
     */
    public static ArrayList<List> loadData() {
        var path = Paths.get("list.csv");
        ArrayList<List> tmpList = new ArrayList<>();
        try {
            for (String line : Files.readAllLines(path)) {
                if(line.contains(";")) {
                    String[] text = line.split(";");
                    tmpList.add(new List(text[0], Boolean.parseBoolean(text[1])));
                } else {
                    tmpList.add(new List(line, false));
                }
            }
        } catch (IOException ioex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(App.APP_NAME);
            alert.setHeaderText("Loading Failed");
            alert.setContentText("An error occurred during the loading of the list");
            alert.showAndWait();
        }
        return tmpList;
    }

    /**
     * @param index
     * @param change
     * @param assList "setData" schreibt das CSV File neu, indem es die Werte erneuert
     * @throws IOException
     */

    public static void setData(int index, boolean change, ArrayList<List> assList) throws IOException {
        var fileName = Paths.get("list.csv");
        var writeObjects = Files.newBufferedWriter(fileName);
        assList.get(index).setCheckProperty(change);

        for (List ass : assList) {
            writeObjects.write(ass.getContentsProperty() + ";" + ass.getCheckProperty() + "\n");
        }
        writeObjects.close();
    }

    /**
     * @param assList
     * @param assOb   "addData" fügt neue Daten einzeln in die csv hinzu
     * @throws IOException
     */

    public static void addData(ArrayList<List> assList, List assOb) throws IOException {
        assList.add(assOb);
        var fileName = Paths.get("list.csv");
        var writeObjects = Files.newBufferedWriter(fileName);
        for (List ass : assList) {
            writeObjects.write(ass.getContentsProperty() + ";" + ass.getCheckProperty() + "\n");
        }

        writeObjects.close();


    }
}




