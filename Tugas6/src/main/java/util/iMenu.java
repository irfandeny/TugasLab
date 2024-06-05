package util;

import javafx.stage.Stage;
import javafx.scene.control.Alert;

public interface iMenu {
    void showMenu(Stage primaryStage);
    void showAlert(Alert.AlertType alertType, String title, String message);
}
