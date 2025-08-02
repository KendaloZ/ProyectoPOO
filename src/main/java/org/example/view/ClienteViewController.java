package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.controller.ClienteController;
import org.example.model.Cliente;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ClienteViewController {

    @FXML private TextField nombreField;
    @FXML private TextField cedulaField;
    @FXML private TextField correoField;
    @FXML private TextField telefonoField;
    @FXML private TextField direccionField;
    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private ComboBox<String> generoCombo;

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colCedula;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, Integer> colTelefono;
    @FXML private TableColumn<Cliente, String> colDireccion;
    @FXML private TableColumn<Cliente, Date> colFechaNac;
    @FXML private TableColumn<Cliente, String> colGenero;

    @FXML private Button actualizarBtn;
    @FXML private Button eliminarBtn;

    private final ClienteController controller = new ClienteController();
    private final ObservableList<Cliente> clientesObs = FXCollections.observableArrayList();

    private Cliente clienteSeleccionado; // para actualizar/eliminar

    @FXML
    public void initialize() {
        generoCombo.getItems().addAll("Masculino", "Femenino", "Otro");

        // Configurar columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));

        // Cargar datos
        recargarTabla();

        // Listener de selección
        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            clienteSeleccionado = newSel;
            boolean haySeleccion = newSel != null;
            actualizarBtn.setDisable(!haySeleccion);
            eliminarBtn.setDisable(!haySeleccion);
            if (haySeleccion) cargarClienteEnFormulario(newSel);
        });
    }

    private void recargarTabla() {
        clientesObs.clear();
        List<Cliente> lista = controller.obtenerClientes();
        clientesObs.addAll(lista);
        tablaClientes.setItems(clientesObs);
    }

    private void cargarClienteEnFormulario(Cliente c) {
        nombreField.setText(c.getNombreCompleto());
        cedulaField.setText(c.getCedula());
        correoField.setText(c.getCorreo());
        telefonoField.setText(String.valueOf(c.getTelefono()));
        direccionField.setText(c.getDireccion());
        if (c.getFechaNacimiento() != null) {
            fechaNacimientoPicker.setValue(c.getFechaNacimiento().toLocalDate());
        } else {
            fechaNacimientoPicker.setValue(null);
        }
        generoCombo.setValue(c.getGenero());
    }

    @FXML
    private void guardarCliente() {
        if (!validarFormulario()) return;

        Cliente cliente = new Cliente();
        llenarClienteDesdeFormulario(cliente);

        if (controller.insertarCliente(cliente)) {
            mostrarInfo("Éxito", "Cliente guardado correctamente.");
            recargarTabla();
            limpiarFormulario();
        } else {
            mostrarError("Error", "No se pudo guardar el cliente.");
        }
    }

    @FXML
    private void actualizarCliente() {
        if (clienteSeleccionado == null) return;
        if (!validarFormulario()) return;

        llenarClienteDesdeFormulario(clienteSeleccionado);

        if (controller.actualizarCliente(clienteSeleccionado)) {
            mostrarInfo("Éxito", "Cliente actualizado correctamente.");
            recargarTabla();
            limpiarFormulario();
        } else {
            mostrarError("Error", "No se pudo actualizar el cliente.");
        }
    }

    @FXML
    private void eliminarCliente() {
        if (clienteSeleccionado == null) return;
        if (confirmar("Confirmar", "¿Eliminar el cliente seleccionado?")) {
            if (controller.eliminarCliente(clienteSeleccionado.getId())) {
                mostrarInfo("Éxito", "Cliente eliminado correctamente.");
                recargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("Error", "No se pudo eliminar el cliente.");
            }
        }
    }

    @FXML
    private void limpiarFormulario() {
        nombreField.clear();
        cedulaField.clear();
        correoField.clear();
        telefonoField.clear();
        direccionField.clear();
        fechaNacimientoPicker.setValue(null);
        generoCombo.setValue(null);
        tablaClientes.getSelectionModel().clearSelection();
        clienteSeleccionado = null;
        actualizarBtn.setDisable(true);
        eliminarBtn.setDisable(true);
    }

    private void llenarClienteDesdeFormulario(Cliente cliente) {
        cliente.setNombreCompleto(nombreField.getText());
        cliente.setCedula(cedulaField.getText());
        cliente.setCorreo(correoField.getText());
        cliente.setTelefono(Integer.parseInt(telefonoField.getText()));
        cliente.setDireccion(direccionField.getText());
        cliente.setGenero(generoCombo.getValue());

        LocalDate ld = fechaNacimientoPicker.getValue();
        cliente.setFechaNacimiento(ld != null ? Date.valueOf(ld) : null);
    }

    private boolean validarFormulario() {
        if (nombreField.getText().isBlank()
                || cedulaField.getText().isBlank()
                || correoField.getText().isBlank()
                || telefonoField.getText().isBlank()
                || generoCombo.getValue() == null
        ) {
            mostrarError("Validación", "Complete los campos obligatorios.");
            return false;
        }
        try {
            Integer.parseInt(telefonoField.getText());
        } catch (NumberFormatException e) {
            mostrarError("Validación", "El teléfono debe ser numérico.");
            return false;
        }
        return true;
    }

    private void mostrarInfo(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setTitle(titulo);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void mostrarError(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(null);
        a.setTitle(titulo);
        a.setContentText(msg);
        a.showAndWait();
    }

    private boolean confirmar(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setTitle(titulo);
        a.setContentText(msg);
        return a.showAndWait().filter(b -> b == ButtonType.OK).isPresent();
    }
}