package com.data;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentMenu {

    public void showStudentMenu(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label label = new Label("Student Menu");
        root.getChildren().add(label);

        Button borrowBookButton = new Button("Borrow Book");
        borrowBookButton.setOnAction(e -> {
            // Borrow book functionality
        });
        root.getChildren().add(borrowBookButton);

        Button returnBookButton = new Button("Return Book");
        returnBookButton.setOnAction(e -> {
            // Return book functionality
        });
        root.getChildren().add(returnBookButton);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            primaryStage.close();
        });
        root.getChildren().add(logoutButton);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Student Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}