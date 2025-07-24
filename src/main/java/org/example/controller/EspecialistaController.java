package org.example.controller;

import org.example.dao.EspecialistaDAO;
import org.example.model.Especialista;

import java.util.List;

public class EspecialistaController {
    private EspecialistaDAO especialistaDAO = new EspecialistaDAO();

    public boolean insertarEspecialista(Especialista itemEspecialista) {
        return this.especialistaDAO.insertarEspecialista(itemEspecialista);
    }

    public boolean actualizarEspecialista(Especialista itemEspecialista) {
        return this.especialistaDAO.actualizarEspecialista(itemEspecialista);
    }

    public boolean eliminarEspecialista(int id) {
        return this.especialistaDAO.eliminarEspecialista(id);
    }

    public List<Especialista> obtenerEspecialistas() {
        return this.especialistaDAO.obtenerEspecialistas();
    }

    public Especialista obtenerEspecialistaXID(int id) {
        return this.especialistaDAO.obtenerEspecialistaXID(id);
    }

    public Especialista obtenerEspecialistaXCedula(String cedula) {
        return this.especialistaDAO.obtenerEspecialistaXCedula(cedula);
    }

    public Especialista obtenerEspecialistaXNombre(String nombre) {
        return this.especialistaDAO.obtenerEspecialistaXNombre(nombre);
    }
}

