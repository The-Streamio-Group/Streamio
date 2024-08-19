package dominio.negocios.services;

import dominio.exceptions.*;
import dominio.negocios.ControllerConteudo;
import dominio.negocios.ControllerPerfil;
import dominio.negocios.ControllerReprodutoraConteudo;
import dominio.negocios.ControllerUsuario;
import dominio.negocios.beans.*;

import java.util.UUID;

public class GerenciarPerfil {
    private static GerenciarPerfil instance;

    private final ControllerUsuario controleUsuario;
    private final ControllerPerfil controlePerfil;
    private final ControllerReprodutoraConteudo controleReprodutoraConteudo;
    private final ControllerConteudo controleConteudo;

    private GerenciarPerfil() {
        this.controleUsuario = ControllerUsuario.getInstance();
        this.controlePerfil = ControllerPerfil.getInstance();
        this.controleReprodutoraConteudo = ControllerReprodutoraConteudo.getInstance();
        this.controleConteudo = ControllerConteudo.getInstance();
    }

    public static GerenciarPerfil getInstance() {
        if (instance == null) {
            instance = new GerenciarPerfil();
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

    public void assistirConteudoPerfil(UUID idPerfil, ReprodutoraConteudo reprodutoraConteudo) throws ElementoNaoExisteException, ElementoNullException, ElementoJaExisteException {
        Perfil assistir = this.controlePerfil.procurarPerfil(idPerfil);
        assistir.adicionarHistorico(reprodutoraConteudo);
        this.controleReprodutoraConteudo.cadastrarReprodutoraConteudo(reprodutoraConteudo);
        this.controleConteudo.assistirConteudo(reprodutoraConteudo.getConteudo().getConteudoID());
    }


    public void adicionarFavoritoPerfil(UUID idPerfil, ReprodutoraConteudo reprodutoraConteudo) throws NaoViuException, ElementoNaoExisteException {
        Perfil favorito = this.controlePerfil.procurarPerfil(idPerfil);
        if (favorito.possuiHistorico(reprodutoraConteudo)) {
            favorito.adicionarFavoritos(reprodutoraConteudo);
        } else {
            throw new NaoViuException();
        }

    }

    public void removerFavoritoPerfil(UUID idPerfil, ReprodutoraConteudo reprodutoraConteudo) throws ElementoNaoExisteException {
        Perfil favorito = this.controlePerfil.procurarPerfil(idPerfil);
        if (favorito.possuiFavoritos(reprodutoraConteudo)) {
            favorito.removerFavoritos(reprodutoraConteudo);
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
