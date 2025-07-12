package org.example.controller;

import org.example.model.Cliente;
import org.example.dao.ClienteDAO;

import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO = new ClienteDAO();

    public void insertarCliente(Cliente itemCliente){
        clienteDAO.insertarCliente(itemCliente);
    }

    public void actualizarCliente(Cliente itemCliente){
        clienteDAO.actualizarCliente(itemCliente);
    }

    public void eliminarCliente(int id){
        clienteDAO.eliminarCliente(id);
    }

    public List<Cliente> obtenerClientes(){
        return clienteDAO.obtenerclientes();
    }

    public Cliente obtenerCliente(int id){
        return clienteDAO.obtenerCliente(id);
    }

    public void asociarMedicamentoCliente(int idCliente, int idMedicamento) {
        clienteDAO.asociarMedicamentoCliente(idCliente, idMedicamento);
    }
}
