module com.example.skgottalent {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.skgottalent to javafx.fxml;
    exports com.example.skgottalent;
    exports com.example.skgottalent.controllers;
    opens com.example.skgottalent.controllers to javafx.fxml;
    exports com.example.skgottalent.models;
    opens com.example.skgottalent.models to javafx.fxml;
}