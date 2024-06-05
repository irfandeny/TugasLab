package com.data;

import books.Buku;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StudentMenu {
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
    public StudentMenu() {
    }

    public String getNim() {
        return nim;
    }

    public void showStudentMenu(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label label = new Label("Student Menu");
        label.setFont(Font.font("Times new roman", FontWeight.BOLD, 18));
        grid.add(label, 0, 0, 2, 1);

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
        grid.add(showBorrowedBooksButton, 1, 1);
        grid.add(returnBooksButton, 1, 2);
        grid.add(logoutButton, 0, 3);

        Scene studentMenuScene = new Scene(grid, 420, 350);
        primaryStage.setScene(studentMenuScene);
        primaryStage.show();
    }

    private void displayBooks(Stage primaryStage) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        TableView<Buku> table = new TableView<>();
        table.setEditable(false);

        TableColumn<Buku, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Buku, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(150);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Buku, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setMinWidth(100);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Buku, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setMinWidth(100);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Buku, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setMinWidth(50);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table.getColumns().addAll(idColumn, titleColumn, authorColumn, categoryColumn, stockColumn);

        ObservableList<Buku> books = FXCollections.observableArrayList(User.bookList);
        table.setItems(books);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showStudentMenu(primaryStage));

        vbox.getChildren().addAll(table, backButton);

        Scene viewBooksScene = new Scene(vbox, 420, 350);
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
        backButton.setOnAction(e -> showStudentMenu(primaryStage));
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

        Scene returnBooksScene = new Scene(vbox, 420, 350);
        primaryStage.setScene(returnBooksScene);
    }
}
