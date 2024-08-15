package dominio.negocios;

import dominio.dados.RepositorioAssinaturaList;
import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoJaExisteException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.exceptions.MesmoElementoException;
import dominio.negocios.beans.Assinatura;

public class ControllerAssinatura {
    private static ControllerAssinatura instancia;
    private final IRepositorioGeneric<Assinatura> repositorio;

    private ControllerAssinatura() {
        this.repositorio = RepositorioAssinaturaList.getInstance();
    }

    public static ControllerAssinatura getInstance() {
        if (instancia == null) {
            instancia = new ControllerAssinatura();
        }
        return instancia;
    }

    // Controller: receber numeroCartao, adquirir UUID, realizar mudanças

    //CREATE
    public void cadastrarAssinatura(Assinatura assinatura) throws ElementoNullException {
        if (assinatura != null) {
            if (!repositorio.existe(assinatura.getAssinaturaID())) {
                this.repositorio.cadastrar(assinatura);
            } else {
                throw new ElementoNullException();
            }
        }
    }

    //DELETE
    public void removerAssinatura(String numeroCartao) throws ElementoNaoExisteException {
        Assinatura removido = this.repositorio.procurar(numeroCartao);
        if (removido != null) {
            this.repositorio.remover(numeroCartao);
        }
    }

    //READ
    public Assinatura procurarAssinatura(String numeroCartao) throws ElementoNaoExisteException {
        return this.repositorio.procurar(numeroCartao);
    }


    public int procurarIndiceAssinatura(String numeroCartao) throws ElementoNaoExisteException {
        return this.repositorio.procurarIndice(numeroCartao);
    }

    public boolean existeAssinatura(String numeroCartao) {
        return this.repositorio.existe(numeroCartao);
    }

    //UPDATE
    public void atualizarAssinatura(Assinatura antigo, Assinatura novo) throws ElementoNullException, MesmoElementoException, ElementoJaExisteException {
        if (!antigo.equals(novo)) {
            if (!this.repositorio.existe(novo.getNumeroCartao())) {
                this.repositorio.atualizar(antigo, novo);
            } else {
                throw new ElementoJaExisteException();
            }

        } else {
            throw new MesmoElementoException();
        }
    }


}
