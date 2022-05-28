module ac.ucr.b66958.proyecto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ac.ucr.b66958.proyecto to javafx.fxml;
    exports ac.ucr.b66958.proyecto;
}