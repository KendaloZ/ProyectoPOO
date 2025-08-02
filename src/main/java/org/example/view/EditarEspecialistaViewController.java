package org.example.view;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import org.example.controller.EspecialistaController;
import org.example.model.Especialista;

import java.io.IOException;
import java.sql.Date;
public class EditarEspecialistaViewController {

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
    private TextField txtEspecialidad;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnVolver;

    private final EspecialistaController especialistaController = new EspecialistaController();
    private Especialista  especialistaActual;

    @FXML
    public void initialize() {
        cbGenero.getItems().addAll("Masculino", "Femenino", "Otro");
    }

    public void setEspecialista(Especialista especialista) {
        this.especialistaActual = especialista;

        txtNombre.setText(especialista.getNombreCompleto());
        txtCedula.setText(especialista.getCedula());
        txtCorreo.setText(especialista.getCorreo());
        txtTelefono.setText(Integer.toString(especialista.getTelefono()));
        txtDireccion.setText(especialista.getDireccion());
        dpFechaNacimiento.setValue(especialista.getFechaNacimiento().toLocalDate());
        cbGenero.setValue(especialista.getGenero());
        txtEspecialidad.setText(especialista.getEspecialidad());

        txtCedula.setDisable(true);
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        if (validarCampos()) {
            especialistaActual.setNombreCompleto(txtNombre.getText());
            especialistaActual.setCorreo(txtCorreo.getText());
            especialistaActual.setTelefono(Integer.parseInt(txtTelefono.getText()));
            especialistaActual.setDireccion(txtDireccion.getText());
            especialistaActual.setFechaNacimiento(Date.valueOf(dpFechaNacimiento.getValue()));
            especialistaActual.setGenero(cbGenero.getValue());
            especialistaActual.setEspecialidad(txtEspecialidad.getText());
            boolean exito = especialistaController.actualizarEspecialista(especialistaActual);

            if (exito) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Especialista actualizado correctamente");
                volver(event);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el especialista");
            }
        }
    }

    @FXML
    private void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuEspecialista.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menú Especialista");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo volver al menú de especialiastas");
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
