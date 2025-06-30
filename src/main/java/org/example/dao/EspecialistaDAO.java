package org.example.dao;

import org.example.model.Especialista;
import org.example.misc.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecialistaDAO {

    public void insertarEspecialista(Especialista itemEspecialista) {
        String sql = "INSERT INTO grupokm_especialista (nombre_completo, cedula, correo, telefono, direccion, fecha_nacimiento, genero, especialidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql); {
                ps.setString(1, itemEspecialista.getNombreCompleto());
                ps.setString(2, itemEspecialista.getCedula());
                ps.setString(3, itemEspecialista.getCorreo());
                ps.setInt(4, itemEspecialista.getTelefono());
                ps.setString(5, itemEspecialista.getDireccion());
                ps.setDate(6, new java.sql.Date(itemEspecialista.getFechaNacimiento().getTime()));
                ps.setString(7, itemEspecialista.getGenero());
                ps.setString(8, itemEspecialista.getEspecialidad());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarEspecialista(Especialista itemEspecialista) {
        String sql = "UPDATE grupokm_especialista SET nombre_completo = ?, cedula = ?, correo = ?, telefono = ?, direccion = ?, fecha_nacimiento = ?, genero = ?, especialidad = ? WHERE id = ?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql); {
                ps.setString(1, itemEspecialista.getNombreCompleto());
                ps.setString(2, itemEspecialista.getCedula());
                ps.setString(3, itemEspecialista.getCorreo());
                ps.setInt(4, itemEspecialista.getTelefono());
                ps.setString(5, itemEspecialista.getDireccion());
                ps.setDate(6, new java.sql.Date(itemEspecialista.getFechaNacimiento().getTime()));
                ps.setString(7, itemEspecialista.getGenero());
                ps.setString(8, itemEspecialista.getEspecialidad());
                ps.setInt(9, itemEspecialista.getId());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEspecialista(int id) {
        String sql = "DELETE FROM grupokm_especialista WHERE id = ?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql); {
                ps.setInt(1, id);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Especialista> obtenerEspecialistas() {
        List<Especialista> especialistas = new ArrayList<>();

        String sql = "SELECT * FROM grupokm_especialista";

        try {
            Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Especialista item = new Especialista(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("cedula"),
                        rs.getString("correo"),
                        rs.getInt("telefono"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("genero"),
                        rs.getString("especialidad")
                );
                especialistas.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return especialistas;
    }

    public Especialista obtenerEspecialista(int id) {
        String sql = "SELECT * FROM grupokm_especialista WHERE id = ?";
        Especialista especialista = null;

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                especialista = new Especialista(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("cedula"),
                        rs.getString("correo"),
                        rs.getInt("telefono"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("genero"),
                        rs.getString("especialidad")
                );
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return especialista;
    }
}
