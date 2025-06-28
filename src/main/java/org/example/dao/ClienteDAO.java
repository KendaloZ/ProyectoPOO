package org.example.dao;


import org.example.model.Cliente;
import org.example.misc.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void insertarCliente(Cliente itemCliente) {
        String sql = "INSERT INTO grupokm_cliente (nombre_completo, cedula, correo, telefono, direccion, fecha_nacimiento, genero, padecimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);{
                ps.setString(1, itemCliente.getNombreCompleto());
                ps.setString(2, itemCliente.getCedula());
                ps.setString(3, itemCliente.getCorreo());
                ps.setInt(4, itemCliente.getTelefono());
                ps.setString(5, itemCliente.getDireccion());
                ps.setDate(6, new java.sql.Date(itemCliente.getFechaNacimiento().getTime()));
                ps.setString(7, itemCliente.getGenero());
                ps.setString(8, itemCliente.getPadecimiento());
            }
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void actualizarCliente(Cliente itemCliente) {
        String sql = "UPDATE grupokm_cliente SET nombre_completo = ?, cedula = ?, correo = ?, telfono = ?, direccion = ?, fecha_nacimiento = ?, genero = ?, padecimiento WHERE id = ?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);{
                ps.setString(1, itemCliente.getNombreCompleto());
                ps.setString(2, itemCliente.getCedula());
                ps.setString(3, itemCliente.getCorreo());
                ps.setInt(4, itemCliente.getTelefono());
                ps.setString(5, itemCliente.getDireccion());
                ps.setDate(6, new java.sql.Date(itemCliente.getFechaNacimiento().getTime()));
                ps.setString(7, itemCliente.getGenero());
                ps.setString(8, itemCliente.getPadecimiento());
                ps.setInt(9, itemCliente.getId());
            }
            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void eliminarCliente(int id) {
        String sql = "DELETE FROM grupokm_cliente WHERE id = ?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);{
                ps.setInt(1, id);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  List<Cliente> obtenerclientes() {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM grupokm_cliente";

        try {
            Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente items = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("cedula"),
                        rs.getString("correo"),
                        rs.getInt("telefono"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("genero"),
                        rs.getString("padecimiento")
                );
                clientes.add(items);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente obtenerCliente(int id) {
        String sql = "SELECT * FROM grupokm_cliente WHERE id = ?";
        Cliente cliente = null;

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("cedula"),
                        rs.getString("correo"),
                        rs.getInt("telefono"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("genero"),
                        rs.getString("padecimiento")
                );
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    //Falta agregar un metodo que ingrese medicamentos en el cliente

}
