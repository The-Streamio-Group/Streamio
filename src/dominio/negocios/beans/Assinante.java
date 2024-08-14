package dominio.negocios.beans;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Assinante extends Usuario {
    private List<Conteudo> conteudosFavoritos;
    private List<Conteudo> historico;

    public Assinante(String nickname, String email, String senha) {
        super(nickname, email, senha);
        this.conteudosFavoritos = new ArrayList<>();
        this.historico = new ArrayList<>();


    }

    //Getters e setters


    public List<Conteudo> getConteudosFavoritos() {
        return conteudosFavoritos;
    }

    public List<Conteudo> getHistorico() {
        return historico;
    }

    @Override
    public String toString() {

        return super.toString() + "Assinante{" +
                "conteudosFavoritos=" + conteudosFavoritos +
                ", historico=" + historico +
                '}';
    }
}
