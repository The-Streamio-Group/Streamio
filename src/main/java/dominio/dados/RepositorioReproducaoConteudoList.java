package dominio.dados;

import dominio.dados.interfaces.IRepositorioReproducaoConteudo;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.Perfil;
import dominio.negocios.beans.ReproducaoConteudo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositorioReproducaoConteudoList implements IRepositorioReproducaoConteudo, Serializable {

    private final ArrayList<ReproducaoConteudo> repositorio;

    private static RepositorioReproducaoConteudoList instance;

    private RepositorioReproducaoConteudoList() {
        this.repositorio = new ArrayList<>();
    }

    //Instância do repositório
    public static RepositorioReproducaoConteudoList getInstance() {
        if (instance == null) {
            instance = new RepositorioReproducaoConteudoList();
        }
        return instance;
    }

    public void cadastrar(ReproducaoConteudo reproducaoConteudo) {
        this.repositorio.add(reproducaoConteudo);
    }


    @Override
    public ReproducaoConteudo procurar(UUID id) throws ElementoNaoExisteException {
        for (ReproducaoConteudo reproducaoConteudo : repositorio) {
            if (reproducaoConteudo.getReprodutoraConteudoID().equals(id)) {
                return reproducaoConteudo;
            }
        }
        throw new ElementoNaoExisteException();
    }

    public List<ReproducaoConteudo> procurarDono(Perfil dono) {
        List<ReproducaoConteudo> resultado = new ArrayList<>();
        for (ReproducaoConteudo reproducaoConteudo : repositorio) {
            if (reproducaoConteudo.getPerfil().equals(dono)) {
                resultado.add(reproducaoConteudo);
            }
        }

        return resultado;
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
    public void atualizar(UUID antigoid, ReproducaoConteudo novo) throws ElementoNullException, ElementoNaoExisteException {
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
        ReproducaoConteudo removido = procurar(id);
        if (removido == null) {
            throw new ElementoNaoExisteException();
        }
        this.repositorio.remove(removido);
    }

    @Override
    public boolean existe(UUID id) {
        boolean existe = false;
        for (ReproducaoConteudo reproducaoConteudo : repositorio) {
            if (reproducaoConteudo.getReprodutoraConteudoID().equals(id)) {
                existe = true;
                break;
            }
        }
        return existe;
    }
}
