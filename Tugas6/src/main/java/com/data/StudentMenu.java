package com.data;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentMenu {
    String nim;
    String name;
    String faculty;
    String studyProgram;

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
        viewBooksButton.setOnAction(e -> {
            // Your logic to view books
        });

        Button borrowBooksButton = new Button("Borrow Books");
        borrowBooksButton.setOnAction(e -> {
            // Your logic to borrow books
        });

        grid.add(viewBooksButton, 0, 1);
        grid.add(borrowBooksButton, 0, 2);

        Scene studentMenuScene = new Scene(grid, 320, 250);
        primaryStage.setScene(studentMenuScene);
        primaryStage.show();
    }
}
