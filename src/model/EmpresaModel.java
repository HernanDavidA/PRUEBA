package model;

import database.ConfigDB;
import entity.Empresa;
import entity.Vacante;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel {

    public List<Empresa> findAll() {
        List<Empresa> listEmpresa = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM empresa;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Empresa objEmpresa = new Empresa();

                // Asignar valores desde el ResultSet al objeto Empresa
                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                listEmpresa.add(objEmpresa);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
        } finally {
            ConfigDB.closeConnection(); // Asegúrate de que ConfigDB.closeConnection() cierre correctamente la conexión
        }

        return listEmpresa;
    }
    public static Empresa findById(int id) {

        Connection objConnection = ConfigDB.openConnection();
        Empresa objEmpresa= null;

        try {                                         // ? = query parameter
            String sql = "SELECT * FROM empresa WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, id);
            ResultSet objResult = objPrepared.executeQuery();

            if (objResult.next()) {
                objEmpresa = new Empresa();
                objPrepared.setInt(1, objEmpresa.getId());
                objPrepared.setString(2,objEmpresa.getNombre());
                objPrepared.setString(3, objEmpresa.getSector());
                objPrepared.setString(4, objEmpresa.getUbicacion());
                objPrepared.setString(5, objEmpresa.getContacto());


            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return objEmpresa;
    }
}
