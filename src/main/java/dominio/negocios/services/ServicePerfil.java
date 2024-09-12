package dominio.negocios.services;

import dominio.exceptions.*;
import dominio.negocios.ControllerPerfil;
import dominio.negocios.ControllerReproducaoConteudo;
import dominio.negocios.ControllerUsuario;
import dominio.negocios.beans.*;

import java.util.UUID;

public class ServicePerfil {
    private static ServicePerfil instance;

    private final ControllerUsuario controleUsuario;
    private final ControllerPerfil controlePerfil;
    private final ControllerReproducaoConteudo controllerReproducaoConteudo;

    private ServicePerfil() {
        this.controleUsuario = ControllerUsuario.getInstance();
        this.controlePerfil = ControllerPerfil.getInstance();
        this.controllerReproducaoConteudo = ControllerReproducaoConteudo.getInstance();
    }

    public static ServicePerfil getInstance() {
        if (instance == null) {
            instance = new ServicePerfil();
        }
        return instance;
    }

    public Assinante buscarAssinante(UUID idAssinante) throws ElementoNaoExisteException, NaoAssinanteException {
        Usuario assinado = this.controleUsuario.procurarUsuario(idAssinante);
        if (assinado instanceof Assinante) {
            return ((Assinante) assinado);
        } else {
            throw new NaoAssinanteException();
        }
    }

    public void adicionarPerfilAssinante(Perfil p, UUID idConta) throws ElementoNullException, NaoAssinanteException, ElementoNaoExisteException, MaxPerfilException {
        Assinante adicionar = this.buscarAssinante(idConta);
        if (adicionar.tamanhoPerfis() <= 3) {
            adicionar.adicionarPerfil(p);
            this.controlePerfil.cadastrarPerfil(p);
        } else {
            throw new MaxPerfilException();
        }

    }

    public void removerPerfilAssinante(UUID idPerfil, UUID idConta) throws NaoAssinanteException, ElementoNaoExisteException {
        Assinante remover = this.buscarAssinante(idConta);
        Perfil removido = this.controlePerfil.procurarPerfil(idPerfil);
        if (remover.possuiPerfil(removido)) {
            this.controlePerfil.removerPerfil(idPerfil);
            remover.getPerfis().remove(removido);
        } else {
            throw new ElementoNaoExisteException();
        }

    }

    public void removerReproducaoConteudo(UUID idReproducaoConteudo) throws ElementoNaoExisteException {
        this.controllerReproducaoConteudo.removerReprodutoraConteudo(idReproducaoConteudo);
    }

    public void atualizarPerfil(UUID antigoId, Perfil p) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException {
        this.controlePerfil.atualizarPerfil(antigoId,p);
    }

    public void mudarNomePerfil(UUID idPerfil, String novoNome) throws MesmoNomeException, ElementoNaoExisteException {
        this.controlePerfil.mudarNomePerfil(idPerfil,novoNome);
    }

    public void mudarFaixaEtaria(UUID idPerfil, int novaIdade) throws ElementoNaoExisteException, MesmoElementoException {
        this.controlePerfil.mudarFaixaEtaria(idPerfil,novaIdade);
    }

    public Perfil procurarPerfil(UUID idPerfil) throws ElementoNaoExisteException {
        return this.controlePerfil.procurarPerfil(idPerfil);
    }

    public Perfil procurarPerfilPorNome(String nome) throws ElementoNaoExisteException {
        return this.controlePerfil.procurarPerfilPorNick(nome);
    }


    public Perfil trocarPerfil(String nickname, Usuario usuarioLogado, Perfil perfilLogado) throws NaoAssinanteException, ElementoNaoExisteException {
        if (usuarioLogado instanceof Assinante) {
            if (this.existePerfilAssinante((Assinante) usuarioLogado, nickname)) {
                return this.controlePerfil.procurarPerfilPorNick(nickname);
            }
        }
        throw new NaoAssinanteException();
    }


    public boolean existePerfilAssinante(Assinante assinante, String nickname) throws ElementoNaoExisteException, NaoAssinanteException {
        boolean resultado = false;

        Perfil perfilLog = this.controlePerfil.procurarPerfilPorNick(nickname);
        Assinante assinantePerfil = this.buscarAssinante(assinante.getUsuarioID());
        if (assinantePerfil.possuiPerfil(perfilLog)) {
            resultado = true;
        }
        return resultado;
    }

}
