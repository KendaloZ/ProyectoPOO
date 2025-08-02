package org.example.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import org.example.controller.ClienteController;
import org.example.model.Cliente;
import org.example.model.Medicamento;
import org.example.controller.MedicamentoClienteController;

import java.io.IOException;
import java.sql.Date;

public class EditarClienteViewController {

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
    private Cliente clienteActual;

    @FXML private TableView<Medicamento> tablaMedicamento;
    @FXML private TableColumn<Medicamento, Integer> colId;
    @FXML private TableColumn<Medicamento, String> colNombre;

    private final MedicamentoClienteController medicamentoController = new MedicamentoClienteController();
    private final ObservableList<Medicamento> listaMedicamentos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cbGenero.getItems().addAll("Masculino", "Femenino", "Otro");

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));

    }

    private void cargarMedicamento() {
        listaMedicamentos.clear();
        listaMedicamentos.addAll(medicamentoController.obtenerMedicamentosClientes(clienteActual.getId()));
        tablaMedicamento.setItems(listaMedicamentos);
    }


    // Recibe el cliente desde el controlador principal
    public void setCliente(Cliente cliente) {
        this.clienteActual = cliente;

        txtNombre.setText(cliente.getNombreCompleto());
        txtCedula.setText(cliente.getCedula());
        txtCorreo.setText(cliente.getCorreo());
        txtTelefono.setText(String.valueOf(cliente.getTelefono()));
        txtDireccion.setText(cliente.getDireccion());
        dpFechaNacimiento.setValue(cliente.getFechaNacimiento().toLocalDate());
        cbGenero.setValue(cliente.getGenero());
        txtPadecimiento.setText(cliente.getPadecimiento());

        txtCedula.setDisable(true);

        cargarMedicamento();
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        if (validarCampos()) {
            clienteActual.setNombreCompleto(txtNombre.getText());
            clienteActual.setCorreo(txtCorreo.getText());
            clienteActual.setTelefono(Integer.parseInt(txtTelefono.getText()));
            clienteActual.setDireccion(txtDireccion.getText());
            clienteActual.setFechaNacimiento(Date.valueOf(dpFechaNacimiento.getValue()));
            clienteActual.setGenero(cbGenero.getValue());
            clienteActual.setPadecimiento(txtPadecimiento.getText());
            boolean exito = clienteController.actualizarCliente(clienteActual);

            if (exito) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente actualizado correctamente");
                volver(event);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el cliente");
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

    @FXML
    private void abrirVentanaSeleccionMedicamento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/SeleccionarMedicamento.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Seleccionar Medicamento");
            stage.initOwner(btnGuardar.getScene().getWindow()); // ventana padre
            stage.showAndWait();

            SeleccionarMedicamentoViewController controller = loader.getController();
            Medicamento medicamentoSeleccionado = controller.getMedicamentoSeleccionado();

            if (medicamentoSeleccionado != null) {
                medicamentoController.asociarMedicamentoCliente(clienteActual.getId(), medicamentoSeleccionado.getId());
                cargarMedicamento(); // refresca la tabla
            }

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de selección de medicamentos");
        }
    }

    @FXML
    private void eliminarMedicamentoAsociado(ActionEvent event) {
        Medicamento seleccionado = tablaMedicamento.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING,"Validacion","Seleccione un medicamento para eliminar.");
            return;
        }

        boolean eliminado = medicamentoController.borrarMedicamentoCliente(clienteActual.getId(), seleccionado.getId());
        if (eliminado) {
            listaMedicamentos.remove(seleccionado);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error","Error al eliminar el medicamento.");
        }
    }


    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() ||
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

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
