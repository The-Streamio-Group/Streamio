package dominio.negocios;

import dominio.dados.RepositorioAssinaturaList;
import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.*;
import dominio.negocios.beans.Assinatura;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

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

    public void cadastrarAssinatura(UUID id, String numeroCartao) throws ElementoNullException, ElementoNaoExisteException {
        if (numeroCartao != null) {
            if (repositorio.existe(this.repositorio.procurar(id).getAssinaturaID())) {
                Assinatura assinatura = this.repositorio.procurar(id);
                assinatura.setNumeroCartao(numeroCartao);
            } else {
                throw new ElementoNullException();
            }
        }
    }

    //DELETE
    public void removerAssinatura(UUID id) throws ElementoNaoExisteException {
        Assinatura removido = this.repositorio.procurar(id);
        if (removido != null) {
            this.repositorio.remover(id);
        }
    }

    //READ
    public Assinatura procurarAssinatura(UUID id) throws ElementoNaoExisteException {
        return this.repositorio.procurar(id);
    }


    public boolean existeAssinatura(UUID id) {
        return this.repositorio.existe(id);
    }

    //UPDATE
    public void atualizarAssinatura(UUID antigoid, Assinatura novo) throws ElementoNullException, MesmoElementoException, ElementoJaExisteException, ElementoNaoExisteException {
        if (this.repositorio.procurar(antigoid).equals(novo)) {
            if (!this.repositorio.existe(novo.getAssinaturaID())) {
                this.repositorio.atualizar(antigoid, novo);
            } else {
                throw new ElementoJaExisteException();
            }

        } else {
            throw new MesmoElementoException();
        }
    }

    public void renovarAssinatura(UUID id) throws ElementoNaoExisteException, AssinaturaNaoExpiradaException {
        if (verificarAssinatura(id)) {
            Assinatura renovar = this.repositorio.procurar(id);
            renovar.setStatusPagamento(true);
        }

    }

    public boolean verificarAssinatura(UUID id) throws ElementoNaoExisteException {
        Assinatura v = this.repositorio.procurar(id);
        if(v.estaExpirada()){
            v.setStatusPagamento(false); //setar como false o status do pagamento
        }
        return v.estaExpirada();
    }


}
