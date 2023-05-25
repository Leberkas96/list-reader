package list.listreader;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class List {
    private StringProperty contentsProperty = new SimpleStringProperty(this, "contents");
    private BooleanProperty checkProperty = new SimpleBooleanProperty(this, "check");
    private CheckBox checkBox;
    private ObjectProperty<CheckBox> checkBoxProperty = new SimpleObjectProperty<>();

    public List() {

    }

    public List(String contents, Boolean check) {
        this.contentsProperty.set(contents);
        this.checkProperty.set(check);
        this.checkBox = new CheckBox();
        checkBox.setSelected(check);
        checkBoxProperty.setValue(checkBox);
        /*
        checkBox.addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

            }
        });
         */
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
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

    public CheckBox getCheckBoxProperty() {
        return checkBoxProperty.get();
    }
}


