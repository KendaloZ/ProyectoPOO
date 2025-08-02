package org.example.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import org.example.controller.MedicamentoController;
import org.example.model.Medicamento;

import java.io.IOException;

public class EditarMedicamentoViewController {

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<Boolean> cbDisponible;

    private final MedicamentoController medicamentoController = new MedicamentoController();
    private Medicamento medicamentoActual
            ;
    @FXML
    private void initialize() {
        cbDisponible.getItems().addAll(true, false);
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamentoActual = medicamento;

        txtNombre.setText(medicamento.getNombre());
        cbDisponible.setValue(medicamento.isDisponible());

    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        if (validarCampos()) {
            medicamentoActual.setNombre(txtNombre.getText());
            medicamentoActual.setDisponible(cbDisponible.getValue());

            boolean exito = medicamentoController.actualizarMedicamento(medicamentoActual);

            if (exito) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Medicamento actualizado correctamente");
                volver(event);
            }else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el medicamento");
            }
        }
    }

    @FXML
    private void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuMedicamento.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menú Medicamento");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo volver al menú de medicamento");
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || cbDisponible.getValue() == null) {
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
