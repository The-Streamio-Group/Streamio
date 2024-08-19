package dominio.dados;

import dominio.dados.interfaces.IRepositorioReprodutoraConteudo;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.ReprodutoraConteudo;

import java.util.ArrayList;
import java.util.UUID;

public class RepositorioReproducaoConteudoList implements IRepositorioReprodutoraConteudo {

    private final ArrayList<ReprodutoraConteudo> repositorio;

    private static RepositorioReproducaoConteudoList instance;

    private RepositorioReproducaoConteudoList() {
        this.repositorio = new ArrayList<>();
    }

    public static IRepositorioReprodutoraConteudo getInstance() {
        if (instance == null) {
            instance = new RepositorioReproducaoConteudoList();
        }
        return instance;
    }

    public void cadastrar(ReprodutoraConteudo reprodutoraConteudo) {
        this.repositorio.add(reprodutoraConteudo);
    }


    @Override
    public ReprodutoraConteudo procurar(UUID id) throws ElementoNaoExisteException {
        for (ReprodutoraConteudo reprodutoraConteudoconteudo : repositorio) {
            if (reprodutoraConteudoconteudo.getReprodutoraConteudoID().equals(id)) {
                return reprodutoraConteudoconteudo;
            }
        }
        throw new ElementoNaoExisteException();
    }

    private int procurarIndice(UUID id) throws ElementoNaoExisteException {
        for (int i = 0; i < this.repositorio.size(); i++) {
            if (repositorio.get(i).getReprodutoraConteudoID().equals(id)) {
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }

    @Override
    public void atualizar(UUID antigoid, ReprodutoraConteudo novo) throws ElementoNullException, ElementoNaoExisteException {
        if (novo == null) {
            throw new ElementoNullException();
        }
        boolean antigoE = existe(novo.getReprodutoraConteudoID());
        if (antigoE) {
            int indice = procurarIndice(antigoid);
            this.repositorio.set(indice, novo);
        } else {
            throw new ElementoNaoExisteException();
        }
    }

    @Override
    public void remover(UUID id) throws ElementoNaoExisteException {
        ReprodutoraConteudo removido = procurar(id);
        if (removido == null) {
            throw new ElementoNaoExisteException();
        }
        this.repositorio.remove(removido);
    }

    @Override
    public boolean existe(UUID id) {
        boolean existe = false;
        for (ReprodutoraConteudo reprodutoraConteudo : repositorio) {
            if (reprodutoraConteudo.getReprodutoraConteudoID().equals(id)) {
                existe = true;
                break;
            }
        }
        return existe;
    }
}
