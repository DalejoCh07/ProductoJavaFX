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
    private Producto productoEnEdicion = null; // Para saber si estamos editando uno existente

    @FXML
    public void initialize() {
        cbCategoria.setItems(FXCollections.observableArrayList("Electrónica", "Hogar", "Alimentos", "Ropa"));
        spStock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        
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
                mostrarAlerta("Error", "Llene todos los campos.");
                return;
            }

            if (productoEnEdicion == null) {
                // Agregar nuevo
                listaProductos.add(new Producto(nombre, cat, precio, fecha, stock));
            } else {
                // Actualizar existente
                productoEnEdicion.setNombre(nombre);
                productoEnEdicion.setCategoria(cat);
                productoEnEdicion.setPrecio(precio);
                productoEnEdicion.setFechaIngreso(fecha);
                productoEnEdicion.setStock(stock);
                tblProductos.refresh();
                productoEnEdicion = null;
            }
            limpiarFormulario();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio inválido.");
        }
    }

    @FXML
    private void cargarDatosParaEditar() {
        productoEnEdicion = tblProductos.getSelectionModel().getSelectedItem();
        if (productoEnEdicion != null) {
            txtNombre.setText(productoEnEdicion.getNombre());
            cbCategoria.setValue(productoEnEdicion.getCategoria());
            txtPrecio.setText(String.valueOf(productoEnEdicion.getPrecio()));
            dpFecha.setValue(productoEnEdicion.getFechaIngreso());
            spStock.getValueFactory().setValue(productoEnEdicion.getStock());
        } else {
            mostrarAlerta("Selección", "Seleccione un producto de la tabla.");
        }
    }

    @FXML
    private void eliminarProducto() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaProductos.remove(seleccionado);
        } else {
            mostrarAlerta("Atención", "Seleccione un producto.");
        }
    }

    @FXML
    private void limpiarFormulario() {
        txtNombre.clear();
        cbCategoria.setValue(null);
        txtPrecio.clear();
        dpFecha.setValue(null);
        spStock.getValueFactory().setValue(0);
        productoEnEdicion = null;
        tblProductos.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
