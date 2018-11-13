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
import model.*;


public class musicaDAO {
   
    public List<Musica> listar(int usuarioId) throws SQLException, ClassNotFoundException  {
        List<Musica> musicas = new ArrayList();
        Connection connection = new ConexaoPostgreSQL().getConnection();
        String sql = "select m.* from musica m "
                + "inner join musica_usuario mu on mu.usuario_id = ? and m.id = mu.musica_id";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuarioId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Musica m = mapear(rs);
            musicas.add(m);
        }
        rs.close();
        statement.close();
        connection.close();
        return musicas;
    }
    
   public List listar() throws SQLException, ClassNotFoundException  {
        List<Foto> fotos = new ArrayList();
        Connection connection = new ConexaoPostgreSQL().getConnection();
        String sql = "select * from muaica";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Foto f = new Foto();
            f.setCod(rs.getInt("cod"));
            f.setImagem(rs.getBytes("imagem"));
            f.setLegenda(rs.getString("legenda"));
            f.setGaleria(new GaleriaDAO().findById(rs.getInt("cod_galeria")));
            fotos.add(f);
        }
        rs.close();
        statement.close();
        connection.close();
        return fotos;
    }
   
   
   public Foto get(int cod) throws SQLException, ClassNotFoundException {
         Foto foto = new Foto();
        Connection connection = new ConexaoPostgreSQL().getConnection();
        String sql = "select * from foto where cod = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cod);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            foto.setCod(rs.getInt("cod"));
            foto.setImagem(rs.getBytes("imagem"));
            foto.setLegenda(rs.getString("legenda"));
            foto.setGaleria(new GaleriaDAO().findById(rs.getInt("cod_galeria")));
        }
        rs.close();
        statement.close();
        connection.close();
        return foto;
       
       
       
   }
    
    
    public void excluir(int cod) throws SQLException, ClassNotFoundException {
        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "delete from foto where cod = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cod);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
    }

    
    
    public void adicionar(Foto foto) throws SQLException, ClassNotFoundException {

        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "insert into foto(imagem, legenda, cod_galeria) values (?, ?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBytes(1, foto.getImagem());
                statement.setString(2, foto.getLegenda());
                statement.setInt(3, foto.getGaleria().getCod());
                statement.executeUpdate();
                statement.close();
                connection.close();
            }
        }
    }

    private Musica mapear(ResultSet rs) throws SQLException {
        Musica m = new Musica();
        m.setId(rs.getInt("id"));
        m.setNome(rs.getString("nome"));
        m.setDescricao(rs.getString("descricao"));
        m.setArquivo(rs.getString("arquivo"));
        return m;
    }
}
