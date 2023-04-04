package at.htlhl.verein;

import javafx.beans.property.*;

import java.util.UUID;

public class Association {
    private final StringProperty uuidProperty = new SimpleStringProperty(this, "uuid");
    private IntegerProperty numberProperty = new SimpleIntegerProperty(this, "number");
    private StringProperty nameProperty = new SimpleStringProperty(this, "name");
    private StringProperty locationProperty = new SimpleStringProperty(this, "location");
    private StringProperty chairmanProperty = new SimpleStringProperty(this, "chairman");
    private IntegerProperty yearOfCreationProperty = new SimpleIntegerProperty(this, "yearOfCreation");
    private BooleanProperty openChangeProperty = new SimpleBooleanProperty(this, "openChange");
    private boolean isMarked;

    public Association() {

    }

    public Association(UUID uuid, int number, String name, String location, String chairman, int yearOfCreation, boolean openChange) {
        if (uuid == null) {
            this.uuidProperty.set(UUID.randomUUID().toString());
        } else {
            this.uuidProperty.set(uuid.toString());
        }
        this.numberProperty.set(number);
        this.nameProperty.set(name);
        this.locationProperty.set(location);
        this.chairmanProperty.set(chairman);
        this.yearOfCreationProperty.set(yearOfCreation);
        this.openChangeProperty.set(openChange);
    }

    public String getUuidProperty() {
        return uuidProperty.get();
    }

    public void setUuidProperty(String uuidProperty) {
        this.uuidProperty.set(uuidProperty);
    }

    public int getNumberProperty() {
        return numberProperty.get();
    }


    public void setNumberProperty(int numberProperty) {
        this.numberProperty.set(numberProperty);
    }

    public String getNameProperty() {
        return nameProperty.get();
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public String getLocationProperty() {
        return locationProperty.get();
    }


    public void setLocationProperty(String locationProperty) {
        this.locationProperty.set(locationProperty);
    }

    public String getChairmanProperty() {
        return chairmanProperty.get();
    }


    public void setChairmanProperty(String chairmanProperty) {
        this.chairmanProperty.set(chairmanProperty);
    }

    public int getYearOfCreationProperty() {
        return yearOfCreationProperty.get();
    }


    public void setYearOfCreationProperty(int yearOfCreationProperty) {
        this.yearOfCreationProperty.set(yearOfCreationProperty);
    }

    public Boolean getOpenChangeProperty() {
        return openChangeProperty.get();
    }

    public void setOpenChangeProperty(boolean openChangeProperty) {
        this.openChangeProperty.set(openChangeProperty);
    }
}


