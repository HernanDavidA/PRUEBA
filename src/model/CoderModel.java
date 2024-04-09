package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Coder objCoder =  (Coder) obj;

        try{
            String sql = "INSERT INTO coder (nombre, apellido, identificacion, cohorte, cv, clan) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1, objCoder.getNombre());
            objPrepared.setString(2, objCoder.getApellido());
            objPrepared.setString(3, objCoder.getIdentificacion());
            objPrepared.setInt(4, objCoder.getCohorte());
            objPrepared.setString(5, objCoder.getCv());
            objPrepared.setString(6, objCoder.getClan());


            objPrepared.execute();

            ResultSet objResult = objPrepared.getGeneratedKeys();

            while(objResult.next()){
                objCoder.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "CODER INSERTION WAS SUCCESSFULLY");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objCoder;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listCoder = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM coder;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepared.executeQuery();

            while(objResult.next()){
                Coder objCoder = new Coder();

                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setIdentificacion(objResult.getString("identificacion"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));
                objCoder.setId(objResult.getInt("id"));
                listCoder.add(objCoder);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> "+ e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return listCoder;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Coder objCoder = (Coder) obj;

        boolean isUpdated = false;
        try {
            String sql = "UPDATE coder SET nombre = ?, apellido = ?, identificacion = ?, cohorte = ?, cv = ?, clan = ? where id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objCoder.getNombre());
            objPrepared.setString(2,objCoder.getApellido());
            objPrepared.setString(3, objCoder.getIdentificacion());
            objPrepared.setInt(4, objCoder.getCohorte());
            objPrepared.setString(5, objCoder.getCv());
            objPrepared.setString(6, objCoder.getClan());
            objPrepared.setInt(7, objCoder.getId());


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
        Coder objCoder = (Coder) obj;
        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {

            String sql = "DELETE FROM coder WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objCoder.getId());

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
    public static Coder findById(int id){

        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM coder WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objCoder = new Coder();
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setIdentificacion(objResult.getString("identificacion"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objCoder;

    }

    public static ArrayList<Coder> findByTechnology(String requirement ) {
        ArrayList<Coder> listCoder = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM coder WHERE cv LIKE ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, "%" + requirement + "%");

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Coder objCoder = new Coder();

                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setIdentificacion(objResult.getString("identificacion"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoder.add(objCoder);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listCoder;
    }
    public static ArrayList<Coder> findByCohorte(int requirement) {
        ArrayList<Coder> listCoder = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM coder WHERE cohorte = ?;";


            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1,  requirement );

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Coder objCoder = new Coder();

                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setIdentificacion(objResult.getString("identificacion"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoder.add(objCoder);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listCoder;
    }
    public static ArrayList<Coder> findByClan(String requirement ) {
        ArrayList<Coder> listCoder = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM coder WHERE clan LIKE ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, "%" + requirement + "%");

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Coder objCoder = new Coder();

                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setIdentificacion(objResult.getString("identificacion"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoder.add(objCoder);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listCoder;
    }
}
