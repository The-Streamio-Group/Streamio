package dominio.negocios.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Perfil {
    private final UUID perfilID;
    private String nick;
    private int idade;
    private List<ReprodutoraConteudo> conteudosFavoritos;
    private List<ReprodutoraConteudo> historico;

    public Perfil(String nick, int idade) {
        this.perfilID = UUID.randomUUID();
        this.nick = nick;
        this.idade = idade;
        this.conteudosFavoritos = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public UUID getPerfilID() {
        return perfilID;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<ReprodutoraConteudo> getConteudosFavoritos() {
        return conteudosFavoritos;
    }

    public void setConteudosFavoritos(List<ReprodutoraConteudo> conteudosFavoritos) {
        this.conteudosFavoritos = conteudosFavoritos;
    }

    public List<ReprodutoraConteudo> getHistorico() {
        return historico;
    }

    public void setHistorico(List<ReprodutoraConteudo> historico) {
        this.historico = historico;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "nick='" + nick + '\'' +
                ", idade=" + idade +
                '}';
    }
}
