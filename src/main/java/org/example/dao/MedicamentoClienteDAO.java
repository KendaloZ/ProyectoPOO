package org.example.dao;

import org.example.misc.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.misc.Conexion;
import org.example.model.Medicamento;


public class MedicamentoClienteDAO {
    public void asociarMedicamentoCliente(int idCliente, int idMedicamento) {
        String sql = "INSERT INTO grupokm_cliente_medicamento (id_cliente, id_medicamento) VALUES (?, ?)";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.setInt(2, idMedicamento);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editarMedicamentoCliente(int idCliente, int idMedicamento, int id) {
        String sql = "UPDATE grupokm_cliente_medicamento  SET id_cliente = ?, id_medicamento = ? WHERE id = ?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMedicamento);
            ps.setInt(2, idCliente);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean desasociarMedicamentoCliente(int idCliente, int idMedicamento) {
        String sql = "DELETE FROM grupokm_cliente_medicamento WHERE id_cliente = ? AND id_medicamento = ?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.setInt(2,idMedicamento);
            return ps.executeUpdate() >0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Medicamento> obtenerMedicamentosClientes(int idCliente) {
        List<Medicamento> medicamentos = new ArrayList();
        String sql = "SELECT c.id_medicamento, m.nombre FROM grupokm_cliente_medicamento c INNER JOIN grupokm_medicamento m ON m.id = c.id_medicamento WHERE c.id_cliente = ?";

        try {
            try (
                    Connection con = Conexion.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql);
            ) {
                ps.setInt(1, idCliente);
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    Medicamento medicamento = new Medicamento(rs.getInt("id_medicamento"), rs.getString("nombre"));
                    medicamentos.add(medicamento);
                }
            }

            return medicamentos;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener medicamentos del cliente", e);
        }
    }
}
