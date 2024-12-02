module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;

    exports com.example.demo.controller;
    opens com.example.demo.level to javafx.fxml;
    opens com.example.demo.actors to javafx.fxml;
    opens com.example.demo.actors.projectile to javafx.fxml;
    opens com.example.demo.view to javafx.fxml;
    opens com.example.demo.actors.plane to javafx.fxml;
    opens com.example.demo.actors.manager to javafx.fxml;
}