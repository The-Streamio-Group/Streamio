package dominio.negocios;

import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.dados.RepositorioAvaliacaoList;
import dominio.exceptions.ElementoJaExisteException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.exceptions.MesmoElementoException;
import dominio.negocios.beans.Avaliacao;

public class CadastroAvaliacao {
    private static CadastroAvaliacao instancia;
    private final IRepositorioGeneric<Avaliacao> repositorio;

    private CadastroAvaliacao() {
        this.repositorio = RepositorioAvaliacaoList.getInstance();
    }

    public static CadastroAvaliacao getInstance() {
        if(instancia == null) {
            instancia = new CadastroAvaliacao();
        }
        return instancia;
    }

    public void cadastrarAvaliacao(Avaliacao a) throws ElementoJaExisteException, ElementoNullException {
        if(a != null) {
            if(!repositorio.existe(a.getAvaliacaoID())) {
                this.repositorio.cadastrar(a);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new ElementoNullException();
        }
    }

    public void removerAvaliacao(String avaliacaoID) throws ElementoNaoExisteException {
        Avaliacao removido = this.repositorio.procurar(avaliacaoID);
        if(removido != null) {
            this.repositorio.remover(avaliacaoID);
        }
    }

    public Avaliacao procurarAvaliacao(String avaliacaoID) throws ElementoNaoExisteException {
        return this.repositorio.procurar(avaliacaoID);
    }

    public int procurarIndiceAvaliacao(String avaliacaoID) throws ElementoNaoExisteException {
        return this.repositorio.procurarIndice(avaliacaoID);
    }

    public boolean existeAvaliacao(String avaliacaoID) {
        return this.repositorio.existe(avaliacaoID);
    }

    public void atualizarAvaliacao(Avaliacao antigo, Avaliacao novo) throws ElementoNullException, ElementoJaExisteException, MesmoElementoException {
        if(!antigo.equals(novo)) {
            if(!this.repositorio.existe(novo.getAvaliacaoID())){
                this.repositorio.atualizar(antigo, novo);
            }
            throw new ElementoJaExisteException();
        } else {
            throw new MesmoElementoException();
        }
    }




}
