package dominio.dados;

import dominio.exceptions.ProdutoraNullException;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Produtora;

public interface IRepositorioProdutora {
    void cadastrar(Produtora p);

    //void cadastrar(String produtoraID, String email, String senha);

    Produtora procurar(String produtoraID) throws ProdutoraNullException;

    boolean existe(String produtoraID);

    void remover(String produtoraID) throws ProdutoraNullException;

    void adicionarConteudo(Conteudo c);
    void removerConteudo(String conteudoID);

}
