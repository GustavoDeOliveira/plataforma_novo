/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author iapereira
 */
public class ConexaoPostgreSQL {
    private String host;
    private String port;
    private String username;
    private String dbname;
    private String password;

    public ConexaoPostgreSQL() {
        this.host = "localhost";
        this.port = "5432";
        this.username = "postgres";
        this.dbname = "plataforma-de-musica";
        this.password = "postgres";
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://" + this.host + ":"+this.port+"/" + this.dbname;
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, username, password);
    }

}
