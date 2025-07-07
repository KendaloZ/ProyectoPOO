package org.example.misc.controller;

import org.example.misc.dao.CitaDAO;
import org.example.misc.model.Cita;

import java.util.List;

public class CitaController {
    private final CitaDAO citaDAO = new CitaDAO();

    public List<Cita> obtenerCitas() {
        return citaDAO.obtenerTodas();
    }

    public boolean insertarCita(Cita cita) {
        return citaDAO.insertarCita(cita);
    }

    public Cita obtenerPorId(int id) {
        return citaDAO.obtenerCitaPorId(id);
    }

    public boolean actualizarCita(Cita cita) {
        return citaDAO.editarCita(cita);
    }

    public boolean eliminarCita(int id) {
        return citaDAO.eliminarCita(id);
    }
}
