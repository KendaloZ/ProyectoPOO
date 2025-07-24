package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.misc.Conexion;
import org.example.model.Medicamento;

public class VisitaMedicamentoDAO {
    public void asociarMedicamentoVisita(int idVisita, int idMedicamento) {
        String sql = "INSERT INTO grupokm_visita_medicamento (id_visita, id_medicamento) VALUES (?, ?)";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idVisita);
            ps.setInt(2, idMedicamento);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editarMedicamentoVisita(int idVisita, int idMedicamento, int id) {
        String sql = "UPDATE grupokm_visita_medicamento SET id_visita = ?, id_medicamento = ? WHERE id = ?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idVisita);
            ps.setInt(2, idMedicamento);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void desasociarMedicamentoVisita(int id) {
        String sql = "DELETE FROM grupokm_visita_medicamento WHERE id = ?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Medicamento> obtenerMedicamentosPorVisita(int idVisita) {
        List<Medicamento> medicamentos = new ArrayList();
        String sql = "SELECT m.id, m.nombre FROM grupokm_visita_medicamento vm INNER JOIN grupokm_medicamento m ON m.id = vm.id_medicamento WHERE vm.id_visita = ?";

        try {
            try (
                    Connection con = Conexion.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql);
            ) {
                ps.setInt(1, idVisita);
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    Medicamento medicamento = new Medicamento(rs.getInt("id"), rs.getString("nombre"));
                    medicamentos.add(medicamento);
                }
            }

            return medicamentos;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener medicamentos de la visita", e);
        }
    }
}