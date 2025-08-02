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
import org.example.controller.MedicamentoController;
import org.example.model.Medicamento;

import java.io.IOException;

public class MenuMedicamentoViewController {
    @FXML private TableView<Medicamento> tablaMedicamento;
    @FXML private TableColumn<Medicamento, Integer> colId;
    @FXML private TableColumn<Medicamento, String> colNombre;
    @FXML private TableColumn<Medicamento, Boolean> colDisponibilidad;
    @FXML private TextField campoBuscar;

    private final MedicamentoController medicamentoController = new MedicamentoController();
    private final ObservableList<Medicamento> listaMedicamentos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        colDisponibilidad.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isDisponible()));

        cargarMedicamento();
    }

    private void cargarMedicamento() {
        listaMedicamentos.clear();
        listaMedicamentos.addAll(medicamentoController.obtenerMedicamentos());
        tablaMedicamento.setItems(listaMedicamentos);
    }

    @FXML
    private void buscarMedicamento(ActionEvent event) {
        String texto = campoBuscar.getText().trim();

        if (texto.isEmpty()) {
            cargarMedicamento();
            return;
        }

        Medicamento resultado = medicamentoController.obtenerMedicamentoXNombre(texto);

        if (resultado == null) {
            try {
                int id = Integer.parseInt(texto);
                resultado = medicamentoController.obtenerPorId(id);
            } catch (NumberFormatException e) {
            }
        }

        listaMedicamentos.clear();
        if (resultado != null) {
            listaMedicamentos.add(resultado);
        } else {
            mostrarAlerta("No se encontró ningún medicamento con ese nombre o ID.");
        }
    }


    @FXML
    private void nuevoMedicamento(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/CrearMedicamento.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Crear Medicamento");
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error al cargar la vista del Menú Crear Medicamento.");
            e.printStackTrace();
        }
    }

    @FXML
    private void editarMedicamento(ActionEvent event) {
        Medicamento seleccionado = tablaMedicamento.getSelectionModel().getSelectedItem();
        if(seleccionado == null){
            mostrarAlerta("Seleccione un medicamento para eliminar.");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/EditarMedicamento.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            EditarMedicamentoViewController editarMedicamentoViewController = fxmlLoader.getController();
            editarMedicamentoViewController.setMedicamento(seleccionado);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Editar Medicamento");
            stage.show();
        }catch (IOException e) {
            mostrarAlerta("Error al cargar la vista de edición de medicamento.");
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminarMedicamento(ActionEvent event) {
        Medicamento seleccionado = tablaMedicamento.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un medicamento para eliminar.");
            return;
        }

        boolean eliminado = medicamentoController.eliminarMedicamento(seleccionado.getId());
        if (eliminado) {
            listaMedicamentos.remove(seleccionado);
        } else {
            mostrarAlerta("Error al eliminar el medicamento.");
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
