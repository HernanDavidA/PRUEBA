package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    static Connection objConnection = null;

    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/prueba";
            String user = "root";
            String password = "";

            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully");

        }catch (SQLException e){
            System.out.println("Error >>> " + e.getMessage());

        }catch (ClassNotFoundException e){
            System.out.println("Error >>> " + e.getMessage());
        }
        return objConnection;
    }
    public static void closeConnection(){
        try {
            if(objConnection != null)objConnection.close();
            System.out.println("Disconnected successfully");


        } catch (SQLException e) {
            System.out.println("Error >>>>"+ e.getMessage());
        }

    }
}
