package org.example.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.controller.ClienteController;
import org.example.model.Cliente;

import java.io.IOException;

public class MenuClienteViewController {

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colCedula;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colDireccion;
    @FXML private TableColumn<Cliente, String> colFechaNacimiento;
    @FXML private TableColumn<Cliente, String> colGenero;
    @FXML private TableColumn<Cliente, String> colPadecimiento;
    @FXML private TextField campoBuscar;

    private final ClienteController clienteController = new ClienteController();
    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Asociar columnas con propiedades del modelo Cliente
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreCompleto()));
        colCedula.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCedula()));
        colCorreo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCorreo()));
        colTelefono.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getTelefono())));
        colDireccion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDireccion()));
        colFechaNacimiento.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFechaNacimiento().toString()));
        colGenero.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGenero()));
        colPadecimiento.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPadecimiento()));

        cargarClientes();
    }

    private void cargarClientes() {
        listaClientes.clear();
        listaClientes.addAll(clienteController.obtenerClientes());
        tablaClientes.setItems(listaClientes);
    }

    @FXML
    private void buscarCliente() {
        String texto = campoBuscar.getText().trim();

        if (texto.isEmpty()) {
            cargarClientes();
            return;
        }

        Cliente resultado = clienteController.obtenerClienteXNombre(texto);

        if (resultado == null) {
            resultado = clienteController.obtenerClienteXCedula(texto);
        }

        if (resultado == null) {
            try {
                int id = Integer.parseInt(texto);
                resultado = clienteController.obtenerClienteXID(id);
            } catch (NumberFormatException e) {
            }
        }

        listaClientes.clear();
        if (resultado != null) {
            listaClientes.add(resultado);
        } else {
            mostrarAlerta("No se encontró ningún cliente con ese nombre, cédula o ID.");
        }
    }


    @FXML
    private void nuevoCliente(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/CrearCliente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Crear Cliente");
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error al cargar la vista del Menú Principal.");
            e.printStackTrace();
        }
    }

    @FXML
    private void editarCliente(ActionEvent event) {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un cliente para editar.");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/EditarCliente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());


            EditarClienteViewController editarController = fxmlLoader.getController();
            editarController.setCliente(seleccionado);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Editar Cliente");
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error al cargar la vista de edición de cliente.");
            e.printStackTrace();
        }
    }


    @FXML
    private void eliminarCliente() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un cliente para eliminar.");
            return;
        }

        boolean eliminado = clienteController.eliminarCliente(seleccionado.getId());
        if (eliminado) {
            listaClientes.remove(seleccionado);
        } else {
            mostrarAlerta("Error al eliminar el cliente.");
        }
    }

    @FXML
    private void volver(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/MenuPrincipal.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menú Principal");
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error al cargar la vista del Menú Principal.");
            e.printStackTrace();
        }
    }


    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
