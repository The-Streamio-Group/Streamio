package dominio.negocios.services;

import dominio.exceptions.*;
import dominio.negocios.*;
import dominio.negocios.beans.*;

import java.util.List;
import java.util.UUID;

public class ServiceAvaliacao {
    private static ServiceAvaliacao instance;

    private final ControllerUsuario controleUsuario;
    private final ControllerPerfil controlePerfil;
    private final ControllerAvaliacao controleAvaliacao;
    private final ControllerConteudo controleConteudo;
    private final ControllerReproducaoConteudo controleReproducaoConteudo;

    private ServiceAvaliacao() {
        this.controleUsuario = ControllerUsuario.getInstance();
        this.controlePerfil = ControllerPerfil.getInstance();
        this.controleAvaliacao = ControllerAvaliacao.getInstance();
        this.controleConteudo = ControllerConteudo.getInstance();
        this.controleReproducaoConteudo = ControllerReproducaoConteudo.getInstance();
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

    public void realizarAvaliacao(Avaliacao a, UUID cId, Perfil p) throws ElementoNullException, ElementoJaExisteException, ElementoNaoExisteException, TempoInsuficienteException, NaoAssinanteException {

        if (p != null) {
            Conteudo avaliado = this.controleConteudo.procurarConteudo(cId);
            List<ReproducaoConteudo> lista = this.controleReproducaoConteudo.filtrarDono(p);
            ReproducaoConteudo achado = null;
            for (ReproducaoConteudo conteudo : lista) {
                if (conteudo.getConteudo().equals(avaliado)) {
                    achado = conteudo;
                }

            }
            if (achado != null) {
                if (achado.getTempoAssistido().compareTo(avaliado.getDuracao().dividedBy(5)) >= 0) {
                    this.controleAvaliacao.cadastrarAvaliacao(a);
                    Conteudo temp = this.controleConteudo.procurarConteudo(avaliado.getConteudoID());
                    temp.adicionarAvalicao(a);
                    temp.atualizarNota();

                } else {
                    throw new TempoInsuficienteException();
                }
            }

        } else {
            throw new NaoAssinanteException();
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

    public List<Avaliacao> procurarDono(Perfil dono) {
        return this.controleAvaliacao.procurarDono(dono);
    }

    public void atualizarAvaliacao(UUID antigoID, Avaliacao novo) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException {
        this.controleAvaliacao.atualizarAvaliacao(antigoID, novo);
    }

}
