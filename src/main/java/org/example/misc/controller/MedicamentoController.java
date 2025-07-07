package org.example.misc.controller;

import org.example.misc.dao.MedicamentoDAO;
import org.example.misc.model.Medicamento;

import java.util.List;

public class MedicamentoController {
    private final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    public List<Medicamento> obtenerMedicamentos() {
        return medicamentoDAO.obtenerTodos();
    }

    public boolean insertarMedicamento(Medicamento medicamento) {
        return medicamentoDAO.insertarMedicamento(medicamento);
    }

    public Medicamento obtenerPorId(int id) {
        return medicamentoDAO.obtenerMedicamentoPorId(id);
    }

    public boolean actualizarMedicamento(Medicamento medicamento) {
        return medicamentoDAO.editarMedicamento(medicamento);
    }

    public boolean eliminarMedicamento(int id) {
        return medicamentoDAO.eliminarMedicamento(id);
    }
}
