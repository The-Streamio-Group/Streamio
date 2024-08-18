package dominio.negocios;

import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.dados.RepositorioAvaliacaoList;
import dominio.exceptions.ElementoJaExisteException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.exceptions.MesmoElementoException;
import dominio.negocios.beans.Avaliacao;

import java.util.UUID;

public class ControllerAvaliacao {
    private static ControllerAvaliacao instancia;
    private final IRepositorioGeneric<Avaliacao> repositorio;

    private ControllerAvaliacao() {
        this.repositorio = RepositorioAvaliacaoList.getInstance();
    }

    public static ControllerAvaliacao getInstance() {
        if (instancia == null) {
            instancia = new ControllerAvaliacao();
        }
        return instancia;
    }

    public void cadastrarAvaliacao(Avaliacao a) throws ElementoJaExisteException, ElementoNullException {
        if (a != null) {
            if (!repositorio.existe(a.getAvaliacaoID())) {
                this.repositorio.cadastrar(a);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new ElementoNullException();
        }
    }

    public void removerAvaliacao(UUID id) throws ElementoNaoExisteException {
        Avaliacao removido = this.repositorio.procurar(id);
        if (removido != null) {
            this.repositorio.remover(id);
        }
    }

    public Avaliacao procurarAvaliacao(UUID id) throws ElementoNaoExisteException {
        return this.repositorio.procurar(id);
    }

    public boolean existeAvaliacao(UUID id) {
        return this.repositorio.existe(id);
    }

    public void atualizarAvaliacao(UUID antigoid, Avaliacao novo) throws ElementoNullException, ElementoJaExisteException, MesmoElementoException, ElementoNaoExisteException {
        //Verifica os parâmetros são iguais
        if (!this.repositorio.procurar(antigoid).equals(novo)) {
            //Verifica se existe no repositório
            if (!this.repositorio.existe(novo.getAvaliacaoID())) {
                this.repositorio.atualizar(antigoid, novo);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new MesmoElementoException();
        }


    }


}
