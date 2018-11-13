package model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Comentario extends Entidade {
    
    private String conteudo;
    
    private Date data;
    
    private Musica musica;
    
    private Usuario autor;


    
    
    // GETTERS / SETTERS
    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
    
    public String getDataString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return format.format(data);
    }
    
    public void setData(String data) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {
            this.data = format.parse(data);
        } catch (ParseException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
