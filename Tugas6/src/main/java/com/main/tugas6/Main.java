package com.main.tugas6;

import com.data.AdminMenu;
import com.data.StudentMenu;
import com.data.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    AdminMenu admin = new AdminMenu();

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

            Label labelAdmin = new Label("Masukkan Akun Anda");
            labelAdmin.setFont(Font.font("Times new roman", FontWeight.BOLD, 18));
            adminGrid.add(label, 0, 0, 2, 1);
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
                try {
                    String username = userField.getText();
                    String password = pwField.getText();

                    if (username.equals("fanden") && password.equals("377")) {
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.showAdminMenu(primaryStage);
                    } else {
                        throw new Exception("Password Atau Username Salah");
                    }
                } catch (Exception ex) {
                    errorLabel.setText(ex.getMessage());
                }
            });
            Button backButton = new Button("Back");
            backButton.setOnAction(f -> start(primaryStage));
            adminGrid.add(backButton,0,4);

            Scene successScene = new Scene(adminGrid, 420, 350);
            primaryStage.setScene(successScene);
            primaryStage.show();
        });
        grid.add(loginAdmin, 0, 1);

        Button loginStudent = new Button("Login Student");
        loginStudent.setOnAction(e -> {
            GridPane studentGrid = new GridPane();
            studentGrid.setAlignment(Pos.CENTER);
            studentGrid.setHgap(10);
            studentGrid.setVgap(10);
            studentGrid.setPadding(new Insets(25, 25, 25, 25));

            Label labelStudent = new Label("Masukkan NIM anda");
            labelStudent.setFont(Font.font("Times new roman", FontWeight.BOLD, 18));
            studentGrid.add(labelStudent, 0, 0, 2, 1);
            Label nimLabel = new Label("NIM\t:");
            studentGrid.add(nimLabel, 0, 1);
            TextField nimField = new TextField();
            studentGrid.add(nimField, 1, 1);

            Button loginMenuStudent = new Button("Login");
            studentGrid.add(loginMenuStudent, 1, 2);
            loginMenuStudent.setOnAction(f -> {
                try {
                    String nim = nimField.getText();
                    StudentMenu student = admin.getStudentByNim(nim);
                    if (student != null) {
                        StudentMenu studentMenu = new StudentMenu();
                        studentMenu.showStudentMenu(primaryStage);
                    } else {
                        throw new IllegalArgumentException("NIM tidak ditemukan");
                    }
                } catch (IllegalArgumentException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }
            });

            Button backButton = new Button("Back");
            backButton.setOnAction(f -> start(primaryStage));
            studentGrid.add(backButton,0,2);

            Scene successScene = new Scene(studentGrid, 420, 350);
            primaryStage.setScene(successScene);
            primaryStage.show();
        });
        grid.add(loginStudent, 1, 1);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));
        grid.add(exitButton, 0, 2);

        Scene scene = new Scene(grid, 420, 350);
        primaryStage.setTitle("Form Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Main() {
        this.addTempStudent();
        this.addTempBook();
    }

    public void addTempStudent() {
        admin.addedStudent("Keysya", "202310370311363", "Teknik", "Informatika");
        admin.addedStudent("Irfan", "202310370311377", "Teknik", "Informatika");
    }

    public void addTempBook(){
        User.addBook("388c-e681-9152", "Foxit eSign", "Author1", "Accessibility", 1);
        User.addBook("d95e-28c4-9523", "Nana Buku", "Author2", "Category", 2);
        User.addBook("sgsg-ytgf-we54", "Sejarah", "Author3", "Sejarah", 8);
        User.addBook("rdgf-rtgf-evgt", "Sejarah", "Author3","Sejarah" , 8);
    }
}
