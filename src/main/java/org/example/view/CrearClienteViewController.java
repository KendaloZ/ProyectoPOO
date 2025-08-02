package org.example.view;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import org.example.controller.ClienteController;
import org.example.model.Cliente;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;;

public class CrearClienteViewController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private ComboBox<String> cbGenero;

    @FXML
    private TextArea txtPadecimiento;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnVolver;

    private final ClienteController clienteController = new ClienteController();


    @FXML
    public void initialize() {
        cbGenero.getItems().addAll("Masculino", "Femenino", "Otro");
    }

    @FXML
    private void guardarCliente(ActionEvent event) {
        if (validarCampos()) {
            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombreCompleto(txtNombre.getText());
            nuevoCliente.setCedula(txtCedula.getText());
            nuevoCliente.setCorreo(txtCorreo.getText());
            nuevoCliente.setTelefono(Integer.parseInt(txtTelefono.getText()));
            nuevoCliente.setDireccion(txtDireccion.getText());
            LocalDate fecha = dpFechaNacimiento.getValue();
            nuevoCliente.setFechaNacimiento(Date.valueOf(fecha));
            nuevoCliente.setGenero(cbGenero.getValue());
            nuevoCliente.setPadecimiento(txtPadecimiento.getText());

            boolean exito = clienteController.insertarCliente(nuevoCliente);

            if (exito) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente guardado correctamente");
                limpiarCampos();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar el cliente");
            }
        }
    }

    @FXML
    private void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuCliente.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menú Cliente");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo volver al menú de clientes");
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() ||
                txtCedula.getText().isEmpty() ||
                txtCorreo.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() ||
                txtDireccion.getText().isEmpty() ||
                dpFechaNacimiento.getValue() == null ||
                cbGenero.getValue() == null) {

            mostrarAlerta(Alert.AlertType.WARNING, "Validación", "Por favor, complete todos los campos");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtCedula.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        dpFechaNacimiento.setValue(null);
        cbGenero.setValue(null);
        txtPadecimiento.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
