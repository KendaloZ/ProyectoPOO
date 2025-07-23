package org.example.misc.controller;

import org.example.misc.dao.VisitaDAO;
import org.example.misc.model.Visita;

import java.util.List;

public class VisitaController {

    private final VisitaDAO visitaDAO = new VisitaDAO();

    public List<Visita> obtenerVisitas() {
        return visitaDAO.obtenerTodasLasVisitas();
    }

    public boolean insertarVisita(Visita visita) {
        return visitaDAO.insertarVisita(visita);
    }

    public Visita obtenerPorId(int id) {
        return visitaDAO.obtenerVisitaPorId(id);
    }

    public boolean actualizarVisita(Visita visita) {
        return visitaDAO.actualizarVisita(visita);
    }

    public boolean eliminarVisita(int id) {
        return visitaDAO.eliminarVisita(id);
    }

    public String getNombreCliente(int idCliente) {
        return visitaDAO.obtenerNombreCliente(idCliente);
    }

    public String getNombreEspecialista(int idEspecialista) {
        return visitaDAO.obtenerNombreEspecialista(idEspecialista);
    }

    public List<String> getMedicamentosPorVisita(int idVisita) {
        return visitaDAO.obtenerMedicamentosPorVisita(idVisita);
    }
}
