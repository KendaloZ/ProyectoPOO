package org.example.controller;

import org.example.dao.VisitaMedicamentoDAO;

public class VisitaMedicamentoController {
    private final VisitaMedicamentoDAO visitaMedicamentoDAO = new VisitaMedicamentoDAO();

    public void asociarMedicamentoVisita(int idVisita, int idMedicamento) {
        this.visitaMedicamentoDAO.asociarMedicamentoVisita(idVisita, idMedicamento);
    }

    public void editarMedicamentoVisita(int idVisita, int idMedicamento, int id) {
        this.visitaMedicamentoDAO.editarMedicamentoVisita(idVisita, idMedicamento, id);
    }

    public void borrarMedicamentoVisita(int id) {
        this.visitaMedicamentoDAO.desasociarMedicamentoVisita(id);
    }

    public void obtenerMedicamentosPorVisita(int idVisita) {
        this.visitaMedicamentoDAO.obtenerMedicamentosPorVisita(idVisita);
    }
}
