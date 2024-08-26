package dominio.negocios.beans;

import java.util.Objects;
import java.util.UUID;

public class Avaliacao {
    private final UUID avaliacaoID;
    private int nota;
    private final Perfil perfil;

    public Avaliacao(int nota, Perfil perfil) {
        this.avaliacaoID = UUID.randomUUID();
        this.perfil = perfil;
        this.nota = nota;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public UUID getAvaliacaoID() {
        return avaliacaoID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avaliacao avaliacao)) return false;
        return Objects.equals(avaliacaoID, avaliacao.avaliacaoID);
    }

    @Override
    public String toString() {
        return "nota=" + nota;
    }

}
