package org.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.example.misc.Conexion;
import org.example.model.Visita;

public class VisitaDAO {
    public boolean insertarVisita(Visita visita) {
        String sql = "INSERT INTO grupokm_visita (fecha, hora_entrada, hora_salida, diagnostico, id_cliente, id_especialista) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            boolean var5;
            try (
                    Connection con = Conexion.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql);
            ) {
                ps.setDate(1, Date.valueOf(visita.getFecha()));
                ps.setTimestamp(2, Timestamp.valueOf(visita.getHoraEntrada()));
                ps.setTimestamp(3, Timestamp.valueOf(visita.getHoraSalida()));
                ps.setString(4, visita.getDiagnostico());
                ps.setInt(5, visita.getIdCliente());
                ps.setInt(6, visita.getIdEspecialista());
                var5 = ps.executeUpdate() > 0;
            }

            return var5;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Visita obtenerVisitaPorId(int id) {
        String sql = "SELECT * FROM grupokm_visita WHERE id = ?";

        try {
            Visita var6;
            try (Connection con = Conexion.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        return null;
                    }

                    var6 = new Visita(rs.getInt("id"), rs.getDate("fecha").toLocalDate(), rs.getTimestamp("hora_entrada").toLocalDateTime(), rs.getTimestamp("hora_salida").toLocalDateTime(), rs.getString("diagnostico"), rs.getInt("id_cliente"), rs.getInt("id_especialista"));
                }
            }

            return var6;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Visita> obtenerTodasLasVisitas() {
        List<Visita> lista = new ArrayList();
        String sql = "SELECT * FROM grupokm_visita";

        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while(rs.next()) {
                lista.add(new Visita(rs.getInt("id"), rs.getDate("fecha").toLocalDate(), rs.getTimestamp("hora_entrada").toLocalDateTime(), rs.getTimestamp("hora_salida").toLocalDateTime(), rs.getString("diagnostico"), rs.getInt("id_cliente"), rs.getInt("id_especialista")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean actualizarVisita(Visita visita) {
        String sql = "UPDATE grupokm_visita SET fecha = ?, hora_entrada = ?, hora_salida = ?, diagnostico = ?, id_cliente = ?, id_especialista = ? WHERE id = ?";

        try {
            boolean var5;
            try (
                    Connection con = Conexion.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql);
            ) {
                ps.setDate(1, Date.valueOf(visita.getFecha()));
                ps.setTimestamp(2, Timestamp.valueOf(visita.getHoraEntrada()));
                ps.setTimestamp(3, Timestamp.valueOf(visita.getHoraSalida()));
                ps.setString(4, visita.getDiagnostico());
                ps.setInt(5, visita.getIdCliente());
                ps.setInt(6, visita.getIdEspecialista());
                ps.setInt(7, visita.getId());
                var5 = ps.executeUpdate() > 0;
            }

            return var5;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarVisita(int id) {
        String sql = "DELETE FROM grupokm_visita WHERE id = ?";

        try {
            boolean var5;
            try (
                    Connection con = Conexion.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql);
            ) {
                ps.setInt(1, id);
                var5 = ps.executeUpdate() > 0;
            }

            return var5;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String obtenerNombreCliente(int idCliente) {
        String sql = "SELECT nombre_completo FROM grupokm_cliente WHERE id = ?";

        try {
            String var6;
            try (Connection con = Conexion.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idCliente);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        return "Desconocido";
                    }

                    var6 = rs.getString("nombre_completo");
                }
            }

            return var6;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Desconocido";
        }
    }

    public String obtenerNombreEspecialista(int idEspecialista) {
        String sql = "SELECT nombre_completo FROM grupokm_especialista WHERE id = ?";

        try {
            String var6;
            try (Connection con = Conexion.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idEspecialista);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        return "Desconocido";
                    }

                    var6 = rs.getString("nombre_completo");
                }
            }

            return var6;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Desconocido";
        }
    }

    public List<String> obtenerMedicamentosPorVisita(int idVisita) {
        List<String> medicamentos = new ArrayList();
        String sql = "    SELECT m.nombre\n    FROM grupokm_visita_medicamento vm\n    JOIN grupokm_medicamento m ON vm.id_medicamento = m.id\n    WHERE vm.id_visita = ?\n";

        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, idVisita);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                medicamentos.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicamentos;
    }
}