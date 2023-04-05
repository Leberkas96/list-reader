package list;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class List {
    private StringProperty contentsProperty = new SimpleStringProperty(this, "contents");
    private BooleanProperty checkProperty = new SimpleBooleanProperty(this, "check");

    public List() {

    }

    public List(String contents, Boolean check) {
        this.contentsProperty.set(contents);
        this.checkProperty.set(check);
    }
    public String getContentsProperty() {
        return contentsProperty.get();
    }
    public void setContentsProperty(String contentsProperty) {
        this.contentsProperty.set(contentsProperty);
    }

    public Boolean getCheckProperty() {
        return checkProperty.get();
    }

    public void setCheckProperty(boolean openChangeProperty) {
        this.checkProperty.set(openChangeProperty);
    }
}


