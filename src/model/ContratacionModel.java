package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;


import entity.Vacante;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {



    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        Vacante objVacante = (Vacante) obj;
        Coder objCoder = (Coder) obj;

        try {
            String sql = "INSERT INTO contratacion (id_coder, id_vacante, estado, salario) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepared.setInt(1, objCoder.getId());
            objPrepared.setInt(2, objVacante.getId());
            objPrepared.setString(3, objContratacion.getEstado());
            objPrepared.setDouble(4, objContratacion.getSalario());

            objPrepared.executeUpdate();

            ResultSet generatedKeys = objPrepared.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                // Aquí tienes el ID generado automáticamente, puedes utilizarlo como lo necesites
                objContratacion.setId(generatedId);
            }

            JOptionPane.showMessageDialog(null, "CONTRATACION INSERTION WAS SUCCESSFULLY");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return objVacante;
    }
    @Override
    public List<Object> findAll() {
        List<Object> listContrataciones = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM contratacion;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepared.executeQuery();

            while(objResult.next()){
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Contratacion objContratacion = new Contratacion();
                objCoder.setId(objResult.getInt("coder.id"));
                objVacante.setId(objResult.getInt("vacante.id"));
                objContratacion.setFecha_aplicacion(objResult.getDate("fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));
                objVacante.setId(objResult.getInt("id"));
                listContrataciones.add(objContratacion);


            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> "+ e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return listContrataciones;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Contratacion objContratacion = (Contratacion) obj;
        Vacante objVacante = (Vacante) obj;
        Coder objCoder = (Coder) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE contratacion SET id_coder = ?, id_vacante = ?, fecha_aplicacion = ?, estado = ?, salario = ? where id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, objContratacion.getIdCoder());
            objPrepared.setInt(2,objContratacion.getIdVacante());
            objPrepared.setDate(3, objContratacion.getFecha_aplicacion());
            objPrepared.setString(4, objContratacion.getEstado());
            objPrepared.setDouble(5, objContratacion.getSalario());
            objPrepared.setInt(6, objContratacion.getId());

            int totalRowAffected = objPrepared.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated successfully");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Contratacion objContratacion = (Contratacion) obj;
        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {

            String sql = "DELETE FROM contratacion WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objContratacion.getId());

            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0 ){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The update was successfully");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>>" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isDeleted;
    }

    public static Contratacion findById(int id) {

        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM contratacion WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, id);
            ResultSet objResult = objPrepared.executeQuery();


            if (objResult.next()) {
                objContratacion = new Contratacion();
                objPrepared.setInt(1, objContratacion.getIdCoder());
                objPrepared.setInt(2,objContratacion.getIdVacante());
                objPrepared.setDate(3, objContratacion.getFecha_aplicacion());
                objPrepared.setString(4, objContratacion.getEstado());
                objPrepared.setDouble(5, objContratacion.getSalario());
                objPrepared.setInt(6, objContratacion.getId());


            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return objContratacion;
    }

    public static ArrayList<Vacante> findByActive( String activo) {
        ArrayList<Vacante> listVacante = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM vacante WHERE estado = ;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, activo);

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Vacante objVacante = new Vacante();

                objPrepared.setInt(1, objVacante.getIdEmpresa());
                objPrepared.setString(2,objVacante.getTitulo());
                objPrepared.setString(3, objVacante.getDuracion());
                objPrepared.setString(4, objVacante.getDescripcion());
                objPrepared.setString(5, objVacante.getTecnologia());
                objPrepared.setInt(6, objVacante.getId());

                listVacante.add(objVacante);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listVacante;
    }
}
