module com.example.skgottalent {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.aspectj.runtime;


    opens com.example.skgottalent to javafx.fxml;
    exports com.example.skgottalent.controllers;
    opens com.example.skgottalent.controllers to javafx.fxml;
    exports com.example.skgottalent.models;
    opens com.example.skgottalent.models to javafx.fxml;
    exports com.example.skgottalent.competition;
    opens com.example.skgottalent.competition to javafx.fxml;
    exports com.example.skgottalent.gui;
    opens com.example.skgottalent.gui to javafx.fxml;
}