package dominio.dados;

import dominio.dados.interfaces.IRepositorioAvaliacao;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.Perfil;
import dominio.negocios.beans.Avaliacao;
// removido o import de Conteudo

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositorioAvaliacaoList implements IRepositorioAvaliacao {

    /*
     * Classe que contém o repositório de todas as Avaliações
     * e seus respectivos CRUDs.
     */

    private final ArrayList<Avaliacao> avaliacoesList;
    private static RepositorioAvaliacaoList instance;

    public RepositorioAvaliacaoList() {
        this.avaliacoesList = new ArrayList<>();
    }

    //Instância do repositório
    public static RepositorioAvaliacaoList getInstance() {
        if (instance == null) {
            instance = new RepositorioAvaliacaoList();
        }
        return instance;
    }

    //CREATE
    @Override
    public void cadastrar(Avaliacao avaliacao) {
        this.avaliacoesList.add(avaliacao);
    }

    //Cadastrando a partir de dados
    public void cadastrar(int nota, Perfil perfil) {
        Avaliacao nova = new Avaliacao(nota, perfil);
        this.cadastrar(nova);
    }

    //READ
    @Override
    public Avaliacao procurar(UUID avaliacaoID) throws ElementoNaoExisteException {
        for (Avaliacao avaliacao : this.avaliacoesList) {
            if (avaliacao.getAvaliacaoID().equals(avaliacaoID)) {
                return avaliacao;
            }
        }
        throw new ElementoNaoExisteException();
    }

    public List<Avaliacao> procurarDono(Perfil dono) {
        List<Avaliacao> resultado = new ArrayList<>();
        for (Avaliacao avaliacao : avaliacoesList) {
            if (avaliacao.getPerfil().equals(dono)) {
                resultado.add(avaliacao);
            }
        }

        return resultado;
    }

    //Método que pergunta para o repositório se existe a avaliação
    @Override
    public boolean existe(UUID avaliacaoID) {
        for (Avaliacao avaliacao : this.avaliacoesList) {
            if (avaliacao.getAvaliacaoID().equals(avaliacaoID)) {
                return true;
            }
        }
        return false;
    }

    //DELETE
    @Override
    public void remover(UUID avaliacaoID) throws ElementoNaoExisteException {
        Avaliacao avaliacaoRemovida = procurar(avaliacaoID);
        if (avaliacaoRemovida == null) {
            throw new ElementoNaoExisteException();
        }
        this.avaliacoesList.remove(avaliacaoRemovida);
    }

    //UPDATE
    @Override
    public void atualizar(UUID antigoid, Avaliacao novo) throws ElementoNullException, ElementoNaoExisteException {
        if (novo == null) {
            throw new ElementoNullException();
        }
        boolean antigoE = existe(antigoid);
        if (antigoE) {
            int indice = procurarIndice(antigoid);
            this.avaliacoesList.set(indice, novo);
        } else {
            throw new ElementoNaoExisteException();
        }
    }


    private int procurarIndice(UUID avaliacaoID) throws ElementoNaoExisteException {
        for (int i = 0; i < this.avaliacoesList.size(); i++) {
            if (avaliacoesList.get(i).getAvaliacaoID().equals(avaliacaoID)) {
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }
}