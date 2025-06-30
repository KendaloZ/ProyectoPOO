package org.example.controller;

import org.example.dao.EspecialistaDAO;
import org.example.model.Especialista;

import java.util.List;

public class EspecialistaCrontroller {
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

    public Especialista obtenerEspecialista(int id){
        return especialistaDAO.obtenerEspecialista(id);
    }
    
}
