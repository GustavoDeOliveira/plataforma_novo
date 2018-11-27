package model;

public class Etiqueta extends Entidade {
    
    private String nome;
    
    private Integer peso;
    
    private Musica musica;
    
    private Usuario usuario;

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return nome + (peso > 0 ? '(' + peso.toString() + ')' : "");
    }
}
