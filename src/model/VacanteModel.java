package model;

import database.CRUD;
import database.ConfigDB;


import entity.Coder;
import entity.Empresa;
import entity.Vacante;


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;


        try {
            String sql = "INSERT INTO vacante (id_empresa, titulo, duracion, descripcion, tecnologia, estado) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepared.setInt(1, objVacante.getIdEmpresa());
            objPrepared.setString(2, objVacante.getTitulo());
            objPrepared.setString(3, objVacante.getDuracion());
            objPrepared.setString(4, objVacante.getDescripcion());
            objPrepared.setString(5, objVacante.getTecnologia());
            objPrepared.setString(6, objVacante.getEstado());

            objPrepared.executeUpdate();

            JOptionPane.showMessageDialog(null, "VACANTE INSERTION WAS SUCCESSFULLY");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return objVacante;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listCoder = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM vacante;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepared.executeQuery();

            while(objResult.next()){
                Vacante objVacante = new Vacante();
                objVacante.setIdEmpresa(objResult.getInt("id_empresa"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));
                objVacante.setId(objResult.getInt("id"));
                objVacante.setEstado(objResult.getString("estado"));
                listCoder.add(objVacante);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> "+ e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return listCoder;
    }

    @Override
    public boolean update(Object obj) {
        boolean isUpdated = false;

        try (Connection objConnection = ConfigDB.openConnection()) {
            Vacante objVacante = (Vacante) obj;
            String sql = "UPDATE vacante SET  titulo = ?, duracion = ?, descripcion = ?, tecnologia = ?, estado = ? WHERE id = ?;";

            try (PreparedStatement objPrepared = objConnection.prepareStatement(sql)) {

                objPrepared.setString(1, objVacante.getTitulo());
                objPrepared.setString(2, objVacante.getDuracion());
                objPrepared.setString(3, objVacante.getDescripcion());
                objPrepared.setString(4, objVacante.getTecnologia());
                objPrepared.setString(5, objVacante.getEstado());
                objPrepared.setInt(6, objVacante.getId());

                int totalRowAffected = objPrepared.executeUpdate();

                if (totalRowAffected > 0) {
                    isUpdated = true;
                    JOptionPane.showMessageDialog(null, "Vacante " + objVacante.getId() + " actualizada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ninguna vacante con ID " + objVacante.getId() + ".");

                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la vacante: " + e.getMessage());
            System.out.println(e.getMessage());
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Vacante objVacante = (Vacante) obj;
        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {

            String sql = "DELETE FROM vacante WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objVacante.getId());

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

    public static ArrayList<Vacante> findByTechnology(String requirement ) {
        ArrayList<Vacante> listVacante = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM vacante WHERE tecnologia LIKE ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, "%" + requirement + "%");

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
    public static ArrayList<Vacante> findByTitulo(String requirement ) {
        ArrayList<Vacante> listVacante = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM vacante WHERE titulo LIKE ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, "%" + requirement + "%");

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
    public static Vacante findById(int id) {

        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM vacante WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, id);
            ResultSet objResult = objPrepared.executeQuery();


            if (objResult.next()) {
                objVacante = new Vacante();
                objPrepared.setInt(1, objVacante.getIdEmpresa());
                objPrepared.setString(2,objVacante.getTitulo());
                objPrepared.setString(3, objVacante.getDuracion());
                objPrepared.setString(4, objVacante.getDescripcion());
                objPrepared.setString(5, objVacante.getTecnologia());
                objPrepared.setInt(6, objVacante.getId());

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return objVacante;
    }

}
