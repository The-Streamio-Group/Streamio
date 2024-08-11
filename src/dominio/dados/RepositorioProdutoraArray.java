package dominio.dados;

import dominio.exceptions.UsuarioNullException;
import dominio.negocios.beans.Produtora;
import dominio.negocios.beans.Conteudo;

// Produtoras que adicionam conteúdos
public class RepositorioProdutoraArray {
    // TODO: Decidir se será feito uma concatenação de IDs entre Produtora e Conteudo ou uso de UUID
    private Produtora[] produtoras;
    private int proxima;

    private static RepositorioProdutoraArray instance;

    private RepositorioProdutoraArray(int tamanho) {
        this.produtoras = new Produtora[tamanho];
        this.proxima = 0;
    }

    public static IRepositorioProdutora getInstance() {
        if(instance == null) {
            instance = new RepositorioProdutoraArray(100); //Tamanho padrão = 100
        }
        return (IRepositorioProdutora) instance;
    }

    // incorporar ProdutoraJaExisteException?
    public void cadastrar(Produtora p) {
        this.produtoras[this.proxima] = p;
        this.proxima = this.proxima + 1;
        if(this.proxima == this.produtoras.length) {
            this.duplicaArrayProdutora();
        }
    }

    public void duplicaArrayProdutora() {
        if(this.produtoras != null && this.produtoras.length > 0) {
            Produtora[] arrayDuplicado = new Produtora[this.produtoras.length * 2];
            for(int i = 0; i < this.produtoras.length; i++) {
                arrayDuplicado[i] = this.produtoras[i];
            }
            this.produtoras  = arrayDuplicado;
        }
    }

    public Produtora procurar(String produtoraID) throws UsuarioNullException {
        int i = this.procurarIndice(produtoraID);
        Produtora resultado = null;
        if(i != this.proxima) {
            resultado = this.produtoras[i];
        } else {
            throw new UsuarioNullException();
        }
        return resultado;
    }

    public int procurarIndice(String produtoraID) {
        int i = 0;
        boolean achou = false;
        while((!achou) && (i < this.proxima)) {
            if(produtoraID.equals(this.produtoras[i].getProdutoraID())) {
                achou = true;
            } else {
                i = i + 1;
            }
        }
        return i;
    }

    public boolean existe(String produtoraID) {
        boolean existe = false;
        int indice = this.procurarIndice(produtoraID);
        if(indice != proxima) {
            existe = true;
        }
        return existe;
    }

    public void remover(String produtoraID) {
        int i = this.procurarIndice(produtoraID);
        if(i != this.proxima) {
            this.produtoras[i] = this.produtoras[this.proxima - 1];
            this.produtoras[this.proxima - 1] = null;
            this.proxima = this.proxima - 1;
        } else {
            //throw new ProdutoraNullException
        }
    }

    // public void adicionarConteudo(Conteudo c)
    // public void adicionarConteudo(
}
