package dominio.negocios.beans;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

public class ReprodutoraConteudo {
    private final UUID reprodutoraConteudoID;
    private LocalDate dataAssistido;
    private Duration tempoAssistido;
    private final Conteudo conteudo;

    public ReprodutoraConteudo(Conteudo conteudo, long minutosAssistidos) {
        this.reprodutoraConteudoID = UUID.randomUUID();
        this.conteudo = conteudo;
        this.tempoAssistido = Duration.ofMinutes(minutosAssistidos);
        this.dataAssistido = LocalDate.now();
    }

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


    @Override
    public String toString() {
        return "ReprodutoraConteudo{" +
                "dataAssistido=" + dataAssistido +
                ", tempoAssistido=" + tempoAssistido +
                ", conteudo=" + conteudo +
                '}';
    }
}
