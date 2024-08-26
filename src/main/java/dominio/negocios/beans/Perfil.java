package dominio.negocios.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Perfil {
    private final UUID perfilID;
    private String nick;
    private int idade;
    private List<ReproducaoConteudo> conteudosFavoritos;
    private List<ReproducaoConteudo> historico;

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

    public List<ReproducaoConteudo> getConteudosFavoritos() {
        return conteudosFavoritos;
    }

    public void setConteudosFavoritos(List<ReproducaoConteudo> conteudosFavoritos) {
        this.conteudosFavoritos = conteudosFavoritos;
    }

    public List<ReproducaoConteudo> getHistorico() {
        return historico;
    }

    public void setHistorico(List<ReproducaoConteudo> historico) {
        this.historico = historico;
    }

    public void adicionarHistorico(ReproducaoConteudo nova){
        this.historico.add(nova);
    }

    public boolean possuiHistorico(ReproducaoConteudo r){
        return this.historico.contains(r);
    }

    public void removerHistorico(ReproducaoConteudo r){
        this.historico.remove(r);
    }

    public void adicionarFavoritos(ReproducaoConteudo nova) {
        this.conteudosFavoritos.add(nova);
    }

    public void removerFavoritos(ReproducaoConteudo r){
        this.conteudosFavoritos.remove(r);
    }

    public boolean possuiFavoritos(ReproducaoConteudo r){
        return this.historico.contains(r);
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "nick='" + nick + '\'' +
                ", idade=" + idade +
                '}';
    }
}
