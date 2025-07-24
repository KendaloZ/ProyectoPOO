package org.example.controller;

import java.util.List;
import org.example.dao.CitaDAO;
import org.example.model.Cita;

public class CitaController {
    private final CitaDAO citaDAO = new CitaDAO();

    public List<Cita> obtenerCitas() {
        return this.citaDAO.obtenerTodas();
    }

    public boolean insertarCita(Cita cita) {
        return this.citaDAO.insertarCita(cita);
    }

    public Cita obtenerPorId(int id) {
        return this.citaDAO.obtenerCitaPorId(id);
    }

    public boolean actualizarCita(Cita cita) {
        return this.citaDAO.editarCita(cita);
    }

    public boolean eliminarCita(int id) {
        return this.citaDAO.eliminarCita(id);
    }
}
