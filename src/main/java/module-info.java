module ITP3B.B5.Verein {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;
    requires org.junit.jupiter.api;
    requires javafx.base;

    opens at.htlhl.verein to javafx.fxml;
    exports at.htlhl.verein;
}