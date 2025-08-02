package org.example.controller;

import org.example.dao.MedicamentoClienteDAO;
import org.example.model.Medicamento;

import java.util.List;


public class MedicamentoClienteController {
    private MedicamentoClienteDAO medicamentoClienteDAO = new MedicamentoClienteDAO();

    public void asociarMedicamentoCliente(int idCliente, int idMedicamento) {
        this.medicamentoClienteDAO.asociarMedicamentoCliente(idCliente, idMedicamento);
    }

    public void editarMedicamentoCliente(int idCliente, int idMedicamento, int id) {
        this.medicamentoClienteDAO.editarMedicamentoCliente(idCliente, idMedicamento, id);
    }

    public boolean borrarMedicamentoCliente(int idCliente, int idMedicamento) {
        return this.medicamentoClienteDAO.desasociarMedicamentoCliente(idCliente, idMedicamento);
    }

    public List<Medicamento> obtenerMedicamentosClientes(int idCliente) {
        return this.medicamentoClienteDAO.obtenerMedicamentosClientes(idCliente);
    }
}
