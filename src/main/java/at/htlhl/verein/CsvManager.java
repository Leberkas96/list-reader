package at.htlhl.verein;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

public class CsvManager {

    /**
     * "loadData" ladet die Daten der csv in das AssRegister hinein
     * @return
     */
    public static ArrayList<Association> loadData() {
        var path = Paths.get("associations.csv");
        ArrayList<Association> tmpAssociation = new ArrayList<>();
        try {
            for (String line : Files.readAllLines(path)) {
                // TODO: Use String.split() to get all values of one line
                String[] text = line.split(";");
                tmpAssociation.add(new Association(UUID.fromString(text[0]), Integer.parseInt(text[1]), text[2], text[3], text[4], Integer.parseInt(text[5]), Boolean.parseBoolean(text[6])));
                // TODO: instantiate the Association with the attributes of the file line
            }
        } catch (IOException ioex) {
            // TODO: do something in case ioex is caught!
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(App.APP_NAME);
            alert.setHeaderText("Loading Failed");
            alert.setContentText("An error occurred during the loading of the list");
            alert.showAndWait();
        }
        return tmpAssociation;
    }

    /**
     *
     * @param index
     * @param change
     * @param assList
     * "setData" schreibt das CSV File neu, indem es die Werte erneuert
     * @throws java.io.IOException
     */

    public static void setData(int index, boolean change, ArrayList<Association> assList) throws java.io.IOException {
        var fileName = java.nio.file.Paths.get("associations.csv");
        var writeObjects = java.nio.file.Files.newBufferedWriter(fileName);
        assList.get(index).setOpenChangeProperty(change);

        for (Association ass : assList) {
            writeObjects.write(ass.getUuidProperty() + ";" + ass.getNumberProperty() + ";" + ass.getNameProperty() + ";" + ass.getLocationProperty() + ";" + ass.getChairmanProperty() + ";" + ass.getYearOfCreationProperty() + ";" + ass.getOpenChangeProperty() + "\n");

        }

        writeObjects.close();

    }

    /**
     *
     * @param assList
     * @param assOb
     * "addData" fügt neue Daten einzeln in die csv hinzu
     * @throws IOException
     */

    public static void addData(ArrayList<Association> assList, Association assOb) throws IOException {
        assList.add(assOb);
        var fileName = java.nio.file.Paths.get("associations.csv");
        var writeObjects = java.nio.file.Files.newBufferedWriter(fileName);

        for (Association ass : assList) {
            writeObjects.write(ass.getUuidProperty() + ";" + ass.getNumberProperty() + ";" + ass.getNameProperty() + ";" + ass.getLocationProperty() + ";" + ass.getChairmanProperty() + ";" + ass.getYearOfCreationProperty() + ";" + ass.getOpenChangeProperty() + "\n");

        }

        writeObjects.close();


    }
}




