package dominio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Relatorio extends Sistema {
    private int id;
    private LocalDate dataGerado;

    public Relatorio(int id, LocalDate dataGerado, ArrayList<Usuario> usuariosDoSistema, ArrayList<Conteudo> conteudosDoSistema, ArrayList<Assinatura> assinaturasDoSistemas) {
        super(usuariosDoSistema, conteudosDoSistema, assinaturasDoSistemas);
        this.id = id;
        this.dataGerado = dataGerado;
    }

    // getters e setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDataGerado() {
        return dataGerado;
    }
    public void setDataGerado(LocalDate dataGerado) {
        this.dataGerado = dataGerado;
    }

    // métodos específicos
    public void imprimirRelatorio() {
    }
}
