/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernprogrammentwurf.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aviva
 */
public class DBConnectionBuilder 
{   
    // config class
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String NAME = "lernprogramm";
    private static final String USER = "root";
    private static final String PWD  = "";
    
    private Connection connection = null;
    
    public DBConnectionBuilder()
    {   
        try
        {
            String connString = "jdbc:mysql://" + HOST + ":" + PORT + "/" + NAME 
            + "?user=" + USER + "&password=" + PWD; 
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connString);
        } 
        catch (ClassNotFoundException e)
        {
            System.out.println("Driver not found");
        }
        catch (SQLException e)
        {
            System.out.println("Connection refused");
        }
        
    }
    
    public void close()
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("SQL-Connection Error!!");
        }
        
    }

    public Connection getConnection() 
    {
        return connection;
    }

    public void setConnection(Connection connection) 
    {
        this.connection = connection;
    }
    
    
    
}
