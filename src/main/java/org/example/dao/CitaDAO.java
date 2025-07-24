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
import org.example.model.Cita;

public class CitaDAO {
    public boolean insertarCita(Cita cita) {
        String sql = "INSERT INTO grupokm_cita (fecha, hora, id_cliente, id_especialista, motivo) VALUES (?, ?, ?, ?, ?)";

        try {
            boolean var5;
            try (
                    Connection con = Conexion.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql);
            ) {
                ps.setDate(1, Date.valueOf(cita.getFecha()));
                ps.setTimestamp(2, Timestamp.valueOf(cita.getHora()));
                ps.setInt(3, cita.getIdCliente());
                ps.setInt(4, cita.getIdEspecialista());
                ps.setString(5, cita.getMotivo());
                var5 = ps.executeUpdate() > 0;
            }

            return var5;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cita obtenerCitaPorId(int id) {
        String sql = "SELECT * FROM grupokm_cita WHERE id = ?";

        try {
            Cita var6;
            try (Connection con = Conexion.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        return null;
                    }

                    var6 = new Cita(rs.getInt("id"), rs.getDate("fecha").toLocalDate(), rs.getTimestamp("hora").toLocalDateTime(), rs.getInt("id_cliente"), rs.getInt("id_especialista"), rs.getString("motivo"));
                }
            }

            return var6;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cita> obtenerTodas() {
        List<Cita> lista = new ArrayList();
        String sql = "SELECT * FROM grupokm_cita";

        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while(rs.next()) {
                lista.add(new Cita(rs.getInt("id"), rs.getDate("fecha").toLocalDate(), rs.getTimestamp("hora").toLocalDateTime(), rs.getInt("id_cliente"), rs.getInt("id_especialista"), rs.getString("motivo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean editarCita(Cita cita) {
        String sql = "UPDATE grupokm_cita SET fecha = ?, hora = ?, id_cliente = ?, id_especialista = ?, motivo = ? WHERE id = ?";

        try {
            boolean var5;
            try (
                    Connection con = Conexion.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql);
            ) {
                ps.setDate(1, Date.valueOf(cita.getFecha()));
                ps.setTimestamp(2, Timestamp.valueOf(cita.getHora()));
                ps.setInt(3, cita.getIdCliente());
                ps.setInt(4, cita.getIdEspecialista());
                ps.setString(5, cita.getMotivo());
                ps.setInt(6, cita.getId());
                var5 = ps.executeUpdate() > 0;
            }

            return var5;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCita(int id) {
        String sql = "DELETE FROM grupokm_cita WHERE id = ?";

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
}