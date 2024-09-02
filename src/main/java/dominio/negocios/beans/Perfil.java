package dominio.negocios.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Perfil {
    private final UUID perfilID;
    private String nick;
    private int idade;
    private List<Conteudo> conteudosFavoritos; //Essa lista só armazena quais são os conteúdos favoritos

    public Perfil(String nick, int idade) {
        this.perfilID = UUID.randomUUID();
        this.nick = nick;
        this.idade = idade;
        this.conteudosFavoritos = new ArrayList<>();
    }

    //Getters e setters
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

    public List<Conteudo> getConteudosFavoritos() {
        return conteudosFavoritos;
    }

    public void setConteudosFavoritos(List<Conteudo> conteudosFavoritos) {
        this.conteudosFavoritos = conteudosFavoritos;
    }

    //Métodos específicos
    public void adicionarFavoritos(Conteudo nova) {
        this.conteudosFavoritos.add(nova);
    }

    public void removerFavoritos(Conteudo r){
        this.conteudosFavoritos.remove(r);
    }

    public boolean possuiFavoritos(Conteudo r){
        return this.conteudosFavoritos.contains(r);
    }

    @Override
    public String toString() {
        return "Nome: " + nick  + " | Idade: " + idade;
    }
}
