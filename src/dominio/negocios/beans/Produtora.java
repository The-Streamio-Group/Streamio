package dominio.negocios.beans;

import java.util.ArrayList;
import java.util.List;

public class Produtora {
    private int produtoraID;
    private List<Conteudo> produto = new ArrayList<>();

    //Caso a produtora seja nova e não adicione nada por agora
    public Produtora(int produtoraID) {
        this.produtoraID = produtoraID;
    }

    //Caso a produtora já tenha produtos(filmes e séries) catalogados
    public Produtora(int produtoraID, List<Conteudo> produto) {
        this(produtoraID);
        this.produto = produto;
    }

    public int getProdutoraID() {
        return produtoraID;
    }

    public void setProdutoraID(int produtoraID) {
        this.produtoraID = produtoraID;
    }

    public List<Conteudo> getProduto() {
        return produto;
    }

    public void setProduto(List<Conteudo> produto) {
        this.produto = produto;
    }
}
