/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author iapereira
 */
public class UsuarioDAO {


    public boolean valida(String usuario, String senha) throws SQLException, ClassNotFoundException {

        
        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "select * from usuario where email = ? and senha = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usuario);
                statement.setString(2, senha);
                ResultSet rs = statement.executeQuery();
                
                if (rs.next()) {
                    rs.close();
                    statement.close();
                    connection.close();
                    return true;
                    
                }
                rs.close();
                statement.close();
                connection.close();
              
            }
        
         
          return false;
        }
    }   
    
    public int carregar(String usuario, String senha) throws SQLException, ClassNotFoundException {

        
        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "select id from usuario where email = ? and senha = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usuario);
                statement.setString(2, senha);
                ResultSet rs = statement.executeQuery();
                
                if (rs.next()) {
                    int id = rs.getInt("id");
                    rs.close();
                    statement.close();
                    connection.close();
                    return id;
                    
                }
                rs.close();
                statement.close();
                connection.close();
              
            }
        
         
          return -1;
        }
    }   
    
}



