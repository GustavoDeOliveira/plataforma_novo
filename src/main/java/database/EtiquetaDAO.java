package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Etiqueta;

public class EtiquetaDAO {
    public List<Etiqueta> salvar(String lista) {
        String[] nomes = lista.split(",");
        List<Etiqueta> etiquetas = new ArrayList<>();
        if (nomes.length < 1) return etiquetas;
        
        try (Connection connection = new ConexaoPostgreSQL().getConnection()) {
            String sql = "select id from etiqueta where nome = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for(String nome : nomes) {
                    statement.setString(1, nome);
                    ResultSet rs = statement.executeQuery();
                    if (!rs.next()) {
                        String query = "INSERT INTO etiqueta (nome) values (?)";
                        PreparedStatement st = connection.prepareStatement(query);
                        st.setString(1, nome);
                        st.execute();
                    }
                    rs.close();
                }
            }
            
            sql = "select * from etiqueta where nome in (";
            for(int i = 1; i < nomes.length; i++)
                sql += "?, ";
                
            sql += "?)";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for(int i = 0; i < nomes.length; i++) {
                    statement.setString(i+1, nomes[i]);
                }
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    etiquetas.add(_mapear(rs));
                }
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return etiquetas;
    }
    
    private Etiqueta _mapear(ResultSet rs) throws SQLException {
        Etiqueta e = new Etiqueta();
        e.setId(rs.getInt("id"));
        e.setNome(rs.getString("nome"));
        
        return e;
    }
}
