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
import java.util.ArrayList;
import java.util.List;
import model.Galeria;

/**
 *
 * @author iapereira
 */
public class GaleriaDAO {

    public List<Galeria> listar() throws SQLException, ClassNotFoundException {
        Connection connection = new ConexaoPostgreSQL().getConnection();
        String sql = "select * from galeria";
        PreparedStatement statement = connection.prepareStatement(sql);
        List galerias = new ArrayList();
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Galeria g = new Galeria();
            g.setCod(rs.getInt("cod"));
            g.setDescricao(rs.getString("descricao"));
            g.setTitulo(rs.getString("titulo"));
            
            galerias.add(g);
        }
        statement.close();
        rs.close();
        connection.close();
        return galerias;
    }

    public void excluir(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "delete from galeria where cod = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
    }

    public void adicionar(Galeria galeria) throws SQLException, ClassNotFoundException {

        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "insert into galeria (titulo, descricao) values (?,?);";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, galeria.getTitulo());
                statement.setString(2, galeria.getDescricao());
                
                statement.executeUpdate();
                connection.close();
            }
        }
    }

    public Galeria findById(int id) throws SQLException, ClassNotFoundException {

        Galeria g;
        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "select * from galeria where cod = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                g = new Galeria();
                if (rs.next()) {
                    g.setCod(rs.getInt("cod"));
                    g.setDescricao(rs.getString("descricao"));
                    g.setTitulo(rs.getString("titulo"));
                    
                    rs.close();
                    statement.close();
                    connection.close();
                    
                }
            }
        }
        return g;
    }
    
    
}



