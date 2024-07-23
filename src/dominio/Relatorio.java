package dominio;
import java.time.LocalDate;
import java.util.HashMap;

public class Relatorio {
    private int id;
    private LocalDate dataGerado;

    /* Gerar um relatório com a quantidade de assinaturas (no futuro, separar por tipo), receita gerada, gênero mais consumido etc*/

    public Relatorio (int id, LocalDate dataGerado) {
        this.id = id;
        this.dataGerado = dataGerado;
    }

    @Override
    public String toString () {
        return "Relatório ID: " + id + "\nData: " + dataGerado;
    }


}

