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
import java.time.LocalDate;;

public class CrearEspecialistaViewController {
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

    @FXML
    public void initialize() {
        cbGenero.getItems().addAll("Masculino", "Femenino", "Otro");
    }

    @FXML
    private void guardarEspecialista(ActionEvent event) {
        if (validarCampos()) {
            Especialista nuevoEspecialista = new Especialista();
            nuevoEspecialista.setNombreCompleto(txtNombre.getText());
            nuevoEspecialista.setCedula(txtCedula.getText());
            nuevoEspecialista.setCorreo(txtCorreo.getText());
            nuevoEspecialista.setTelefono(Integer.parseInt(txtTelefono.getText()));
            nuevoEspecialista.setDireccion(txtDireccion.getText());
            LocalDate fecha = dpFechaNacimiento.getValue();
            nuevoEspecialista.setFechaNacimiento(Date.valueOf(fecha));
            nuevoEspecialista.setGenero(cbGenero.getValue());
            nuevoEspecialista.setEspecialidad(txtEspecialidad.getText());

            boolean exito = especialistaController.insertarEspecialista(nuevoEspecialista);

            if (exito) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Especialista guardado correctamente");
                limpiarCampos();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar el especialista");
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
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo volver al menú de especialista");
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
        txtEspecialidad.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

