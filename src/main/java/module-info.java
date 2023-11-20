module com.mycompany.projectdesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires jackson.core;
    requires jackson.databind;

    opens com.mycompany.projectdesktop to javafx.fxml;
    exports com.mycompany.projectdesktop;
}
