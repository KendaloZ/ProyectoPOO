package org.example.misc.dao;

import org.example.misc.Conexion;
import org.example.misc.model.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    public boolean insertarCita(Cita cita) {
        String sql = "INSERT INTO grupokm_cita (fecha, hora, idCliente, idEspecialista, motivo) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(cita.getFecha()));
            ps.setTimestamp(2, Timestamp.valueOf(cita.getHora()));
            ps.setInt(3, cita.getIdCliente());
            ps.setInt(4, cita.getIdEspecialista());
            ps.setString(5, cita.getMotivo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cita obtenerCitaPorId(int id) {
        String sql = "SELECT * FROM grupokm_cita WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cita(
                        rs.getInt("id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTimestamp("hora").toLocalDateTime(),
                        rs.getInt("idCliente"),
                        rs.getInt("idEspecialista"),
                        rs.getString("motivo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cita> obtenerTodas() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM grupokm_cita";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Cita(
                        rs.getInt("id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTimestamp("hora").toLocalDateTime(),
                        rs.getInt("idCliente"),
                        rs.getInt("idEspecialista"),
                        rs.getString("motivo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean editarCita(Cita cita) {
        String sql = "UPDATE grupokm_cita SET fecha = ?, hora = ?, idCliente = ?, idEspecialista = ?, motivo = ? WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(cita.getFecha()));
            ps.setTimestamp(2, Timestamp.valueOf(cita.getHora()));
            ps.setInt(3, cita.getIdCliente());
            ps.setInt(4, cita.getIdEspecialista());
            ps.setString(5, cita.getMotivo());
            ps.setInt(6, cita.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCita(int id) {
        String sql = "DELETE FROM grupokm_cita WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
