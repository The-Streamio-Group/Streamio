package dominio.dados;

import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Usuario;

import java.util.ArrayList;
import java.util.UUID;

public class RepositorioConteudoList implements IRepositorioGeneric<Conteudo> {

    /*
     * Classe que contém o repositório de todos os Conteúdos
     * e seus respectivos CRUDs.
     */
    // não usa mais titulo, usa UUID

    private final ArrayList<Conteudo> conteudosList;

    private static RepositorioConteudoList instance;

    private RepositorioConteudoList() {
        this.conteudosList = new ArrayList<>();
    }

    //Instância do repositório
    public static IRepositorioGeneric<Conteudo> getInstance() {
        if (instance == null) {
            instance = new RepositorioConteudoList();
        }
        return (IRepositorioGeneric<Conteudo>) instance;
    }

    //CREATE
    @Override
    public void cadastrar(Conteudo c) {
        this.conteudosList.add(c);
    }

    //READ
    @Override
    public Conteudo procurar(UUID conteudoID) throws ElementoNaoExisteException {
        for (Conteudo conteudo : conteudosList) {
            if (conteudo.getConteudoID().equals(conteudoID)) {
                return conteudo;
            }
        }
        throw new ElementoNaoExisteException();
    }

    //DELETE
    @Override
    public void remover(UUID conteudoID) throws ElementoNaoExisteException {
        Conteudo removido = procurar(conteudoID);
        if (removido == null) {
            throw new ElementoNaoExisteException();
        } else {
            this.conteudosList.remove(removido);
        }
    }

    //UPDATE
    @Override
    public void atualizar(UUID antigoid, Conteudo novo) throws ElementoNullException, ElementoNaoExisteException {
        if (novo == null) {
            throw new ElementoNullException();
        }
        if (existe(antigoid)) {
            this.conteudosList.set(procurarIndice(antigoid), novo);
        } else {
            throw new ElementoNaoExisteException();
        }
    }

    //Método que procura o índice do Conteúdo a partir do --título--

    private int procurarIndice(UUID conteudoID) throws ElementoNaoExisteException {
        for (int i = 0; i < this.conteudosList.size(); i++) {
            if (conteudosList.get(i).getConteudoID().equals(conteudoID)) {
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }

    //Método que pergunta para o repositório se o Conteúdo existe.
    @Override
    public boolean existe(UUID conteudoID) {
        boolean existe = false;
        for (Conteudo conteudo : conteudosList) {
            if (conteudo.getConteudoID().equals(conteudoID)) {
                existe = true;
                break;
            }
        }
        return existe;
    }
}
