package model;

import java.util.Objects;

public class EtiquetaMusica {
    
    private Integer musicaId;
    
    private Integer etiquetaId;
    
    private Integer peso;

    public Integer getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(Integer usuarioId) {
        this.musicaId = usuarioId;
    }

    public Integer getEtiquetaId() {
        return etiquetaId;
    }

    public void setEtiquetaId(Integer etiquetaId) {
        this.etiquetaId = etiquetaId;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EtiquetaMusica other = (EtiquetaMusica) obj;
        if (!Objects.equals(this.musicaId, other.musicaId)) {
            return false;
        }
        if (!Objects.equals(this.etiquetaId, other.etiquetaId)) {
            return false;
        }
        return true;
    }
}
