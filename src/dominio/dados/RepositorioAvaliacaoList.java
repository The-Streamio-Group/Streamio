package dominio.dados;

import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.negocios.beans.Assinante;
import dominio.negocios.beans.Avaliacao;

import java.util.ArrayList;

public class RepositorioAvaliacaoList implements IRepositorioGeneric<Avaliacao>{

    /*
     * Classe que contém o repositório de todas as Avaliações
     * e seus respectivos CRUDs.
     */

    private ArrayList<Avaliacao> avaliacoesList;
    private static RepositorioAvaliacaoList instance;

    public RepositorioAvaliacaoList() {
        this.avaliacoesList = new ArrayList<>();
    }

    public static IRepositorioGeneric<Avaliacao> getInstance() {
        if(instance == null) {
            instance = new RepositorioAvaliacaoList();
        }
        return (IRepositorioGeneric<Avaliacao>) instance;
    }

    //CREATE
    @Override
    public void cadastrar(Avaliacao avaliacao){
        this.avaliacoesList.add(avaliacao);

    }

    //Cadastrando a partir de dados
    public void cadastrar(int nota, Assinante assinante){
        Avaliacao nova = new Avaliacao(nota, assinante);
        this.cadastrar(nova);

     }

    //READ
    @Override
    public Avaliacao procurar(String avaliacaoID) throws ElementoNaoExisteException {
        for (Avaliacao avaliacao : this.avaliacoesList) {
            if(avaliacao.getAvaliacaoID().equals(avaliacaoID)){
                return avaliacao;
            }
        }
        throw new ElementoNaoExisteException();
    }

    //Método que pergunta para o repositório se existe a avaliação
    @Override
    public boolean existe(String avaliacaoID){
        for (Avaliacao avaliacao : this.avaliacoesList) {
            if(avaliacao.getAvaliacaoID().equals(avaliacaoID)){
                return true;
            }
        }
        return false;
    }

    //DELETE
    @Override
    public void remover(String avaliacaoID) throws ElementoNaoExisteException {
        Avaliacao avaliacaoRemovida = procurar(avaliacaoID);
        if(avaliacaoRemovida == null){throw new ElementoNaoExisteException();}
        this.avaliacoesList.remove(avaliacaoRemovida);
    }

    //UPTADE
    @Override
    public void atualizar(Avaliacao antigo, Avaliacao novo) throws ElementoNaoExisteException {
        if(novo == null){throw new ElementoNaoExisteException();}

        boolean antigoE = existe(antigo.getAvaliacaoID());
        if(antigoE){
            int indice = avaliacoesList.indexOf(antigo);
            this.avaliacoesList.set(indice, novo);

        }
    }

    @Override
    public int procurarIndice(String avaliacaoID) throws ElementoNaoExisteException {
        for(int i = 0; i < this.avaliacoesList.size(); i++){
            if(avaliacoesList.get(i).getAvaliacaoID().equals(avaliacaoID)){
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }

    @Override
    public int totalUsuarios(){

        return this.avaliacoesList.size();
    }

    @Override
    public String dadosString(String avaliacaoID) throws ElementoNaoExisteException {
        Avaliacao impresso = this.procurar(avaliacaoID);
        return impresso.toString();
    }
}