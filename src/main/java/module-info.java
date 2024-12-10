module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;

    exports com.example.demo.controller;
    exports com.example.demo.view;
    exports com.example.demo.actors;
    opens com.example.demo.level to javafx.fxml;
    opens com.example.demo.actors to javafx.fxml;
    opens com.example.demo.actors.projectile to javafx.fxml;
    opens com.example.demo.view to javafx.fxml;
    opens com.example.demo.actors.plane to javafx.fxml;
    opens com.example.demo.manager to javafx.fxml;
    exports com.example.demo.manager;
}