package dominio.negocios;

import dominio.dados.RepositorioAssinaturaList;
import dominio.dados.interfaces.IRepositorioAssinatura;
import dominio.exceptions.*;
import dominio.negocios.beans.Assinante;
import dominio.negocios.beans.Assinatura;

import java.time.LocalDate;
import java.util.UUID;

public class ControllerAssinatura {
    private static ControllerAssinatura instancia;
    private final IRepositorioAssinatura repositorio;

    private ControllerAssinatura() {
        this.repositorio = RepositorioAssinaturaList.getInstance();
    }

    public static ControllerAssinatura getInstance() {
        if (instancia == null) {
            instancia = new ControllerAssinatura();
        }
        return instancia;
    }


    //CREATE
    public void cadastrarAssinatura(Assinatura assinatura) throws ElementoNullException {
        if (assinatura != null) {
            if (!existeAssinatura(assinatura.getAssinaturaID())) {
                this.repositorio.cadastrar(assinatura);
            } else {
                throw new ElementoNullException();
            }
        }
    }


    //DELETE
    public void removerAssinatura(UUID id) throws ElementoNaoExisteException {
        Assinatura removido = procurarAssinatura(id);
        if (removido != null) {
            this.repositorio.remover(id);
        }
    }

    public void cancelarAssinatura(UUID id) throws ElementoNaoExisteException{
            Assinatura removido = procurarAssinatura(id);
            if(removido != null){
                this.repositorio.cancelar(id);
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
        if (procurarAssinatura(antigoid).equals(novo)) {
            if (!existeAssinatura(novo.getAssinaturaID())) {
                this.repositorio.atualizar(antigoid, novo);
            } else {
                throw new ElementoJaExisteException();
            }

        } else {
            throw new MesmoElementoException();
        }
    }

    public void atualizarCartaoAssinatura(UUID antigoid, String numCartao) throws ElementoNaoExisteException {
        Assinatura novoCartao = this.repositorio.procurar(antigoid);
        novoCartao.setNumeroCartao(numCartao);
        novoCartao.setStatusPagamento(true);
        novoCartao.setDataAssinatura(LocalDate.now());
        novoCartao.setDataExpiracao(LocalDate.now().plusDays(30));

    }


    public void renovarAssinatura(UUID id) throws ElementoNaoExisteException, AssinaturaNaoExpiradaException {
        Assinatura renovar = procurarAssinatura(id);
        if (!renovar.isStatusPagamento()) {
            renovar.setStatusPagamento(true);
            renovar.setDataAssinatura(LocalDate.now());
            renovar.setDataExpiracao(LocalDate.now().plusDays(30));
        } else {
            throw new AssinaturaNaoExpiradaException();
        }

    }

    public boolean verificarAssinatura(UUID id) throws ElementoNaoExisteException {
        Assinatura v = procurarAssinatura(id);
        return v.estaExpirada();
        //Se sim, ele retorna true

    }


}
