module com.example.mystore {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.shop to javafx.fxml;
    exports com.example.shop;
}