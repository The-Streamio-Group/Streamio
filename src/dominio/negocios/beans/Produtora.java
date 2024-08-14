package dominio.negocios.beans;

import java.util.List;


public class Produtora extends Usuario {
    private List<Conteudo> produto;


    //Caso a produtora seja nova e não adicione nada por agora
    public Produtora(String nickname, String email, String senha) {
        super(nickname,email,senha);

    }

    //Caso a produtora já tenha produtos(filmes e séries) catalogados
    public Produtora(String nickname,String email, String senha, List<Conteudo> produto) {
        this(nickname, email, senha);
        this.produto = produto;
    }

    //Getters e setters


    @Override
    public String toString() {
        return super.toString() + "Produtora{" +
                "produto=" + produto +
                '}';
    }
}
