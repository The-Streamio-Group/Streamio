package dominio.negocios.beans;

import java.util.ArrayList;
import java.util.List;

public class Produtora extends Usuario {
    private List<Conteudo> produto;


    //Caso a produtora seja nova e não adicione nada por agora
    public Produtora(String nickname, String email, String senha) {
        super(nickname, email, senha);
        this.produto = new ArrayList<>();

    }

    //Caso a produtora já tenha produtos(filmes e séries) catalogados
    public Produtora(String nickname, String email, String senha, List<Conteudo> produto) {
        this(nickname, email, senha);
        this.produto = produto;
    }

    //Getters e setters

    public List<Conteudo> getProduto() {
        return produto;
    }

    public void setProduto(List<Conteudo> produto) {
        this.produto = produto;
    }

    //Métodos Específicos
    public void adicionarProduto(Conteudo novo){
        this.produto.add(novo);
    }

    public void removerProduto(Conteudo c){
        this.produto.remove(c);
    }

    @Override
    public String toString() {
        return "Produtora{" +
                "produto=" + produto +
                '}';
    }
}

