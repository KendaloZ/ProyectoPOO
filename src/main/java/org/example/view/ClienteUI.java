package org.example.view;

import org.example.controller.ClienteController;
import org.example.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ClienteUI extends JFrame {
    private final ClienteController clienteController = new ClienteController();

    // Campos de entrada
    private JTextField idField, nombreField, cedulaField, correoField, telefonoField, direccionField, fechaField, generoField, padecimientoField;
    private JTextArea outputArea;

    public ClienteUI() {
        setTitle("Gestión de Clientes");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(10, 2));
        idField = new JTextField();
        nombreField = new JTextField();
        cedulaField = new JTextField();
        correoField = new JTextField();
        telefonoField = new JTextField();
        direccionField = new JTextField();
        fechaField = new JTextField(); // formato: yyyy-MM-dd
        generoField = new JTextField();
        padecimientoField = new JTextField();

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
        inputPanel.add(new JLabel("Padecimiento:"));
        inputPanel.add(padecimientoField);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        JButton insertarBtn = new JButton("Insertar");
        JButton actualizarBtn = new JButton("Actualizar");
        JButton eliminarBtn = new JButton("Eliminar");
        JButton mostrarBtn = new JButton("Mostrar Todos");
        JButton buscarBtn = new JButton("Buscar Cliente");


        buttonPanel.add(insertarBtn);
        buttonPanel.add(actualizarBtn);
        buttonPanel.add(eliminarBtn);
        buttonPanel.add(mostrarBtn);
        buttonPanel.add(buscarBtn);

        // Área de salida
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Acciones
        insertarBtn.addActionListener(this::insertarCliente);
        actualizarBtn.addActionListener(this::actualizarCliente);
        eliminarBtn.addActionListener(this::eliminarCliente);
        mostrarBtn.addActionListener(this::mostrarClientes);
        buscarBtn.addActionListener(this::buscarCliente);

    }

    private void insertarCliente(ActionEvent e) {
        Cliente cliente = construirCliente(false);
        clienteController.insertarCliente(cliente);
        outputArea.setText("Cliente insertado correctamente.");
    }

    private void actualizarCliente(ActionEvent e) {
        Cliente cliente = construirCliente(true);
        clienteController.actualizarCliente(cliente);
        outputArea.setText("Cliente actualizado correctamente.");
    }

    private void eliminarCliente(ActionEvent e) {
        int id = Integer.parseInt(idField.getText());
        clienteController.eliminarCliente(id);
        outputArea.setText("Cliente eliminado correctamente.");
    }

    private void mostrarClientes(ActionEvent e) {
        List<Cliente> clientes = clienteController.obtenerClientes();
        StringBuilder sb = new StringBuilder();
        for (Cliente c : clientes) {
            sb.append("ID: ").append(c.getId())
                    .append(" | Nombre: ").append(c.getNombreCompleto())
                    .append(" | Cédula: ").append(c.getCedula())
                    .append(" | Correo: ").append(c.getCorreo())
                    .append(" | Teléfono: ").append(c.getTelefono())
                    .append(" | Dirección: ").append(c.getDireccion())
                    .append(" | Fecha: ").append(c.getFechaNacimiento())
                    .append(" | Género: ").append(c.getGenero())
                    .append(" | Padecimiento: ").append(c.getPadecimiento())
                    .append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private void buscarCliente(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText());
            Cliente cliente = clienteController.obtenerCliente(id);
            if (cliente != null) {
                outputArea.setText(
                        "ID: " + cliente.getId() + "\n" +
                                "Nombre: " + cliente.getNombreCompleto() + "\n" +
                                "Cédula: " + cliente.getCedula() + "\n" +
                                "Correo: " + cliente.getCorreo() + "\n" +
                                "Teléfono: " + cliente.getTelefono() + "\n" +
                                "Dirección: " + cliente.getDireccion() + "\n" +
                                "Fecha Nacimiento: " + cliente.getFechaNacimiento() + "\n" +
                                "Género: " + cliente.getGenero() + "\n" +
                                "Padecimiento: " + cliente.getPadecimiento()
                );
            } else {
                outputArea.setText("Cliente no encontrado con ID: " + id);
            }
        } catch (NumberFormatException ex) {
            outputArea.setText("Por favor ingrese un ID válido.");
        }
    }


    private Cliente construirCliente(boolean incluirId) {
        int id = incluirId ? Integer.parseInt(idField.getText()) : 0;
        String nombre = nombreField.getText();
        String cedula = cedulaField.getText();
        String correo = correoField.getText();
        int telefono = Integer.parseInt(telefonoField.getText());
        String direccion = direccionField.getText();
        LocalDate fecha = LocalDate.parse(fechaField.getText());
        Date fechaNacimiento = Date.valueOf(fecha);
        String genero = generoField.getText();
        String padecimiento = padecimientoField.getText();

        return incluirId
                ? new Cliente(id, nombre, cedula, correo, telefono, direccion, fechaNacimiento, genero, padecimiento)
                : new Cliente(nombre, cedula, correo, telefono, direccion, fechaNacimiento, genero, padecimiento);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteUI().setVisible(true));
    }
}
