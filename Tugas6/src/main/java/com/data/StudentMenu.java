package com.data;

import books.Buku;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StudentMenu {
    String nim;
    String name;
    String faculty;
    String studyProgram;

    private static ArrayList<Buku> borrowedBooks = new ArrayList<>();

    public StudentMenu(String name, String nim, String faculty, String studyProgram) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
    }

    public StudentMenu() {
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    public void showStudentMenu(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label label = new Label("Student Menu");
        grid.add(label, 0, 0);

        Button viewBooksButton = new Button("View Books");
        viewBooksButton.setOnAction(e -> displayBooks(primaryStage));

        Button borrowBooksButton = new Button("Borrow Books");
        borrowBooksButton.setOnAction(e -> borrowBooks(primaryStage));

        Button showBorrowedBooksButton = new Button("Show Borrowed Books");
        showBorrowedBooksButton.setOnAction(e -> showBorrowedBooks(primaryStage));

        Button returnBooksButton = new Button("Return Books");
        returnBooksButton.setOnAction(e -> returnBooks(primaryStage));

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> primaryStage.close());

        grid.add(viewBooksButton, 0, 1);
        grid.add(borrowBooksButton, 0, 2);
        grid.add(showBorrowedBooksButton, 0, 3);
        grid.add(returnBooksButton, 0, 4);
        grid.add(logoutButton, 0, 5);

        Scene studentMenuScene = new Scene(grid, 320, 250);
        primaryStage.setScene(studentMenuScene);
        primaryStage.show();
    }

    private void displayBooks(Stage primaryStage) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        for (Buku buku : User.bookList) {
            Label bookLabel = new Label(buku.getTitle() + " - " + buku.getAuthor() + " - " + buku.getCategory() + " - Stock: " + buku.getStock());
            vbox.getChildren().add(bookLabel);
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showStudentMenu(primaryStage));
        vbox.getChildren().add(backButton);

        Scene viewBooksScene = new Scene(vbox, 320, 250);
        primaryStage.setScene(viewBooksScene);
    }

    private void borrowBooks(Stage primaryStage) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        TextField bookIdField = new TextField();
        bookIdField.setPromptText("Enter Book ID");

        TextField daysField = new TextField();
        daysField.setPromptText("Enter number of days (max 60)");

        Button borrowButton = new Button("Borrow");
        borrowButton.setOnAction(e -> {
            String idBuku = bookIdField.getText();
            int daysToReturn;
            try {
                daysToReturn = Integer.parseInt(daysField.getText());
                if (daysToReturn > 60) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The maximum borrowing period is 60 days.");
                    alert.showAndWait();
                    return;
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid number of days.");
                alert.showAndWait();
                return;
            }

            Buku bukuygPinjam = null;
            for (Buku buku : User.bookList) {
                if (buku.getId().equals(idBuku)) {
                    bukuygPinjam = buku;
                    break;
                }
            }

            if (bukuygPinjam == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Book ID not found.");
                alert.showAndWait();
                return;
            }

            if (bukuygPinjam.getStock() > 0) {
                bukuygPinjam.setStock(bukuygPinjam.getStock() - 1);
                bukuygPinjam.setDaysToReturn(daysToReturn);
                borrowedBooks.add(bukuygPinjam);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book borrowed successfully for " + daysToReturn + " days.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Book is out of stock.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showStudentMenu(primaryStage));

        vbox.getChildren().addAll(bookIdField, daysField, borrowButton, backButton);

        Scene borrowBooksScene = new Scene(vbox, 320, 250);
        primaryStage.setScene(borrowBooksScene);
    }

    private void showBorrowedBooks(Stage primaryStage) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        if (borrowedBooks.isEmpty()) {
            vbox.getChildren().add(new Label("No books borrowed."));
        } else {
            for (Buku buku : borrowedBooks) {
                Label borrowedBookLabel = new Label(buku.getId() + " - " + buku.getTitle() + " - " + buku.getAuthor() + " - " + buku.getCategory() + " - Days to return: " + buku.getDaysToReturn());
                vbox.getChildren().add(borrowedBookLabel);
            }
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showStudentMenu(primaryStage));
        vbox.getChildren().add(backButton);

        Scene borrowedBooksScene = new Scene(vbox, 320, 250);
        primaryStage.setScene(borrowedBooksScene);
    }

    private void returnBooks(Stage primaryStage) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        TextField bookIdField = new TextField();
        bookIdField.setPromptText("Enter Book ID");

        Button returnButton = new Button("Return");
        returnButton.setOnAction(e -> {
            String idBuku = bookIdField.getText();
            Buku bukuPinjam = null;
            for (Buku buku : borrowedBooks) {
                if (buku.getId().equals(idBuku)) {
                    bukuPinjam = buku;
                    break;
                }
            }

            if (bukuPinjam == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Book ID not found.");
                alert.showAndWait();
                return;
            }

            for (Buku buku : borrowedBooks) {
                if (buku.getId().equals(idBuku)) {
                    buku.setStock(buku.getStock() + 1);
                    borrowedBooks.remove(buku);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book returned successfully.");
                    alert.showAndWait();
                    break;
                }
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showStudentMenu(primaryStage));

        vbox.getChildren().addAll(bookIdField, returnButton, backButton);

        Scene returnBooksScene = new Scene(vbox, 320, 250);
        primaryStage.setScene(returnBooksScene);
    }
}
