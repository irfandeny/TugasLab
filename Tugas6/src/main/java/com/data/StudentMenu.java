package com.data;

import books.Buku;
import com.main.tugas6.Main;
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
        table.getItems().clear();
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
        backButton.setOnAction(e -> showStudentMenu(primaryStage));

        vbox.getChildren().addAll(bookIdField, returnButton, backButton);

        Scene returnBooksScene = new Scene(vbox, 420, 350);
        primaryStage.setScene(returnBooksScene);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
