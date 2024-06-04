package com.data;

import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import com.main.tugas6.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AdminMenu extends User {
    public static ArrayList<StudentMenu> studentList = new ArrayList<>();

    public void showAdminMenu(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label label = new Label("Admin Menu");
        grid.add(label, 0, 0);

        Button addBookButton = new Button("Add Book");
        addBookButton.setOnAction(e -> showAddBookMenu(primaryStage));

        Button removeBookButton = new Button("Remove Book");
        removeBookButton.setOnAction(e -> showRemoveBookMenu(primaryStage));

        Button viewBooksButton = new Button("View Books");
        viewBooksButton.setOnAction(e -> showViewBooksMenu(primaryStage));

        Button viewStudentButton = new Button("View Students");
        viewStudentButton.setOnAction(e -> showViewStudentsMenu(primaryStage));

        Button addStudentButton = new Button("Add Student");
        addStudentButton.setOnAction(e -> showAddStudentMenu(primaryStage));

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> new Main().start(primaryStage));

        grid.add(addBookButton, 0, 1);
        grid.add(removeBookButton, 0, 2);
        grid.add(viewBooksButton, 0, 3);
        grid.add(viewStudentButton, 0, 4);
        grid.add(addStudentButton, 0, 5);
        grid.add(logoutButton, 0, 6);

        Scene adminMenuScene = new Scene(grid, 320, 350);
        primaryStage.setScene(adminMenuScene);
        primaryStage.show();
    }

    private void showAddBookMenu(Stage primaryStage) {
        GridPane gridAdd = new GridPane();
        gridAdd.setAlignment(Pos.CENTER);
        gridAdd.setHgap(10);
        gridAdd.setVgap(10);
        gridAdd.setPadding(new Insets(25, 25, 25, 25));

        Label labelAdd = new Label("Tambah buku");
        gridAdd.add(labelAdd, 0, 0);

        Button textBookButton = new Button("Text Book");
        textBookButton.setOnAction(f -> {
            TextBook textBook = new TextBook("", "", "", "", 0);
            textBook.addBookForm(primaryStage);
        });

        Button storyBookButton = new Button("Story Book");
        storyBookButton.setOnAction(f -> {
            StoryBook storyBook = new StoryBook("", "", "", "", 0);
            storyBook.addBookForm(primaryStage);
        });

        Button historyBookButton = new Button("History Book");
        historyBookButton.setOnAction(f -> {
            HistoryBook historyBook = new HistoryBook("", "", "", "", 0);
            historyBook.addBookForm(primaryStage);
        });

        gridAdd.add(textBookButton, 0, 1);
        gridAdd.add(storyBookButton, 0, 2);
        gridAdd.add(historyBookButton, 0, 3);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu(primaryStage));
        gridAdd.add(backButton, 0, 4);

        Scene addBookScene = new Scene(gridAdd, 320, 250);
        primaryStage.setScene(addBookScene);
        primaryStage.show();
    }

    private void showRemoveBookMenu(Stage primaryStage) {
        GridPane gridRemove = new GridPane();
        gridRemove.setAlignment(Pos.CENTER);
        gridRemove.setHgap(10);
        gridRemove.setVgap(10);
        gridRemove.setPadding(new Insets(25, 25, 25, 25));

        Label labelRemove = new Label("Hapus Buku");
        gridRemove.add(labelRemove, 0, 0);

        Label idLabel = new Label("Masukkan ID Buku:");
        gridRemove.add(idLabel, 0, 1);

        TextField idField = new TextField();
        gridRemove.add(idField, 1, 1);

        Button removeButton = new Button("Hapus Buku");
        removeButton.setOnAction(e -> {
            String id = idField.getText();
            boolean found = false;
            for (int i = 0; i < User.bookList.size(); i++) {
                if (User.bookList.get(i).getId().equals(id)) {
                    User.bookList.remove(i);
                    found = true;
                    break;
                }
            }
            if (found) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Buku berhasil dihapus");
            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Buku tidak ditemukan");
            }
        });
        gridRemove.add(removeButton, 1, 2);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu(primaryStage));
        gridRemove.add(backButton, 1, 3);

        Scene removeBookScene = new Scene(gridRemove, 320, 250);
        primaryStage.setScene(removeBookScene);
        primaryStage.show();
    }

    private void showViewBooksMenu(Stage primaryStage) {
        GridPane gridView = new GridPane();
        gridView.setAlignment(Pos.CENTER);
        gridView.setHgap(10);
        gridView.setVgap(10);
        gridView.setPadding(new Insets(25, 25, 25, 25));

        Label labelView = new Label("Daftar Buku");
        Label labelView1 = new Label("Id Buku - judul buku - stock");
        gridView.add(labelView, 0, 0);
        gridView.add(labelView1,0,1);

        StringBuilder booksList = new StringBuilder();
        for (int i = 0; i < User.bookList.size(); i++) {
            booksList.append(User.bookList.get(i).getId()).append(" - ")
                    .append(User.bookList.get(i).getTitle()).append(" - ")
                    .append(User.bookList.get(i).getStock()).append("\n");
        }

        TextArea booksArea = new TextArea(booksList.toString());
        booksArea.setEditable(false);
        gridView.add(booksArea, 0, 2);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu(primaryStage));
        gridView.add(backButton, 0, 3);

        Scene viewBooksScene = new Scene(gridView, 320, 250);
        primaryStage.setScene(viewBooksScene);
        primaryStage.show();
    }

    private void showViewStudentsMenu(Stage primaryStage) {
        GridPane gridView = new GridPane();
        gridView.setAlignment(Pos.CENTER);
        gridView.setHgap(10);
        gridView.setVgap(10);
        gridView.setPadding(new Insets(25, 25, 25, 25));

        Label labelView = new Label("Daftar Mahasiswa");
        gridView.add(labelView, 0, 0);

        StringBuilder studentsList = new StringBuilder();
        for (StudentMenu student : studentList) {
            studentsList.append(student.getNim()).append(" - ")
                    .append(student.getName()).append(" - ")
                    .append(student.getFaculty()).append(" - ")
                    .append(student.getStudyProgram()).append("\n");
        }

        TextArea studentsArea = new TextArea(studentsList.toString());
        studentsArea.setEditable(false);
        gridView.add(studentsArea, 0, 1);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu(primaryStage));
        gridView.add(backButton, 0, 2);

        Scene viewStudentsScene = new Scene(gridView, 320, 250);
        primaryStage.setScene(viewStudentsScene);
        primaryStage.show();
    }

    private void showAddStudentMenu(Stage primaryStage) {
        GridPane gridAdd = new GridPane();
        gridAdd.setAlignment(Pos.CENTER);
        gridAdd.setHgap(10);
        gridAdd.setVgap(10);
        gridAdd.setPadding(new Insets(25, 25, 25, 25));

        Label labelAdd = new Label("Tambah Mahasiswa");
        gridAdd.add(labelAdd, 0, 0);

        Label nameLabel = new Label("Name:");
        gridAdd.add(nameLabel, 0, 1);

        TextField nameField = new TextField();
        gridAdd.add(nameField, 1, 1);

        Label nimLabel = new Label("NIM:");
        gridAdd.add(nimLabel, 0, 2);

        TextField nimField = new TextField();
        gridAdd.add(nimField, 1, 2);

        Label facultyLabel = new Label("Faculty:");
        gridAdd.add(facultyLabel, 0, 3);

        TextField facultyField = new TextField();
        gridAdd.add(facultyField, 1, 3);

        Label studyProgramLabel = new Label("Study Program:");
        gridAdd.add(studyProgramLabel, 0, 4);

        TextField studyProgramField = new TextField();
        gridAdd.add(studyProgramField, 1, 4);

        Button addButton = new Button("Add Student");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String faculty = facultyField.getText();
            String studyProgram = studyProgramField.getText();
            if (name.isEmpty() || nim.isEmpty() || faculty.isEmpty() || studyProgram.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Semua field harus diisi.");
            } else {
                addedStudent(name, nim, faculty, studyProgram);
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Mahasiswa berhasil ditambahkan.");
            }
        });

        gridAdd.add(addButton, 1, 5);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu(primaryStage));
        gridAdd.add(backButton, 1, 6);

        Scene addStudentScene = new Scene(gridAdd, 400, 350);
        primaryStage.setScene(addStudentScene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void addedStudent(String name, String nim, String faculty, String studyProgram) {
        StudentMenu student = new StudentMenu(name, nim, faculty, studyProgram);
        studentList.add(student);
    }

    public StudentMenu getStudentByNim(String nim) {
        for (StudentMenu studentMenu : studentList) {
            if (studentMenu != null && studentMenu.getNim().equals(nim)) {
                return studentMenu;
            }
        }
        return null;
    }
}
