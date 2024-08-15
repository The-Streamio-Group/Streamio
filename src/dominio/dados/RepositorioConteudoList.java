package dominio.dados;

import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.negocios.beans.Conteudo;

import java.util.ArrayList;

public class RepositorioConteudoList implements IRepositorioGeneric<Conteudo> {

    /*
     * Classe que contém o repositório de todos os Conteúdos
     * e seus respectivos CRUDs.
     */

    private ArrayList<Conteudo> conteudosList;

    private static RepositorioConteudoList instance;

    private RepositorioConteudoList() {
        this.conteudosList = new ArrayList<>();
    }

    //Instância do repositório
    public static IRepositorioGeneric<Conteudo> getInstance() {
        if(instance == null) {
            instance = new RepositorioConteudoList();
        }
        return (IRepositorioGeneric<Conteudo>) instance;
    }

    //CREATE
    @Override
    public void cadastrar(Conteudo c)  {
        this.conteudosList.add(c);
    }

    //READ
    @Override
    public Conteudo procurar(String titulo) throws ElementoNaoExisteException {
        for (Conteudo conteudo : conteudosList) {
            if (conteudo.getTitulo().equals(titulo)) {
                return conteudo;
            }
        }
        throw new ElementoNaoExisteException();
    }

    //DELETE
    @Override
    public void remover(String titulo) throws ElementoNaoExisteException {
        Conteudo removido = procurar(titulo);
            if(removido == null) {
                throw new ElementoNaoExisteException();
            } else {
                this.conteudosList.remove(removido);
        }
    }

    //UPDATE
    @Override
    public void atualizar(Conteudo antigo, Conteudo novo) throws ElementoNullException {
        if(novo == null){throw new ElementoNullException();}


        if(existe(antigo.getTitulo())){
            this.conteudosList.set(conteudosList.indexOf(antigo), novo);

        }
    }

    //Método que procura o índice do Conteúdo a partir do título
    @Override
    public int procurarIndice(String titulo) throws ElementoNaoExisteException {
        for(int i = 0; i < this.conteudosList.size(); i++){
            if(conteudosList.get(i).getTitulo().equals(titulo)){
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }

    //Método que pergunta para o repositório se o Conteúdo existe.
    @Override
    public boolean existe(String titulo) {
        boolean existe = false;
        for(Conteudo conteudo : conteudosList) {
            if(conteudo.getTitulo().equals(titulo)) {
                existe = true;
                break;
            }
        }
        return existe;
    }


}
