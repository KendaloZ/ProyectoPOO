package org.example.view;

import org.example.controller.EspecialistaController;
import org.example.model.Especialista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EspecialistaUI extends JFrame {
    private final EspecialistaController especialistaController = new EspecialistaController();

    private JTextField idField, nombreField, cedulaField, correoField, telefonoField, direccionField, fechaField, generoField, especialidadField;
    private JTextArea outputArea;

    public EspecialistaUI() {
        setTitle("Gestión de Especialistas");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(10, 2));
        idField = new JTextField();
        nombreField = new JTextField();
        cedulaField = new JTextField();
        correoField = new JTextField();
        telefonoField = new JTextField();
        direccionField = new JTextField();
        fechaField = new JTextField(); // formato: yyyy-MM-dd
        generoField = new JTextField();
        especialidadField = new JTextField();

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Nombre Completo:"));
        inputPanel.add(nombreField);
        inputPanel.add(new JLabel("Cédula:"));
        inputPanel.add(cedulaField);
        inputPanel.add(new JLabel("Correo:"));
        inputPanel.add(correoField);
        inputPanel.add(new JLabel("Teléfono:"));
        inputPanel.add(telefonoField);
        inputPanel.add(new JLabel("Dirección:"));
        inputPanel.add(direccionField);
        inputPanel.add(new JLabel("Fecha Nacimiento (yyyy-MM-dd):"));
        inputPanel.add(fechaField);
        inputPanel.add(new JLabel("Género:"));
        inputPanel.add(generoField);
        inputPanel.add(new JLabel("Especialidad:"));
        inputPanel.add(especialidadField);

        JPanel buttonPanel = new JPanel();
        JButton insertarBtn = new JButton("Insertar");
        JButton actualizarBtn = new JButton("Actualizar");
        JButton eliminarBtn = new JButton("Eliminar");
        JButton mostrarBtn = new JButton("Mostrar Todos");
        JButton buscarBtn = new JButton("Buscar Especialista");

        buttonPanel.add(insertarBtn);
        buttonPanel.add(actualizarBtn);
        buttonPanel.add(eliminarBtn);
        buttonPanel.add(mostrarBtn);
        buttonPanel.add(buscarBtn);

        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        insertarBtn.addActionListener(this::insertarEspecialista);
        actualizarBtn.addActionListener(this::actualizarEspecialista);
        eliminarBtn.addActionListener(this::eliminarEspecialista);
        mostrarBtn.addActionListener(this::mostrarEspecialistas);
        buscarBtn.addActionListener(this::buscarEspecialista);
    }

    private void insertarEspecialista(ActionEvent e) {
        Especialista especialista = construirEspecialista(false);
        especialistaController.insertarEspecialista(especialista);
        outputArea.setText("Especialista insertado correctamente.");
    }

    private void actualizarEspecialista(ActionEvent e) {
        Especialista especialista = construirEspecialista(true);
        especialistaController.actualizarEspecialista(especialista);
        outputArea.setText("Especialista actualizado correctamente.");
    }

    private void eliminarEspecialista(ActionEvent e) {
        int id = Integer.parseInt(idField.getText());
        especialistaController.eliminarEspecialista(id);
        outputArea.setText("Especialista eliminado correctamente.");
    }

    private void mostrarEspecialistas(ActionEvent e) {
        List<Especialista> especialistas = especialistaController.obtenerEspecialistas();
        StringBuilder sb = new StringBuilder();
        for (Especialista esp : especialistas) {
            sb.append("ID: ").append(esp.getId())
                    .append(" | Nombre: ").append(esp.getNombreCompleto())
                    .append(" | Cédula: ").append(esp.getCedula())
                    .append(" | Correo: ").append(esp.getCorreo())
                    .append(" | Teléfono: ").append(esp.getTelefono())
                    .append(" | Dirección: ").append(esp.getDireccion())
                    .append(" | Fecha: ").append(esp.getFechaNacimiento())
                    .append(" | Género: ").append(esp.getGenero())
                    .append(" | Especialidad: ").append(esp.getEspecialidad())
                    .append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private void buscarEspecialista(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText());
            Especialista esp = especialistaController.obtenerEspecialista(id);
            if (esp != null) {
                outputArea.setText(
                        "ID: " + esp.getId() + "\n" +
                                "Nombre: " + esp.getNombreCompleto() + "\n" +
                                "Cédula: " + esp.getCedula() + "\n" +
                                "Correo: " + esp.getCorreo() + "\n" +
                                "Teléfono: " + esp.getTelefono() + "\n" +
                                "Dirección: " + esp.getDireccion() + "\n" +
                                "Fecha Nacimiento: " + esp.getFechaNacimiento() + "\n" +
                                "Género: " + esp.getGenero() + "\n" +
                                "Especialidad: " + esp.getEspecialidad()
                );
            } else {
                outputArea.setText("Especialista no encontrado con ID: " + id);
            }
        } catch (NumberFormatException ex) {
            outputArea.setText("Por favor ingrese un ID válido.");
        }
    }

    private Especialista construirEspecialista(boolean incluirId) {
        int id = incluirId ? Integer.parseInt(idField.getText()) : 0;
        String nombre = nombreField.getText();
        String cedula = cedulaField.getText();
        String correo = correoField.getText();
        int telefono = Integer.parseInt(telefonoField.getText());
        String direccion = direccionField.getText();
        LocalDate fecha = LocalDate.parse(fechaField.getText());
        Date fechaNacimiento = Date.valueOf(fecha);
        String genero = generoField.getText();
        String especialidad = especialidadField.getText();

        return incluirId
                ? new Especialista(id, nombre, cedula, correo, telefono, direccion, fechaNacimiento, genero, especialidad)
                : new Especialista(nombre, cedula, correo, telefono, direccion, fechaNacimiento, genero, especialidad);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EspecialistaUI().setVisible(true));
    }
}
