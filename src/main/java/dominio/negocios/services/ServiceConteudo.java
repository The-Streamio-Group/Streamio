package dominio.negocios.services;

import dominio.exceptions.*;
import dominio.negocios.ControllerConteudo;
import dominio.negocios.ControllerPerfil;
import dominio.negocios.ControllerReproducaoConteudo;
import dominio.negocios.beans.*;

import java.util.UUID;

public class ServiceConteudo {
    public static ServiceConteudo instance;

    private final ControllerConteudo controleConteudo;
    private final ControllerPerfil controlePerfil;
    private final ControllerReproducaoConteudo controllerReproducaoConteudo;

    private ServiceConteudo() {
        controleConteudo = ControllerConteudo.getInstance();
        controlePerfil = ControllerPerfil.getInstance();
        controllerReproducaoConteudo = ControllerReproducaoConteudo.getInstance();
    }

    public static ServiceConteudo getInstance() {
        if (instance == null) {
            instance = new ServiceConteudo();
        }
        return instance;
    }


    public void adicionarConteudo(Conteudo adicionado, Usuario usuariologado) throws ElementoNullException, NaoProdutoraException {
        if (usuariologado instanceof Produtora) {
            this.controleConteudo.cadastrarConteudo(adicionado);
            ((Produtora) usuariologado).adicionarProduto(adicionado);


        } else {
            throw new NaoProdutoraException();
        }
    }

    public void assistirConteudoPerfil(UUID idPerfil, Conteudo conteudo, long minutosAssistidos, Usuario usuariologado) throws ElementoNaoExisteException, ElementoNullException, ElementoJaExisteException, NaoAssinanteException {
        if (usuariologado instanceof Assinante) {
            Perfil assistir = this.controlePerfil.procurarPerfil(idPerfil);
            this.controllerReproducaoConteudo.cadastrarReprodutoraConteudo(conteudo, minutosAssistidos, assistir);
            this.controleConteudo.assistirConteudo(conteudo.getConteudoID());
        } else {
            throw new NaoAssinanteException();
        }
    }

    public void removerConteudo(UUID id, Usuario usuariologado) throws ElementoNaoExisteException, NaoProdutoraException {
        if (usuariologado instanceof Produtora) {
            Conteudo r = this.controleConteudo.procurarConteudo(id);
            this.controleConteudo.removerConteudo(id);
            ((Produtora) usuariologado).removerProduto(r);
        } else {
            throw new NaoProdutoraException();
        }

    }

    public void adicionarFavoritoPerfil(UUID idPerfil, Conteudo conteudo) throws ElementoNaoExisteException, NaoAssinanteException {
        Perfil favorito = this.controlePerfil.procurarPerfil(idPerfil);
        if (favorito != null) {
            favorito.adicionarFavoritos(conteudo);
        } else {
            throw new NaoAssinanteException();
        }

    }

    public void removerFavoritoPerfil(UUID idPerfil, Conteudo conteudo) throws ElementoNaoExisteException {
        Perfil desfavorito = this.controlePerfil.procurarPerfil(idPerfil);
        if (desfavorito.possuiFavoritos(conteudo)) {
            desfavorito.removerFavoritos(conteudo);
        } else {
            throw new ElementoNaoExisteException();
        }

    }

    public void atualizarConteudo(UUID antigoid, Conteudo novo, Usuario usuariologado) throws ElementoJaExisteException, ElementoNullException, MesmoElementoException, NaoProdutoraException, ElementoNaoExisteException {
        if (usuariologado instanceof Produtora) {
            this.controleConteudo.atualizarConteudo(antigoid, novo);

        } else {
            throw new NaoProdutoraException();
        }
    }
}