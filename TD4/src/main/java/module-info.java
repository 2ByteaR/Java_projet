module com.example.td4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.td4 to javafx.fxml;
    exports com.example.td4;
}