package com.main.tugas6;

import com.data.AdminMenu;
import com.data.StudentMenu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label label = new Label("Login as Student or Admin");
        label.setFont(Font.font("Times new roman", FontWeight.BOLD, 18));
        grid.add(label, 0, 0, 2, 1);

        Button loginAdmin = new Button("Login Admin");
        loginAdmin.setOnAction(e -> {
            GridPane adminGrid = new GridPane();
            adminGrid.setAlignment(Pos.CENTER);
            adminGrid.setHgap(10);
            adminGrid.setVgap(10);
            adminGrid.setPadding(new Insets(25, 25, 25, 25));

            Label userName = new Label("User Name\t:");
            adminGrid.add(userName, 0, 1);
            TextField userField = new TextField();
            adminGrid.add(userField, 1, 1);

            Label pw = new Label("Password\t\t:");
            adminGrid.add(pw, 0, 2);
            PasswordField pwField = new PasswordField();
            adminGrid.add(pwField, 1, 2);

            Button btn = new Button("Sign in");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            adminGrid.add(hbBtn, 1, 4);

            Label errorLabel = new Label("");
            errorLabel.setTextFill(Color.RED);
            adminGrid.add(errorLabel, 1, 3);

            btn.setOnAction(r -> {
                String username = userField.getText();
                String password = pwField.getText();

                if (username.equals("fanden") && password.equals("377")) {
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.showAdminMenu(primaryStage);
                } else {
                    errorLabel.setText("Password Atau Username Salah");
                }
            });

            Scene successScene = new Scene(adminGrid, 320, 250);
            primaryStage.setScene(successScene);
            primaryStage.show();
        });
        grid.add(loginAdmin, 0, 1);

        Button loginStudent = new Button("Login Student");
        loginStudent.setOnAction(e -> {
            StudentMenu studentMenu = new StudentMenu();
            studentMenu.showStudentMenu(primaryStage);
        });
        grid.add(loginStudent, 1, 1);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));
        grid.add(exitButton, 0, 2);

        Scene scene = new Scene(grid, 320, 250);
        primaryStage.setTitle("Form Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}