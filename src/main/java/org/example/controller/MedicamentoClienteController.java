package org.example.controller;

import org.example.dao.MedicamentoClienteDAO;


public class MedicamentoClienteController {
    private MedicamentoClienteDAO medicamentoClienteDAO = new MedicamentoClienteDAO();

    public void asociarMedicamentoCliente(int idCliente, int idMedicamento) {
        medicamentoClienteDAO.asociarMedicamentoCliente(idCliente, idMedicamento);
    }

    public void editarMedicamentoCliente(int idCliente, int idMedicamento, int id) {
        medicamentoClienteDAO.editarMedicamentoCliente(idCliente, idMedicamento, id);
    }

    public void borrarMedicamentoCliente(int idCliente) {
        medicamentoClienteDAO.desasociarMedicamentoCliente(idCliente);
    }
//    public void obtenerMedicamentosClientes(int idMedicamento) {
//        medicamentoClienteDAO.obtenerMedicamentosClientes(idMedicamento);
//    }
}
