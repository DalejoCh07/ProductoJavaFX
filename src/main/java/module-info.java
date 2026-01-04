module ec.edu.espoch.productojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espoch.productojavafx to javafx.fxml;
    exports ec.edu.espoch.productojavafx;
}
