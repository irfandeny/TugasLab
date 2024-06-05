module com.main.tugas6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.main.tugas6 to javafx.fxml;
    opens books to javafx.base;
    exports com.main.tugas6;
    exports com.data;
    opens com.data to javafx.fxml;
}