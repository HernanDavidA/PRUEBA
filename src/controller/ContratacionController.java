package controller;

import entity.Contratacion;
import entity.Vacante;
import model.CoderModel;
import model.ContratacionModel;
import model.EmpresaModel;
import model.VacanteModel;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;

public class ContratacionController {




   public static void update(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listContratacion = getAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listContratacion + "\n Enter the id of the coder to edit"));
        Contratacion objContratacion = objContratacionModel.findById(idUpdate);
        if (objContratacion == null) {
            JOptionPane.showMessageDialog(null, "Vacante not found");
        } else {


            int vacante = Integer.parseInt(JOptionPane.showInputDialog("vacante", objContratacion.getIdVacante()));
            int coder = Integer.parseInt(JOptionPane.showInputDialog(" coder", objContratacion.getIdCoder()));
            String estado = JOptionPane.showInputDialog("estado", objContratacion.getEstado());
            Date fecha = Date.valueOf(JOptionPane.showInputDialog("Enter the fecha", objContratacion.getFecha_aplicacion()));
            Double salario = Double.parseDouble(JOptionPane.showInputDialog("Enter estado", objContratacion.getSalario()));


            objContratacion.setIdVacante(vacante);
            objContratacion.setIdCoder(coder);
            objContratacion.setEstado(estado);
            objContratacion.setFecha_aplicacion(fecha);
            objContratacion.setSalario(salario);


            objContratacion.setId(idUpdate);
            objContratacionModel.update(objContratacion);
        }
    }
    public static String getAll(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listEmpresa = "\n Contratacion LIST \n";
        for (Object ite : objContratacionModel.findAll()){
            Contratacion objEmpresa = (Contratacion) ite;
            listEmpresa += objEmpresa.toString() +"\n";
        }
        return listEmpresa;
    }

    public static String getActive(){

        ContratacionModel objContratacionModel = new ContratacionModel();
        String activo = "activo";


        ArrayList<Vacante> objContrato = objContratacionModel.findByActive(activo);
        String listVacantes = getAll();

        return listVacantes;

    }

}
