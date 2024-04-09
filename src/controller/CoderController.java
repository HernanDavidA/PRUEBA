package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.util.ArrayList;

public class CoderController {

    public static void create(){
        CoderModel objCoderModel = new CoderModel();
        String name = JOptionPane.showInputDialog("Insert name of the coder");
        String lastName = JOptionPane.showInputDialog("Insert last name of the coder");
        String identificacion = JOptionPane.showInputDialog("Enter the identificacion of the coder");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Enter the cohorte of the coder"));
        String cv = JOptionPane.showInputDialog("Enter information to your CV (Technologies that you handle for example)");
        String clan = JOptionPane.showInputDialog("Enter the clan where you belong");

        Coder objCoder = new Coder();

        objCoder.setNombre(name);
        objCoder.setApellido(lastName);
        objCoder.setIdentificacion(identificacion);
        objCoder.setCohorte(cohorte);
        objCoder.setCv(cv);
        objCoder.setClan(clan);

        objCoder = (Coder) objCoderModel.insert(objCoder);

    }
    public static String getAll(){
        CoderModel objCoderModel = new CoderModel();
        String listCoder = "\n CODER LIST \n";
        for (Object ite : objCoderModel.findAll()){
            // Become the Object to coder
            Coder objCoder = (Coder) ite;
            listCoder += objCoder.toString() +"\n";
        }
        return listCoder;
    }
    public static void getAllCodersString(){
        JOptionPane.showMessageDialog(null, getAll());
    }
    public static void update(){
        CoderModel objCoderModel = new CoderModel();
        String listCoder = getAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCoder + "\n Enter the id of the coder to edit"));
        Coder objCoder = objCoderModel.findById(idUpdate);
        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "Coder not found");
        } else {


            String name = JOptionPane.showInputDialog("Insert name of the coder", objCoder.getNombre());
            String lastName = JOptionPane.showInputDialog("Insert last name of the coder", objCoder.getApellido());
            String identificacion = JOptionPane.showInputDialog("Enter the identificacion of the coder", objCoder.getIdentificacion());
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Enter the cohorte of the coder", objCoder.getCohorte()));
            String cv = JOptionPane.showInputDialog("Enter information to your CV (Technologies that you handle for example)", objCoder.getCv());
            String clan = JOptionPane.showInputDialog("Enter the clan where you belong", objCoder.getClan());


            objCoder.setNombre(name);
            objCoder.setApellido(lastName);
            objCoder.setIdentificacion(identificacion);
            objCoder.setCohorte(cohorte);
            objCoder.setCv(cv);
            objCoder.setClan(clan);

            objCoder.setId(idUpdate);
            objCoderModel.update(objCoder);
        }
    }
    public static void delete(){
        CoderModel objCoderModel = new CoderModel();
        String listCoder = getAll();
        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listCoder +  "\n  Enter the ID of the coder to delete"));

        Coder objCoder = objCoderModel.findById(idDeleted);
        int confirm = 1;
        if(objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the coder? \n" + objCoder.getNombre() + " " + objCoder.getApellido());
            if (confirm == 0){
                objCoder.setId(idDeleted);
                objCoderModel.delete(objCoder);
            }
        }
    }
    public static void findCoderByClan(){
        CoderModel objCoderModel = new CoderModel();

        String id = JOptionPane.showInputDialog(null, "Enter the name of the clan");
        ArrayList<Coder> objCoder = objCoderModel.findByClan(id);
        String listPatient = getAll();

        if(objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else{
            JOptionPane.showMessageDialog(null, "Coder found\n" + objCoder);
        }

    }
    public static void findCoderByTechnology(){
        CoderModel objCoderModel = new CoderModel();

        String id = JOptionPane.showInputDialog(null, "Enter the name of the technology");
        ArrayList<Coder> objCoder = objCoderModel.findByTechnology(id);
        String listPatient = getAll();

        if(objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else{
            JOptionPane.showMessageDialog(null, "Coder found\n" + objCoder);
        }

    }
    public static void findCoderByCohorte(){
        CoderModel objCoderModel = new CoderModel();

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of the cohorte"));
        ArrayList<Coder> objCoder = objCoderModel.findByCohorte(id);
        String listPatient = getAll();

        if(objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else{
            JOptionPane.showMessageDialog(null, "Coder found\n" + objCoder);
        }

    }
}
