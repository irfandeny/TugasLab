package books;

import com.data.AdminMenu;
import com.data.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TextBook extends Buku {
    String category = "Text Book";

    public TextBook(String id, String title, String author, String category, int stock) {
        super(id, title, author, category, stock);
    }

    public void addBookForm(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label titleLabel = new Label("Title:");
        grid.add(titleLabel, 0, 1);
        TextField titleField = new TextField();
        grid.add(titleField, 1, 1);

        Label authorLabel = new Label("Author:");
        grid.add(authorLabel, 0, 2);
        TextField authorField = new TextField();
        grid.add(authorField, 1, 2);

        Label stockLabel = new Label("Stock:");
        grid.add(stockLabel, 0, 3);
        TextField stockField = new TextField();
        grid.add(stockField, 1, 3);

        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> {
            setTitle(titleField.getText());
            setAuthor(authorField.getText());
            setStock(Integer.parseInt(stockField.getText()));
            setCategory(category);
            setId(Buku.generateId());
            if (category.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Semua field harus diisi.");
            } else {
                User.bookList.add(new HistoryBook(getId(), getTitle(), getAuthor(), getCategory(), getStock()));
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Buku berhasil ditambahkan.");
            }
        });
        grid.add(addButton, 1, 5);


        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new AdminMenu().showAdminMenu(primaryStage));
        grid.add(backButton, 2, 5);

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
