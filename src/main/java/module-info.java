module com.example.spaceinvaders {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens com.example.spaceinvaders to javafx.fxml;
    exports com.example.spaceinvaders;
}