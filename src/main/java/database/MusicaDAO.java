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


public class MusicaDAO {
   
    public List<Musica> listarAutoral(int usuarioId) throws SQLException, ClassNotFoundException  {
        List<Musica> musicas = new ArrayList();
        Connection connection = new ConexaoPostgreSQL().getConnection();
        String sql = "select m.* from musica m "
                + "inner join musica_usuario mu on mu.usuario_id = ? and m.id = mu.musica_id";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuarioId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Musica m = _mapear(rs);
            musicas.add(m);
        }
        rs.close();
        statement.close();
        connection.close();
        return musicas;
    }
    
   public List<Musica> listar(int usuarioId) throws SQLException, ClassNotFoundException  {
        List<Musica> musicas = new ArrayList();
        List<Etiqueta> etiquetas = new ArrayList();
        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "SELECT id, nome, peso FROM etiqueta_usuario "
                    + "INNER JOIN etiqueta e ON etiqueta_id = e.id AND usuario_id = ? "
                    + "ORDER BY peso DESC";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, usuarioId);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        Etiqueta e = _mapearEtiqueta(rs);
                        etiquetas.add(e);
                    }
                }
            }

            sql = "SELECT m.id AS m_id, m.nome AS m_nome, m.descricao, m.arquivo, "
                        + "e.id AS e_id, e.nome AS e_nome, em.peso FROM musica m "
                    + "LEFT OUTER JOIN etiqueta_musica em ON em.musica_id = m.id "
                    + "LEFT OUTER JOIN etiqueta e ON em.etiqueta_id = e.id "
                    + "LEFT OUTER JOIN etiqueta_usuario eu ON e.id = eu.etiqueta_id AND eu.usuario_id != ? "
                    + "ORDER BY m.id, em.peso DESC";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, usuarioId);
                try (ResultSet rs = statement.executeQuery()) {
                    musicas = _mapearMusicas(rs);
                }
            }
        }
        
        musicas = _recomendar(musicas, etiquetas);
        
        return musicas;
    }
   
    public void excluir(int cod) throws SQLException, ClassNotFoundException {
        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "delete from musica where cod = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cod);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
    }
 
    public void adicionar(Musica musica) throws SQLException, ClassNotFoundException {
        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "insert into musica (nome, descricao, arquivo) values (?, ?, ?) returning id";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, musica.getNome());
                statement.setString(2, musica.getDescricao());
                statement.setString(3, musica.getArquivo());
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) musica.setId(rs.getInt("id"));
                    else throw new SQLException("Nenhum id gerado ao salvar a música.");
                }
            }
            sql = "insert into musica_usuario (usuario_id, musica_id) values (?, ?)";
            for(Usuario u : musica.getAutores()) {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, u.getId());
                    statement.setInt(2, musica.getId());
                    statement.executeUpdate();
                }
            }
            sql = "insert into etiqueta_musica (etiqueta_id, musica_id, peso) values (?, ?, ?)";
            int peso = 100;
            for(Etiqueta e : musica.getEtiquetas()) {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, e.getId());
                    statement.setInt(2, musica.getId());
                    statement.setInt(3, peso > 10 ? peso-=10 : peso);
                    statement.executeUpdate();
                }
            }
        }
    }

    private Musica _mapear(ResultSet rs) throws SQLException {
        Musica m = new Musica();
        m.setId(rs.getInt("id"));
        m.setNome(rs.getString("nome"));
        m.setDescricao(rs.getString("descricao"));
        m.setArquivo(rs.getString("arquivo"));
        return m;
    }

    private Etiqueta _mapearEtiqueta(ResultSet rs) throws SQLException {
        Etiqueta e = new Etiqueta();
        e.setId(rs.getInt("id"));
        e.setNome(rs.getString("nome"));
        e.setPeso(rs.getInt("peso"));
        return e;
    }

    private List<Musica> _mapearMusicas(ResultSet rs) throws SQLException {
        List<Musica> musicas = new ArrayList<>();
        while(rs.next()) {
            Musica m = new Musica();
            m.setId(rs.getInt("m_id"));
            m.setNome(rs.getString("m_nome"));
            m.setDescricao(rs.getString("descricao"));
            m.setArquivo(rs.getString("arquivo"));
            if (rs.getInt("e_id") != 0) {
                Etiqueta e = new Etiqueta();
                e.setId(rs.getInt("e_id"));
                e.setNome(rs.getString("e_nome"));
                e.setPeso(rs.getInt("peso"));
                e.setMusica(m);
                if (musicas.contains(m)) {
                    musicas.get(musicas.indexOf(m)).getEtiquetas().add(e);
                } else {
                    m.setEtiquetas(new ArrayList<Etiqueta>());
                    m.getEtiquetas().add(e);
                    musicas.add(m);
                }
            } else {
                musicas.add(m);
            }
        }
        return musicas;
    }
    
    private List<Musica> _recomendar(List<Musica> musicas, List<Etiqueta> preferencias) {
        if (preferencias.isEmpty()) return musicas;
        ArrayList<Musica> mlist = new ArrayList<>();
        for (Musica musica : musicas) {
            forPrefs:
            for (Etiqueta preferencia : preferencias) {
                if (musica.getEtiquetas() == null) {
                    mlist.add(musica);
                    break forPrefs;
                }
                for (Etiqueta etiqueta : musica.getEtiquetas()) {
                    if (preferencia.equals(etiqueta)) {
                        mlist.add(musica);
                        break forPrefs;
                    }
                }
            }
        }
        return mlist;
    }
}
