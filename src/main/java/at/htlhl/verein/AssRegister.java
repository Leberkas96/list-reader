package at.htlhl.verein;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AssRegister {
    private static AssRegister register;
    private ArrayList<Association> vereinList;

    private AssRegister() {
        vereinList = CsvManager.loadData();
    }

    public static AssRegister getRegister() {
        if (register == null) {
            register = new AssRegister();
        }
        return register;
    }

    public void saveAssociation(Association newAss) {
        vereinList.add(newAss);

        var path = Paths.get("associations.csv");
        var writeAss = java.nio.file.Paths.get("association.csv");
        try {
            FileWriter fw = new FileWriter("associations.csv");
            StringBuilder finalString = new StringBuilder();
            for (Association ass : vereinList) {
                finalString.append(ass.getUuidProperty()).append(";");
                finalString.append(ass.getNumberProperty()).append(";");
                finalString.append(ass.getNameProperty()).append(";");
                finalString.append(ass.getLocationProperty()).append(";");
                finalString.append(ass.getChairmanProperty()).append(";");
                finalString.append(ass.getYearOfCreationProperty()).append(";");
                finalString.append(ass.getOpenChangeProperty()).append("\n");
            }
            fw.write(finalString.toString());
            fw.close();
        } catch (IOException ignored) {
        }
    }

    public ArrayList<Association> getVereinList() {
        return vereinList;
    }

    public static void setRegister(AssRegister register) {
        AssRegister.register = register;
    }

    public void setVereinList(ArrayList<Association> vereinList) {
        this.vereinList = vereinList;
    }

    public void loadRegister() {
        this.vereinList = CsvManager.loadData();
    }
}

