package dominio.dados;

import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoNaoExisteException;
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

    //UPTADE
    @Override
    public void atualizar(Conteudo antigo, Conteudo novo) throws ElementoNaoExisteException {
        if(novo == null){throw new ElementoNaoExisteException();}

        boolean antigoE = existe(antigo.getTitulo());
        if(antigoE){
            int indice = conteudosList.indexOf(antigo);
            this.conteudosList.set(indice, novo);

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

    @Override
    public int totalUsuarios(){

        return this.conteudosList.size();
    }

    @Override
    public String dadosString(String titulo) throws ElementoNaoExisteException {
        Conteudo impresso = this.procurar(titulo);
        return impresso.toString();
    }
}
