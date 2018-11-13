package model;

import java.util.List;


public class Usuario extends Entidade {
    
    private String nome;
    
    private String email;
    
    private String senha;
    
    private List<Musica> publicacoes;
    
    private List<Musica> favoritas;
    
    private List<Musica> denuncias;
    
    private List<Etiqueta> etiquetas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Musica> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Musica> publicacoes) {
        this.publicacoes = publicacoes;
    }

    public List<Musica> getFavoritas() {
        return favoritas;
    }

    public void setFavoritas(List<Musica> favoritas) {
        this.favoritas = favoritas;
    }

    public List<Musica> getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(List<Musica> denuncias) {
        this.denuncias = denuncias;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
