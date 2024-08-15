package dominio.negocios.beans;


import java.util.ArrayList;
import java.util.List;


public class Assinante extends Usuario {
    private List<Conteudo> conteudosFavoritos;
    private List<Conteudo> historico;
    private final Assinatura assinatura;

    public Assinante(String nickname, String email, String senha, Assinatura assinatura) {
        super(nickname, email, senha);
        this.conteudosFavoritos = new ArrayList<>();
        this.historico = new ArrayList<>();
        this.assinatura = assinatura;


    }


    //Getters e setters


    public List<Conteudo> getConteudosFavoritos() {
        return conteudosFavoritos;
    }

    public List<Conteudo> getHistorico() {
        return historico;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }


    @Override
    public String toString() {

        return super.toString() + "Assinante{" +
                "conteudosFavoritos=" + conteudosFavoritos +
                ", historico=" + historico +
                '}';
    }
}
