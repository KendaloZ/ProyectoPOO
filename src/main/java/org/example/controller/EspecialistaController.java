package org.example.controller;

import org.example.dao.EspecialistaDAO;
import org.example.model.Especialista;

import java.util.List;

public class EspecialistaController {
    private EspecialistaDAO especialistaDAO = new EspecialistaDAO();

    public void insertarEspecialista(Especialista itemEspecialista){
        especialistaDAO.insertarEspecialista(itemEspecialista);
    }

    public void actualizarEspecialista(Especialista itemEspecialista){
        especialistaDAO.actualizarEspecialista(itemEspecialista);
    }

    public void eliminarEspecialista(int id){
        especialistaDAO.eliminarEspecialista(id);
    }

    public List<Especialista> obtenerEspecialistas(){
        return especialistaDAO.obtenerEspecialistas();
    }

    public Especialista obtenerEspecialistaXID(int id){
        return especialistaDAO.obtenerEspecialistaXID(id);
    }

    public Especialista obtenerEspecialistaXCedula(String cedula){
        return especialistaDAO.obtenerEspecialistaXCedula(cedula);
    }

    public Especialista obtenerEspecialistaXNombre(String nombre){
        return especialistaDAO.obtenerEspecialistaXNombre(nombre);
    }
}
