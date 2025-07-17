package org.example.dao;

import org.example.misc.Conexion;
// importar el modelo medicamento
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoClienteDAO {
    public void asociarMedicamentoCliente(int idCliente, int idMedicamento) {
        String sql = "INSERT INTO grupokm_cliente_medicamento (id_cliente, id_medicamento) VALUES (?, ?)";
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);{
                ps.setInt(1, idCliente);
                ps.setInt(2, idMedicamento);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarMedicamentoCliente(int idCliente, int idMedicamento, int id) {
        String sql = "UPDATE grupokm_cliente_medicamento  SET id_cliente = ?, id_medicamento = ? WHERE id = ?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);{
                ps.setInt(1, idMedicamento);
                ps.setInt(2, idCliente);
                ps.setInt(3, id);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void desasociarMedicamentoCliente(int id){
        String sql = "DELETE FROM grupokm_cliente_medicamento WHERE id = ?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);{
                ps.setInt(1, id);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //En proceso
//    public void List<Medicamento> obtenerMedicamentosClientes(int idCliente){
//        List<Medicamento> medicamentos = new ArrayList<>();
//        String sql = "SELECT * FROM grupokm_cliente_medicamento WHERE id_cliente = ?";
//        try {
//            Connection con = Conexion.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()){
//                Medicamento items = new Medicamento(
//                        rs.getInt("id"),
//                        rs.getInt("id_cliente"),
//                        rs.getInt("id_medicamento")
//                );
//                Medicamento.add(items);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
