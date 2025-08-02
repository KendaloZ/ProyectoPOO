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
import org.example.controller.EspecialistaController;
import org.example.model.Especialista;

import java.io.IOException;

public class MenuEspecialistaViewController {
    @FXML private TableView<Especialista> tablaEspecialistas;
    @FXML private TableColumn<Especialista, Integer> colId;
    @FXML private TableColumn<Especialista, String> colNombre;
    @FXML private TableColumn<Especialista, String> colCedula;
    @FXML private TableColumn<Especialista, String> colCorreo;
    @FXML private TableColumn<Especialista, String> colTelefono;
    @FXML private TableColumn<Especialista, String> colDireccion;
    @FXML private TableColumn<Especialista, String> colFechaNacimiento;
    @FXML private TableColumn<Especialista, String> colGenero;
    @FXML private TableColumn<Especialista, String> colEspecialidad;
    @FXML private TextField campoBuscar;

    private final EspecialistaController especialistaController = new EspecialistaController();
    private final ObservableList<Especialista> listaEspecialistas = FXCollections.observableArrayList();

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
        colEspecialidad.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEspecialidad()));

        cargarEspecialistas();
    }

    private void cargarEspecialistas() {
        listaEspecialistas.clear();
        listaEspecialistas.addAll(especialistaController.obtenerEspecialistas());
        tablaEspecialistas.setItems(listaEspecialistas);
    }

    @FXML
    private void buscarEspecialista(ActionEvent event) {
        String texto = campoBuscar.getText().trim();

        if (texto.isEmpty()) {
            cargarEspecialistas();
            return;
        }

        Especialista resultado = especialistaController.obtenerEspecialistaXNombre(texto);

        if (resultado == null) {
            resultado = especialistaController.obtenerEspecialistaXCedula(texto);
        }

        if (resultado == null) {
            try {
                int id = Integer.parseInt(texto);
                resultado = especialistaController.obtenerEspecialistaXID(id);
            } catch (NumberFormatException e) {
            }
        }

        listaEspecialistas.clear();
        if (resultado != null) {
            listaEspecialistas.add(resultado);
        } else {
            mostrarAlerta("No se encontró ningún especialista con ese nombre, cédula o ID.");
        }
    }

    @FXML
    private void nuevoEspecialista(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/CrearEspecialista.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Crear Especialista");
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error al cargar la vista del Menú Principal.");
            e.printStackTrace();
        }
    }

    @FXML
    private void editarEspecialista(ActionEvent event) {
        Especialista seleccionado = tablaEspecialistas.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un especialista para editar.");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/EditarEspecialista.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            EditarEspecialistaViewController editarController = fxmlLoader.getController();
            editarController.setEspecialista(seleccionado);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Editar Especialista");
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error al cargar la vista de edición de especialista.");
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarEspecialista(ActionEvent event) {
        Especialista seleccionado = tablaEspecialistas.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un especialita para eliminar.");
            return;
        }

        boolean eliminado = especialistaController.eliminarEspecialista(seleccionado.getId());
        if (eliminado) {
            listaEspecialistas.remove(seleccionado);
        } else {
            mostrarAlerta("Error al eliminar el especialista.");
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
