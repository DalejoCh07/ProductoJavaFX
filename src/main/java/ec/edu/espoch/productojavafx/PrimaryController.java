package ec.edu.espoch.productojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class PrimaryController {

    @FXML private TextField txtNombre;
    @FXML private ComboBox<String> cbCategoria;
    @FXML private TextField txtPrecio;
    @FXML private DatePicker dpFecha;
    @FXML private Spinner<Integer> spStock;
    
    @FXML private TableView<Producto> tblProductos;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, String> colCategoria;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;

    private ObservableList<Producto> listaProductos;

    @FXML
    public void initialize() {
        // Configurar ComboBox y Spinner
        cbCategoria.setItems(FXCollections.observableArrayList("Electrónica", "Hogar", "Alimentos", "Ropa"));
        spStock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        
        // Configurar Tabla
        listaProductos = FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
        tblProductos.setItems(listaProductos);
    }

    @FXML
    private void guardarProducto() {
        try {
            String nombre = txtNombre.getText();
            String cat = cbCategoria.getValue();
            double precio = Double.parseDouble(txtPrecio.getText());
            LocalDate fecha = dpFecha.getValue();
            int stock = spStock.getValue();

            if (nombre.isEmpty() || cat == null || fecha == null) {
                mostrarAlerta("Error", "Por favor llene todos los campos.");
                return;
            }

            Producto p = new Producto(nombre, cat, precio, fecha, stock);
            listaProductos.add(p);
            limpiarFormulario();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "El precio debe ser un número válido.");
        }
    }

    @FXML
    private void eliminarProducto() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaProductos.remove(seleccionado);
        } else {
            mostrarAlerta("Atención", "Seleccione un producto de la tabla para eliminar.");
        }
    }

    @FXML
    private void limpiarFormulario() {
        txtNombre.clear();
        cbCategoria.setValue(null);
        txtPrecio.clear();
        dpFecha.setValue(null);
        spStock.getValueFactory().setValue(0);
        tblProductos.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
