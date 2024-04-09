package controller;

import entity.Coder;
import entity.Empresa;
import entity.Vacante;
import model.CoderModel;
import model.EmpresaModel;
import model.VacanteModel;


import javax.swing.*;
import java.util.ArrayList;

public class VacanteController {

    public static void create(){
        VacanteModel objVacanteModel = new VacanteModel();
        int idEmpresa = Integer.parseInt(JOptionPane.showInputDialog(getAllCompany() + "Enter the id of the company"));
        String titulo = JOptionPane.showInputDialog("Insert title of the vacant");
        String duracion = JOptionPane.showInputDialog("Insert duration of the vacant");
        String descripcion = JOptionPane.showInputDialog("Enter the descripcion of the vacant");
        String tecnologia = JOptionPane.showInputDialog("Enter the technology of the vacant");
        String estado = JOptionPane.showInputDialog("Enter the status of the vacant");

        Vacante objVacante = new Vacante();
        objVacante.setIdEmpresa(idEmpresa);
        objVacante.setTitulo(titulo);
        objVacante.setDuracion(duracion);
        objVacante.setDescripcion(descripcion);
        objVacante.setTecnologia(tecnologia);
        objVacante.setEstado(estado);


        objVacante = (Vacante) objVacanteModel.insert(objVacante);

    }
    public static String getAllCompany(){
        EmpresaModel objEmpresaModel = new EmpresaModel();
        String listEmpresa = "\n COMPANY LIST \n";
        for (Object ite : objEmpresaModel.findAll()){
            Empresa objEmpresa = (Empresa) ite;
            listEmpresa += objEmpresa.toString() +"\n";
        }
        return listEmpresa;
    }
    public static String getAll(){
        VacanteModel objVacanteModel = new VacanteModel();
        String listVacante = "\n VACANTES LIST \n";
        for (Object ite : objVacanteModel.findAll()){
            Vacante objEmpresa = (Vacante) ite;
            listVacante += objEmpresa.toString() +"\n";
        }
        return listVacante;
    }
    public static void getAllString(){
        JOptionPane.showMessageDialog(null, getAll());
    }

    public static void update(){
        VacanteModel objVacanteModel = new VacanteModel();
        String listVacante = getAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listVacante + "\n Enter the id of the coder to edit"));
        Vacante objVacante = objVacanteModel.findById(idUpdate);
        if (objVacante == null) {
            JOptionPane.showMessageDialog(null, "Vacante not found");
        } else {


            String titulo = JOptionPane.showInputDialog("Insert title of the vacante", objVacante.getTitulo());
            String tecnologia = JOptionPane.showInputDialog("Insert tecnologia del coder", objVacante.getTecnologia());
            String duracion = JOptionPane.showInputDialog("Enter the duracion", objVacante.getDuracion());
            int idEmpresa = Integer.parseInt(JOptionPane.showInputDialog("Enter the id empresa", objVacante.getIdEmpresa()));
            String estado = JOptionPane.showInputDialog("Enter estado", objVacante.getEstado());
            String descripcion = JOptionPane.showInputDialog("Enter description", objVacante.getDescripcion());




            objVacante.setTitulo(titulo);
            objVacante.setTecnologia(tecnologia);
            objVacante.setDuracion(duracion);
            objVacante.setDescripcion(descripcion);
            objVacante.setIdEmpresa(idEmpresa);
            objVacante.setEstado(estado);


            objVacante.setId(idUpdate);
            objVacanteModel.update(objVacante);
        }
    }
    public static void delete(){
        VacanteModel objVacanteModel = new VacanteModel();
        String listVacante = getAll();
        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listVacante +  "\n  Enter the ID of the coder to delete"));

        Vacante objVacante = objVacanteModel.findById(idDeleted);
        int confirm = 1;
        if(objVacante == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the coder? \n");
            if (confirm == 0){
                objVacante.setId(idDeleted);
                objVacanteModel.delete(objVacante);
            }
        }
    }
    public static void findCoderByTechnology(){
        VacanteModel objVacanteModel = new VacanteModel();

        String id = JOptionPane.showInputDialog(null, "Enter the name of the technology");
        ArrayList<Vacante> objVacante = objVacanteModel.findByTechnology(id);
        String listVacante = getAll();

        if(objVacante == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else{
            JOptionPane.showMessageDialog(null, "Coder found\n" + listVacante);
        }

    }
    public static void findCoderByTitulo(){
        VacanteModel objVacanteModel = new VacanteModel();

        String id = JOptionPane.showInputDialog(null, "Enter the name of the technology");
        ArrayList<Vacante> objVacante = objVacanteModel.findByTitulo(id);
        String listVacante = getAll();

        if(objVacante == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else{
            JOptionPane.showMessageDialog(null, "Coder found\n" + listVacante);
        }

    }
}
