package dominio.negocios;

import dominio.dados.RepositorioReproducaoConteudoList;
import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoJaExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.negocios.beans.ReprodutoraConteudo;

public class ControllerReprodutoraConteudo {
    private static ControllerReprodutoraConteudo instancia;
    private final IRepositorioGeneric<ReprodutoraConteudo> repositorio;

    private ControllerReprodutoraConteudo() {
        this.repositorio = RepositorioReproducaoConteudoList.getInstance();
    }

    public static ControllerReprodutoraConteudo getInstance() {
        if (instancia == null) {
            instancia = new ControllerReprodutoraConteudo();
        }
        return instancia;
    }


    public void cadastrarReprodutoraConteudo(ReprodutoraConteudo r) throws ElementoJaExisteException, ElementoNullException {
        if (this.repositorio.existe(r.getReprodutoraConteudoID())) {
            this.repositorio.cadastrar(r);
        }

    }
}
