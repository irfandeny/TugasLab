package com.main.tugas6;

import com.data.AdminMenu;
import com.data.StudentMenu;
import com.data.User;
import exceptions.InvalidNimFormatException;
import exceptions.NimNotFoundException;
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
        grid.setPadding(new Insets(15, 15, 15, 15));

        Label label = new Label("Login as Student or Admin");
        label.setFont(Font.font("Times new roman", FontWeight.BOLD, 18));
        grid.add(label, 0, 0, 2, 1);

        Button loginAdmin = new Button("Login Admin");
        loginAdmin.setOnAction(e -> {
            GridPane adminGrid = new GridPane();
            adminGrid.setAlignment(Pos.CENTER);
            adminGrid.setHgap(10);
            adminGrid.setVgap(10);
            adminGrid.setPadding(new Insets(15, 15, 15, 15));

            Label labelAdmin = new Label("Masukkan Akun Anda");
            labelAdmin.setFont(Font.font("Times new roman", FontWeight.BOLD, 18));
            adminGrid.add(labelAdmin, 0, 0, 2, 1);
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
                        adminMenu.showMenu(primaryStage);
                    } else {
                        throw new Exception("Password Atau Username Salah");
                    }
                } catch (Exception ex) {
                    errorLabel.setText(ex.getMessage());
                }
            });

            Button backButton = new Button("Back");
            backButton.setOnAction(f -> start(primaryStage));
            adminGrid.add(backButton, 0, 4);

            Scene successScene = new Scene(adminGrid, 420, 350);
            primaryStage.setScene(successScene);
            primaryStage.show();
        });
        grid.add(loginAdmin, 0, 1);

        Button loginStudent = new Button("Login Student");
        loginStudent.setOnAction(e -> handleStudentLogin(primaryStage));
        grid.add(loginStudent, 1, 1);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));
        grid.add(exitButton, 0, 2);

        Button verApl = new Button("VerSion");
        verApl.setOnAction(e ->{
            GridPane gridver = new GridPane();
            gridver.setAlignment(Pos.CENTER);
            gridver.setHgap(10);
            gridver.setVgap(10);
            gridver.setPadding(new Insets(15, 15, 15, 15));
            Label labbVer = new Label("Ver:1.1");
            gridver.add(labbVer,0,0);

            Button backButton = new Button("Back");
            backButton.setOnAction(f -> start(primaryStage));
            gridver.add(backButton, 0, 4);

            Scene scenVer = new Scene(gridver,420,350);
            primaryStage.setScene(scenVer);
            primaryStage.show();
                });
        grid.add(verApl,1,4);

        Scene scene = new Scene(grid, 420, 350);
        primaryStage.setTitle("Form Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Main() {
        try {
            this.addTempStudent();
            this.addTempBook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTempStudent() {
        try {
            admin.addedStudent("Keysya", "202310370311363", "Teknik", "Informatika");
            admin.addedStudent("Irfan", "202310370311377", "Teknik", "Informatika");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTempBook() {
        try {
            User.addBook("388c-e681-9152", "Foxit eSign", "Author1", "Accessibility", 1);
            User.addBook("d95e-28c4-9523", "Nana Buku", "Author2", "Category", 2);
            User.addBook("sgsg-ytgf-we54", "Sejarah", "Author3", "Sejarah", 8);
            User.addBook("rdgf-rtgf-evgt", "Sejarah", "Author3", "Sejarah", 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleStudentLogin(Stage primaryStage) {
        GridPane studentGrid = new GridPane();
        studentGrid.setAlignment(Pos.CENTER);
        studentGrid.setHgap(10);
        studentGrid.setVgap(10);
        studentGrid.setPadding(new Insets(15, 15, 15, 15));

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
                validateNim(nimField.getText());
                StudentMenu student = admin.getStudentByNim(nimField.getText());
                if (student != null) {
                    StudentMenu studentMenu = new StudentMenu();
                    studentMenu.showMenu(primaryStage);
                } else {
                    throw new NimNotFoundException("NIM tidak ditemukan");
                }
            } catch (InvalidNimFormatException | NimNotFoundException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(f -> start(primaryStage));
        studentGrid.add(backButton, 0, 2);

        Scene successScene = new Scene(studentGrid, 420, 350);
        primaryStage.setScene(successScene);
        primaryStage.show();
    }

    private void validateNim(String nim) throws InvalidNimFormatException {
        if (nim.length() != 15) {
            throw new InvalidNimFormatException("NIM harus terdiri dari 15 angka.");
        }
        try {
            Long.parseLong(nim);
        } catch (NumberFormatException ex) {
            throw new InvalidNimFormatException("NIM harus berupa angka.");
        }
    }

}
