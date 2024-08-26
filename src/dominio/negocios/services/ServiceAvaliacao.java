package dominio.negocios.services;

import dominio.exceptions.*;
import dominio.negocios.*;
import dominio.negocios.beans.*;

import java.util.UUID;

public class ServiceAvaliacao {
    private static ServiceAvaliacao instance;

    private final ControllerUsuario controleUsuario;
    private final ControllerPerfil controlePerfil;
    private final ControllerAvaliacao controleAvaliacao;
    private final ControllerConteudo controleConteudo;

    private ServiceAvaliacao() {
        this.controleUsuario = ControllerUsuario.getInstance();
        this.controlePerfil = ControllerPerfil.getInstance();
        this.controleAvaliacao = ControllerAvaliacao.getInstance();
        this.controleConteudo = ControllerConteudo.getInstance();
    }

    public static ServiceAvaliacao getInstance() {
        if (instance == null) {
            instance = new ServiceAvaliacao();
        }
        return instance;
    }

    public Perfil buscaPerfil(UUID idPerfil, UUID idUsuario) throws ElementoNaoExisteException, NaoAssinanteException, PerfilNaoPertencenteException {
        Usuario busca = this.controleUsuario.procurarUsuario(idUsuario);
        if (busca instanceof Assinante) {
            Perfil p = this.controlePerfil.procurarPerfil(idPerfil);
            if (((Assinante) busca).possuiPerfil(p)) {
                return p;
            } else {
                throw new PerfilNaoPertencenteException();
            }
        } else {
            throw new NaoAssinanteException();
        }
    }

    public void realizarAvaliacao(Avaliacao a, Perfil p, ReproducaoConteudo reproducaoConteudo) throws ElementoNullException, ElementoJaExisteException, ElementoNaoExisteException, TempoInsuficienteException {
        if (p.possuiHistorico(reproducaoConteudo)) {
            Conteudo c = reproducaoConteudo.getConteudo();
            if (reproducaoConteudo.getTempoAssistido().compareTo(c.getDuracao().dividedBy(5)) >= 0) {
                this.controleAvaliacao.cadastrarAvaliacao(a);
                Conteudo temp = this.controleConteudo.procurarConteudo(c.getConteudoID());
                temp.adicionarAvalicao(a);

            } else {
                throw new TempoInsuficienteException();
            }
        }

    }

    public void removerAvaliacao(Avaliacao a, Conteudo c, Usuario u) throws ElementoNaoExisteException, NaoAssinanteException {
        if (u instanceof Assinante) {
            if (c.possuiAvaliacao(a)) {
                this.controleAvaliacao.remover(a.getAvaliacaoID());
                c.removerAvalicao(a);
            }
        } else {
            throw new NaoAssinanteException();
        }
    }
}
