package dominio.negocios.services;

import dominio.exceptions.AssinaturaNaoExpiradaException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.exceptions.NaoAssinanteException;
import dominio.negocios.ControllerAssinatura;
import dominio.negocios.ControllerUsuario;
import dominio.negocios.beans.Assinante;
import dominio.negocios.beans.Assinatura;
import dominio.negocios.beans.Usuario;

import javax.print.attribute.HashPrintServiceAttributeSet;
import java.util.UUID;

public class GerenciarAssinatura {
    private static GerenciarAssinatura instance;

    private final ControllerAssinatura controleAssinatura;
    private final ControllerUsuario controleUsuario;

    private GerenciarAssinatura() {
        this.controleAssinatura = ControllerAssinatura.getInstance();
        this.controleUsuario = ControllerUsuario.getInstance();
    }

    public static GerenciarAssinatura getInstance() {
        if (instance == null) {
            instance = new GerenciarAssinatura();
        }
        return instance;
    }

    public Assinatura buscarAssinatura(UUID idConta) throws ElementoNaoExisteException, NaoAssinanteException {
        Usuario assinado = this.controleUsuario.procurarUsuario(idConta);
        if(assinado instanceof Assinante){
            return ((Assinante) assinado).getAssinatura();
        }
        else{
            throw new NaoAssinanteException();
        }

    }
    public void renovarAssinaturaUsuario(UUID idConta) throws ElementoNaoExisteException, AssinaturaNaoExpiradaException, NaoAssinanteException {
        Assinatura renovar = this.buscarAssinatura(idConta);
            this.controleAssinatura.renovarAssinatura(renovar.getAssinaturaID());
    }

    public void cancelarAssinaturaUsuario(UUID idConta) throws ElementoNaoExisteException, NaoAssinanteException {
        Assinatura cancelar = this.buscarAssinatura(idConta);
        this.controleAssinatura.removerAssinatura(cancelar.getAssinaturaID());
    }

    public void atualizarCartaoAssinaturaUsuario(UUID idConta, String numCartao) throws ElementoNaoExisteException, NaoAssinanteException, ElementoNullException {
        Assinatura atualizarCartao = this.buscarAssinatura(idConta);
        this.controleAssinatura.cadastrarAssinatura(atualizarCartao);
        this.controleAssinatura.atualizarCartaoAssinatura(atualizarCartao.getAssinaturaID(),numCartao);
    }




}
