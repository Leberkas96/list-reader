module list.listreader {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens list.listreader to javafx.fxml;
    exports list.listreader;
}