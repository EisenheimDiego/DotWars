module ac.ucr.b66958.proyecto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    exports ac.ucr.b66958.proyecto.domain;

    opens ac.ucr.b66958.proyecto to javafx.fxml;
    exports ac.ucr.b66958.proyecto;
}