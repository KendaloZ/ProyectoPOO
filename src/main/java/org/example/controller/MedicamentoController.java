package org.example.controller;


import java.util.List;
import org.example.dao.MedicamentoDAO;
import org.example.model.Medicamento;

public class MedicamentoController {
    private final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    public List<Medicamento> obtenerMedicamentos() {
        return this.medicamentoDAO.obtenerTodos();
    }

    public boolean insertarMedicamento(Medicamento medicamento) {
        return this.medicamentoDAO.insertarMedicamento(medicamento);
    }

    public Medicamento obtenerPorId(int id) {
        return this.medicamentoDAO.obtenerMedicamentoPorId(id);
    }

    public boolean actualizarMedicamento(Medicamento medicamento) {
        return this.medicamentoDAO.editarMedicamento(medicamento);
    }

    public boolean eliminarMedicamento(int id) {
        return this.medicamentoDAO.eliminarMedicamento(id);
    }
}
