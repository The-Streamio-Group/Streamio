package dominio.negocios;

import dominio.dados.RepositorioAvaliacaoList;
import dominio.dados.interfaces.IRepositorioAssinatura;
import dominio.dados.interfaces.IRepositorioAvaliacao;
import dominio.exceptions.ElementoJaExisteException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.exceptions.MesmoElementoException;
import dominio.negocios.beans.Avaliacao;
import dominio.negocios.beans.Perfil;

import java.util.List;
import java.util.UUID;

public class ControllerAvaliacao {
    private static ControllerAvaliacao instancia;
    private final IRepositorioAvaliacao repositorio;

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
            if (!existeAvaliacao(a.getAvaliacaoID())) {
                this.repositorio.cadastrar(a);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new ElementoNullException();
        }
    }

    public void remover(UUID id) throws ElementoNaoExisteException {
        Avaliacao removido = procurarAvaliacao(id);
        if (removido != null) {
            this.repositorio.remover(id);
        }
    }

    public List<Avaliacao> procurarDono(Perfil dono) {
        return this.repositorio.procurarDono(dono);
    }

    public void atualizarAvaliacao(UUID antigoid, Avaliacao novo) throws ElementoNullException, ElementoJaExisteException, MesmoElementoException, ElementoNaoExisteException {
        //Verifica os parâmetros são iguais
        if (!procurarAvaliacao(antigoid).equals(novo)) {
            //Verifica se existe no repositório
            if (!existeAvaliacao(novo.getAvaliacaoID())) {
                this.repositorio.atualizar(antigoid, novo);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new MesmoElementoException();
        }


    }

    //Classes private
    private boolean existeAvaliacao(UUID id) {
        return this.repositorio.existe(id);
    }

    private Avaliacao procurarAvaliacao(UUID id) throws ElementoNaoExisteException {
        return this.repositorio.procurar(id);
    }


}
