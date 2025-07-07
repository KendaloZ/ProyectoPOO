package org.example.misc.dao;

import org.example.misc.Conexion;
import org.example.misc.model.Medicamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {
    public boolean insertarMedicamento(Medicamento medicamento) {
        String sql = "INSERT INTO grupokm_medicamento (nombre, disponible) VALUES (?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, medicamento.getNombre());
            ps.setBoolean(2, medicamento.isDisponible());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Medicamento obtenerMedicamentoPorId(int id) {
        String sql = "SELECT * FROM grupokm_medicamento WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Medicamento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getBoolean("disponible")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Medicamento> obtenerTodos() {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM grupokm_medicamento";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Medicamento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getBoolean("disponible")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean editarMedicamento(Medicamento medicamento) {
        String sql = "UPDATE grupokm_medicamento SET nombre = ?, disponible = ? WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, medicamento.getNombre());
            ps.setBoolean(2, medicamento.isDisponible());
            ps.setInt(3, medicamento.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarMedicamento(int id) {
        String sql = "DELETE FROM grupokm_medicamento WHERE id = ?";
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
