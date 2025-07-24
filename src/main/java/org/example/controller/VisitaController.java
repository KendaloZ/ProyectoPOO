package org.example.controller;

import java.util.List;
import org.example.dao.VisitaDAO;
import org.example.model.Visita;

public class VisitaController {
    private final VisitaDAO visitaDAO = new VisitaDAO();

    public List<Visita> obtenerVisitas() {
        return this.visitaDAO.obtenerTodasLasVisitas();
    }

    public boolean insertarVisita(Visita visita) {
        return this.visitaDAO.insertarVisita(visita);
    }

    public Visita obtenerPorId(int id) {
        return this.visitaDAO.obtenerVisitaPorId(id);
    }

    public boolean actualizarVisita(Visita visita) {
        return this.visitaDAO.actualizarVisita(visita);
    }

    public boolean eliminarVisita(int id) {
        return this.visitaDAO.eliminarVisita(id);
    }

    public String getNombreCliente(int idCliente) {
        return this.visitaDAO.obtenerNombreCliente(idCliente);
    }

    public String getNombreEspecialista(int idEspecialista) {
        return this.visitaDAO.obtenerNombreEspecialista(idEspecialista);
    }

    public List<String> getMedicamentosPorVisita(int idVisita) {
        return this.visitaDAO.obtenerMedicamentosPorVisita(idVisita);
    }
}
