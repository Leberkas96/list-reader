module at.htlhl.bschach {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens list to javafx.fxml;
    exports list;
    //Removed module in compiler settings: --add-exports java.datatransfer/java.awt=at.htlhl.bschach,at.htlhl.bschach
}