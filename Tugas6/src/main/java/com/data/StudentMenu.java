package com.data;

import books.Buku;
import com.main.tugas6.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.iMenu;
import java.util.ArrayList;

public class StudentMenu extends User implements iMenu {
    String nim;
    String name;
    String faculty;
    String studyProgram;

    private static final ArrayList<Buku> borrowedBooks = new ArrayList<>();

    public StudentMenu(String name, String nim, String faculty, String studyProgram) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
    }

    public StudentMenu() {}

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

    @Override
    public void showMenu(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label label = new Label("Student Menu");
        label.setFont(Font.font("Times new roman", FontWeight.BOLD, 18));
        grid.add(label, 0, 0, 2, 1);

        Button viewBooksButton = new Button("View Books");
        viewBooksButton.setOnAction(e -> showBooks(primaryStage));

        Button borrowBooksButton = new Button("Borrow Books");
        borrowBooksButton.setOnAction(e -> borrowBooks(primaryStage));

        Button showBorrowedBooksButton = new Button("Show Borrowed Books");
        showBorrowedBooksButton.setOnAction(e -> showBorrowedBooks(primaryStage));

        Button returnBooksButton = new Button("Return Books");
        returnBooksButton.setOnAction(e -> returnBooks(primaryStage));

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> new Main().start(primaryStage));

        grid.add(viewBooksButton, 0, 1);
        grid.add(borrowBooksButton, 0, 2);
        grid.add(showBorrowedBooksButton, 1, 1);
        grid.add(returnBooksButton, 1, 2);
        grid.add(logoutButton, 0, 3);

        Scene studentMenuScene = new Scene(grid, 420, 350);
        primaryStage.setScene(studentMenuScene);
        primaryStage.show();
    }


    @Override
    public TableView<Buku> displayBooks(){
        return super.displayBooks();
    }

    private void showBooks(Stage primaryStage) {
        GridPane gridView = new GridPane();
        gridView.setAlignment(Pos.CENTER);
        gridView.setHgap(10);
        gridView.setVgap(10);
        gridView.setPadding(new Insets(25, 25, 25, 25));

        TableView<Buku> tableStudent = new TableView<>();
        tableStudent.getItems().clear();
        tableStudent = displayBooks();
        gridView.add(tableStudent, 0, 1);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMenu(primaryStage));
        gridView.add(backButton, 0, 2);

        Scene viewBooksScene = new Scene(gridView, 420, 350);
        primaryStage.setScene(viewBooksScene);
        primaryStage.show();
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
                    showAlert(Alert.AlertType.ERROR, "Error", "The maximum borrowing period is 60 days.");
                    return;
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid number of days.");
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
                showAlert(Alert.AlertType.ERROR, "Error", "Book ID not found.");
                return;
            }

            try {
                if (bukuygPinjam.getStock() > 0) {
                    bukuygPinjam.setStock(bukuygPinjam.getStock() - 1);
                    bukuygPinjam.setDaysToReturn(daysToReturn);
                    borrowedBooks.add(bukuygPinjam);
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Book borrowed successfully for " + daysToReturn + " days.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Book is out of stock.");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while borrowing the book.");
                ex.printStackTrace();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMenu(primaryStage));

        vbox.getChildren().addAll(bookIdField, daysField, borrowButton, backButton);

        Scene borrowBooksScene = new Scene(vbox, 420, 350);
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
        backButton.setOnAction(e -> showMenu(primaryStage));
        vbox.getChildren().add(backButton);

        Scene borrowedBooksScene = new Scene(vbox, 420, 350);
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
                showAlert(Alert.AlertType.ERROR, "Error", "Book ID not found.");
                return;
            }

            try {
                for (Buku buku : borrowedBooks) {
                    if (buku.getId().equals(idBuku)) {
                        buku.setStock(buku.getStock() + 1);
                        borrowedBooks.remove(buku);
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Book returned successfully.");
                        break;
                    }
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while returning the book.");
                ex.printStackTrace();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMenu(primaryStage));

        vbox.getChildren().addAll(bookIdField, returnButton, backButton);

        Scene returnBooksScene = new Scene(vbox, 420, 350);
        primaryStage.setScene(returnBooksScene);
    }
    @Override
    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
