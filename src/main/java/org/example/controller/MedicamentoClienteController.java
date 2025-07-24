package org.example.controller;

import org.example.dao.MedicamentoClienteDAO;


public class MedicamentoClienteController {
    private MedicamentoClienteDAO medicamentoClienteDAO = new MedicamentoClienteDAO();

    public void asociarMedicamentoCliente(int idCliente, int idMedicamento) {
        this.medicamentoClienteDAO.asociarMedicamentoCliente(idCliente, idMedicamento);
    }

    public void editarMedicamentoCliente(int idCliente, int idMedicamento, int id) {
        this.medicamentoClienteDAO.editarMedicamentoCliente(idCliente, idMedicamento, id);
    }

    public void borrarMedicamentoCliente(int idCliente) {
        this.medicamentoClienteDAO.desasociarMedicamentoCliente(idCliente);
    }

    public void obtenerMedicamentosClientes(int idMedicamento) {
        this.medicamentoClienteDAO.obtenerMedicamentosClientes(idMedicamento);
    }
}
