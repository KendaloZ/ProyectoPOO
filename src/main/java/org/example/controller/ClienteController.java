package org.example.controller;

import org.example.model.Cliente;
import org.example.dao.ClienteDAO;

import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO = new ClienteDAO();

    public boolean insertarCliente(Cliente itemCliente) {
        return this.clienteDAO.insertarCliente(itemCliente);
    }

    public boolean actualizarCliente(Cliente itemCliente) {
        return this.clienteDAO.actualizarCliente(itemCliente);
    }

    public boolean eliminarCliente(int id) {
        return this.clienteDAO.eliminarCliente(id);
    }

    public List<Cliente> obtenerClientes() {
        return this.clienteDAO.obtenerclientes();
    }

    public Cliente obtenerClienteXID(int id) {
        return this.clienteDAO.obtenerClienteXID(id);
    }

    public Cliente obtenerClienteXCedula(String cedula) {
        return this.clienteDAO.obtenerClienteXCedula(cedula);
    }

    public Cliente obtenerClienteXNombre(String nombreCompleto) {
        return this.clienteDAO.obtenerClienteXNombre(nombreCompleto);
    }
}

