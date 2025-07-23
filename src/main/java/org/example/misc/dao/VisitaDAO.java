package org.example.misc.dao;

import org.example.misc.Conexion;
import org.example.misc.model.Visita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitaDAO {
    public boolean insertarVisita(Visita visita) {
        String sql = "INSERT INTO grupokm_visita (fecha, hora_entrada, hora_salida, diagnostico, id_cliente, id_especialista) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(visita.getFecha()));
            ps.setTimestamp(2, Timestamp.valueOf(visita.getHoraEntrada()));
            ps.setTimestamp(3, Timestamp.valueOf(visita.getHoraSalida()));
            ps.setString(4, visita.getDiagnostico());
            ps.setInt(5, visita.getIdCliente());
            ps.setInt(6, visita.getIdEspecialista());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Visita obtenerVisitaPorId(int id) {
        String sql = "SELECT * FROM grupokm_visita WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Visita(
                        rs.getInt("id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTimestamp("hora_entrada").toLocalDateTime(),
                        rs.getTimestamp("hora_salida").toLocalDateTime(),
                        rs.getString("diagnostico"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_especialista")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Visita> obtenerTodasLasVisitas() {
        List<Visita> lista = new ArrayList<>();
        String sql = "SELECT * FROM grupokm_visita";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Visita(
                        rs.getInt("id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTimestamp("hora_entrada").toLocalDateTime(),
                        rs.getTimestamp("hora_salida").toLocalDateTime(),
                        rs.getString("diagnostico"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_especialista")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizarVisita(Visita visita) {
        String sql = "UPDATE grupokm_visita SET fecha = ?, hora_entrada = ?, hora_salida = ?, diagnostico = ?, id_cliente = ?, id_especialista = ? WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(visita.getFecha()));
            ps.setTimestamp(2, Timestamp.valueOf(visita.getHoraEntrada()));
            ps.setTimestamp(3, Timestamp.valueOf(visita.getHoraSalida()));
            ps.setString(4, visita.getDiagnostico());
            ps.setInt(5, visita.getIdCliente());
            ps.setInt(6, visita.getIdEspecialista());
            ps.setInt(7, visita.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarVisita(int id) {
        String sql = "DELETE FROM grupokm_visita WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String obtenerNombreCliente(int idCliente) {
        String sql = "SELECT nombre FROM grupokm_cliente WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Desconocido";
    }

    public String obtenerNombreEspecialista(int idEspecialista) {
        String sql = "SELECT nombre FROM grupokm_especialista WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEspecialista);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Desconocido";
    }

    public List<String> obtenerMedicamentosPorVisita(int idVisita) {
        List<String> medicamentos = new ArrayList<>();
        String sql = """
        SELECT m.nombre 
        FROM grupokm_visita_medicamento vm
        JOIN grupokm_medicamento m ON vm.id_medicamento = m.id
        WHERE vm.id_visita = ?
    """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVisita);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                medicamentos.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamentos;
    }
}