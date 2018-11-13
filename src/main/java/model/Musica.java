package model;

import java.util.List;



public class Musica extends Entidade {
    
    private String nome;
    
    private String descricao;
    
    private List<Comentario> comentarios;
    
    private List<Usuario> autores;
    
    private List<Etiqueta> etiquetas;
    
    private String arquivo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Usuario> getAutores() {
        return autores;
    }

    public void setAutores(List<Usuario> autores) {
        this.autores = autores;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
