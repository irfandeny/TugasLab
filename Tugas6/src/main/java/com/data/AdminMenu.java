package com.data;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminMenu {

    public void showAdminMenu(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label label = new Label("Admin Menu");
        root.getChildren().add(label);

        Button addBookButton = new Button("Add Book");
        addBookButton.setOnAction(e -> {
            // Add book functionality
        });
        root.getChildren().add(addBookButton);

        Button removeBookButton = new Button("Remove Book");
        removeBookButton.setOnAction(e -> {
            // Remove book functionality
        });
        root.getChildren().add(removeBookButton);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            primaryStage.close();
        });
        root.getChildren().add(logoutButton);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Admin Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}