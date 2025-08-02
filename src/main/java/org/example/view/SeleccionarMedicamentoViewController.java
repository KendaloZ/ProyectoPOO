package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.controller.MedicamentoController;
import org.example.model.Medicamento;


public class SeleccionarMedicamentoViewController {
    @FXML private TableView<Medicamento> tablaMedicamento;
    @FXML private TableColumn<Medicamento, Integer> colId;
    @FXML private TableColumn<Medicamento, String> colNombre;
    @FXML private TableColumn<Medicamento, Boolean> colDisponibilidad;
    @FXML private TextField campoBuscar;

    private final MedicamentoController medicamentoController = new MedicamentoController();
    private final ObservableList<Medicamento> listaMedicamentos = FXCollections.observableArrayList();

    private Medicamento medicamentoSeleccionado;
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        colDisponibilidad.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isDisponible()));

        listaMedicamentos.addAll(medicamentoController.obtenerMedicamentos());
        tablaMedicamento.setItems(listaMedicamentos);

        // Evento doble clic
        tablaMedicamento.setRowFactory(tv -> {
            TableRow<Medicamento> fila = new TableRow<>();
            fila.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !fila.isEmpty()) {
                    medicamentoSeleccionado = fila.getItem();
                    Stage stage = (Stage) tablaMedicamento.getScene().getWindow();
                    stage.close();
                }
            });
            return fila;
        });
    }

    public Medicamento getMedicamentoSeleccionado() {
        return medicamentoSeleccionado;
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) tablaMedicamento.getScene().getWindow();
        stage.close();
    }
}
