package dominio.negocios.services;

import dominio.exceptions.*;
import dominio.negocios.ControllerConteudo;
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
    private final ControllerConteudo controleConteudo;

    private ServicePerfil() {
        this.controleUsuario = ControllerUsuario.getInstance();
        this.controlePerfil = ControllerPerfil.getInstance();
        this.controllerReproducaoConteudo = ControllerReproducaoConteudo.getInstance();
        this.controleConteudo = ControllerConteudo.getInstance();
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
        if (adicionar.tamanhoPerfis() <= 5) {
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

    public void assistirConteudoPerfil(UUID idPerfil, ReproducaoConteudo reproducaoConteudo) throws ElementoNaoExisteException, ElementoNullException, ElementoJaExisteException {
        Perfil assistir = this.controlePerfil.procurarPerfil(idPerfil);
        assistir.adicionarHistorico(reproducaoConteudo);
        this.controllerReproducaoConteudo.cadastrarReprodutoraConteudo(reproducaoConteudo);
        this.controleConteudo.assistirConteudo(reproducaoConteudo.getConteudo().getConteudoID());
    }


    public void removerReproducaoConteudo(Usuario usuarioLogado, UUID idReproducaoConteudo) throws ElementoNaoExisteException {
        ReproducaoConteudo r = this.controllerReproducaoConteudo.procurarReprodutoraConteudo(idReproducaoConteudo);
        Perfil remover = r.getPerfil();
        remover.removerHistorico(r);
        if (remover.getConteudosFavoritos().contains(r)) {
            remover.removerFavoritos(r);
        }
        //Após remover no Perfil, remove do repositório
        this.controllerReproducaoConteudo.removerReprodutoraConteudo(idReproducaoConteudo);


    }


    public void adicionarFavoritoPerfil(UUID idPerfil, ReproducaoConteudo reproducaoConteudo) throws NaoViuException, ElementoNaoExisteException {
        Perfil favorito = this.controlePerfil.procurarPerfil(idPerfil);
        if (favorito.possuiHistorico(reproducaoConteudo)) {
            favorito.adicionarFavoritos(reproducaoConteudo);
        } else {
            throw new NaoViuException();
        }

    }

    public void removerFavoritoPerfil(UUID idPerfil, ReproducaoConteudo reproducaoConteudo) throws ElementoNaoExisteException {
        Perfil favorito = this.controlePerfil.procurarPerfil(idPerfil);
        if (favorito.possuiFavoritos(reproducaoConteudo)) {
            favorito.removerFavoritos(reproducaoConteudo);
        } else {
            throw new ElementoNaoExisteException();
        }

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
