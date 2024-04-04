module com.example.skgottalent {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.skgottalent to javafx.fxml;
    exports com.example.skgottalent;
}