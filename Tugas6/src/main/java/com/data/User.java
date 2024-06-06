package com.data;

import books.Buku;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class User {
    public static ArrayList<Buku> bookList = new ArrayList<>();

    public static void addBook(String id, String title, String author, String category, int stock) {
        bookList.add(new Buku(id, title, author, category, stock));
    }

    public TableView<Buku> displayBooks() {
        TableView<Buku> tableDisplay = new TableView<>();
        tableDisplay.setEditable(true);

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

        tableDisplay.getColumns().addAll(idColumn, titleColumn, authorColumn, categoryColumn, stockColumn);
        updateTableItems(tableDisplay);

        return tableDisplay;
    }

    protected void updateTableItems(TableView<Buku> tableDisplay) {
        ObservableList<Buku> books = FXCollections.observableArrayList(bookList);
        tableDisplay.getItems().clear();
        tableDisplay.setItems(books);
    }
}
