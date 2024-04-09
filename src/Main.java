import controller.CoderController;
import controller.ContratacionController;
import controller.VacanteController;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;
import entity.Vacante;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        /*
        CoderController.create();
        CoderController.update();
        CoderController.findCoderByClan();
        CoderController.findCoderByTechnology();
        CoderController.findCoderByCohorte();


        VacanteController.create();
        VacanteController.update();
        VacanteController.delete();
        VacanteController.getAllString();
        VacanteController.findCoderByTechnology();
        VacanteController.findCoderByTitulo();



        */


        ContratacionController.getActive();



    }
}