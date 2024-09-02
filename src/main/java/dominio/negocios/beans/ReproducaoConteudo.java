package dominio.negocios.beans;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class ReproducaoConteudo {
    private final UUID reprodutoraConteudoID;
    private LocalDate dataAssistido;
    private Duration tempoAssistido;
    private final Conteudo conteudo;
    private Perfil perfil;

    public ReproducaoConteudo(Conteudo conteudo, long minutosAssistidos, Perfil p) {
        this.reprodutoraConteudoID = UUID.randomUUID();
        this.conteudo = conteudo;
        this.tempoAssistido = Duration.ofMinutes(minutosAssistidos);
        this.dataAssistido = LocalDate.now();
        this.perfil = p;
    }

    //Getters e Setters
    public LocalDate getDataAssistido() {
        return dataAssistido;
    }

    public void setDataAssistido(LocalDate dataAssistido) {
        this.dataAssistido = dataAssistido;
    }

    public Duration getTempoAssistido() {
        return tempoAssistido;
    }

    public void setTempoAssistido(Duration tempoAssistido) {
        this.tempoAssistido = tempoAssistido;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public UUID getReprodutoraConteudoID() {
        return reprodutoraConteudoID;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return conteudo.getTitulo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReproducaoConteudo that)) return false;
        return Objects.equals(getReprodutoraConteudoID(), that.getReprodutoraConteudoID());
    }

}
