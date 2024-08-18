package dominio.negocios.beans;


import java.util.ArrayList;
import java.util.List;


public class Assinante extends Usuario {
    private List<ReprodutoraConteudo> conteudosFavoritos;
    private List<ReprodutoraConteudo> historico;
    private final Assinatura assinatura;
    private List<Perfil> perfis;

    public Assinante(String nickname, String email, String senha, Assinatura assinatura) {
        super(nickname, email, senha);
        this.conteudosFavoritos = new ArrayList<>();
        this.historico = new ArrayList<>();
        this.assinatura = assinatura;
    }

    //Getters e setters

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
