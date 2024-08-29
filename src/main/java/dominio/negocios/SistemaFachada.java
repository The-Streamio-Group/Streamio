package dominio.negocios;

import dominio.exceptions.*;
import dominio.negocios.beans.*;
import dominio.negocios.services.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

public class SistemaFachada implements ISistemaFachada {
    private final ControllerAssinatura controllerAssinatura;
    private final ControllerAvaliacao controllerAvaliacao;
    private final ControllerConteudo controllerConteudo;
    private final ControllerPerfil controllerPerfil;
    private final ControllerReproducaoConteudo controllerReproducaoConteudo;
    private final ControllerUsuario controllerUsuario;
    private final ServiceAssinatura serviceAssinatura;
    private final ServiceAvaliacao serviceAvaliacao;
    private final ServicePerfil servicePerfil;
    private final ServiceRelatorio serviceRelatorio;
    private final ServiceLogin serviceLogin;

    private static SistemaFachada instancia;
    private Usuario usuariologado; //Instância do usuário logado
    private Perfil perfilLogado;  //Instância do Perfil logado

    public SistemaFachada() {
        this.controllerAssinatura = ControllerAssinatura.getInstance();
        this.controllerAvaliacao = ControllerAvaliacao.getInstance();
        this.controllerConteudo = ControllerConteudo.getInstance();
        this.controllerPerfil = ControllerPerfil.getInstance();
        this.controllerReproducaoConteudo = ControllerReproducaoConteudo.getInstance();
        this.serviceAssinatura = ServiceAssinatura.getInstance();
        this.serviceAvaliacao = ServiceAvaliacao.getInstance();
        this.servicePerfil = ServicePerfil.getInstance();
        this.controllerUsuario = ControllerUsuario.getInstance();
        this.serviceRelatorio = ServiceRelatorio.getInstance();
        this.serviceLogin = ServiceLogin.getInstance();
    }

    public static SistemaFachada getInstance() {
        if (instancia == null) {
            instancia = new SistemaFachada();
        }
        return instancia;
    }


    public void realizarLogin(String email, String senha) throws ElementoNaoExisteException, UsuarioJaLogadoException, AssinaturaExpiradaException, SenhaErradaException {
        this.serviceLogin.realizarLogin(email, senha);
    }

    public void logoff() {
        this.serviceLogin.logoff();
    }


    //Perfil
    public void cadastrarPerfil(Perfil p) throws ElementoNullException {
        this.controllerPerfil.cadastrarPerfil(p);
    }

    public void removerPerfil(UUID idPerfil) throws ElementoNaoExisteException {
        this.controllerPerfil.removerPerfil(idPerfil);
    }

    public void trocarPerfil(String nickname) throws NaoAssinanteException, ElementoNaoExisteException {
        this.servicePerfil.trocarPerfil(nickname, usuariologado, perfilLogado);
    }

    public void mudarNomePerfil(UUID idPerfil, String novoNome) throws MesmoNomeException, ElementoNaoExisteException {
        this.controllerPerfil.mudarNomePerfil(idPerfil, novoNome);

    }

    public void mudarFaixaEtaria(UUID idPerfil, int novaIdade) throws ElementoNaoExisteException, MesmoElementoException {
        this.controllerPerfil.mudarFaixaEtaria(idPerfil, novaIdade);

    }

    public void atualizarPerfil(UUID antigoId, Perfil p) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException {
        this.controllerPerfil.atualizarPerfil(antigoId, p);
    }

    public Perfil procurarPerfil(UUID idPerfil) throws ElementoNaoExisteException {
        return this.controllerPerfil.procurarPerfil(idPerfil);
    }

    public Perfil procurarPerfilPorNome(String nome) throws ElementoNaoExisteException {
        return this.controllerPerfil.procurarPerfilPorNick(nome);
    }


    //Usuário
    public void cadastrarUsuario(Usuario u) throws ElementoNullException {
        this.controllerUsuario.cadastrarUsuario(u);
    }

    public void removerUsuario(UUID idUsuario) throws ElementoNaoExisteException {
        this.controllerUsuario.removerUsuario(idUsuario);
    }

    public void procurarUsuario(UUID idUsuario) throws ElementoNaoExisteException {
        this.controllerUsuario.procurarUsuario(idUsuario);
    }

    public void alterarNomeUsuario(UUID antigoID, String nome) throws MesmoNomeException, ElementoNaoExisteException {
        this.controllerUsuario.atualizarNomeUsuario(antigoID, nome);
    }

    public void alterarSenhaUsuario(UUID antigoID, String senha) throws MesmoNomeException, ElementoNaoExisteException {
        this.controllerUsuario.alterarSenhaUsuario(antigoID, senha);
    }


    //Parte da Produtora
    public void adicionarConteudo(Conteudo adicionado) throws ElementoNullException, NaoProdutoraException {
        if (usuariologado instanceof Produtora) {
            this.controllerConteudo.cadastrarConteudo(adicionado);
            ((Produtora) usuariologado).adicionarProduto(adicionado);


        } else {
            throw new NaoProdutoraException();
        }
    }

    public void removerConteudo(UUID id) throws ElementoNaoExisteException, NaoProdutoraException {
        if (usuariologado instanceof Produtora) {
            Conteudo r = this.controllerConteudo.procurarConteudo(id);
            this.controllerConteudo.removerConteudo(id);
            ((Produtora) usuariologado).removerProduto(r);
        } else {
            throw new NaoProdutoraException();
        }

    }

    public void atualizarConteudo(UUID antigoid, Conteudo novo) throws ElementoJaExisteException, ElementoNullException, MesmoElementoException, NaoProdutoraException, ElementoNaoExisteException {
        if (usuariologado instanceof Produtora) {
            this.controllerConteudo.atualizarConteudo(antigoid, novo);

        } else {
            throw new NaoProdutoraException();
        }
    }

    public String gerarRelatorioProdutora() throws NaoProdutoraException, ElementoNaoExisteException {
        return this.serviceRelatorio.GerarRelatorio(usuariologado);
    }


    //Parte dos Assinantes
    public void assistirConteudo(ReproducaoConteudo reproducaoConteudo) throws ElementoNaoExisteException, NaoAssinanteException, ElementoNullException, ElementoJaExisteException {
        if (usuariologado instanceof Assinante) {
            this.servicePerfil.assistirConteudoPerfil(perfilLogado.getPerfilID(), reproducaoConteudo);

        } else {
            throw new NaoAssinanteException();
        }

    }

    public void adicionarFavorito(ReproducaoConteudo reproducaoConteudo) throws ElementoNaoExisteException, NaoAssinanteException, NaoViuException {
            this.servicePerfil.adicionarFavoritoPerfil(perfilLogado.getPerfilID(), reproducaoConteudo);
    }


    //Avaliacao
    public void realizarAvaliacao(Avaliacao a, ReproducaoConteudo reproducaoConteudo) throws ElementoNaoExisteException, ElementoNullException, ElementoJaExisteException, NaoAssinanteException, TempoInsuficienteException {
        this.serviceAvaliacao.realizarAvaliacao(a,reproducaoConteudo, perfilLogado);
    }

    public void atualizarAvaliacao(UUID idAvaliacao, Avaliacao avaliacao) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException {
        this.controllerAvaliacao.atualizarAvaliacao(idAvaliacao, avaliacao);
    }


    //Assinatura
    public void realizarAssinatura(UUID idConta, String numCartao) throws ElementoNaoExisteException, NaoAssinanteException, ElementoNullException {
        this.serviceAssinatura.atualizarCartaoAssinaturaUsuario(idConta, numCartao);
    }


    public void atualizarAssinatura(UUID idAssinatura, Assinatura assinatura) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException {
        this.controllerAssinatura.atualizarAssinatura(idAssinatura, assinatura);
    }


    //ReprodutoraConteudo
    public void atualizarDataReprodutora(UUID idReprodutora, LocalDate data) throws ElementoNaoExisteException, MesmoElementoException {
        this.controllerReproducaoConteudo.atualizarDataAssistido(idReprodutora, data);
    }

    public void atualizarTempoAssistido(UUID idReprodutora, Duration duracao) throws ElementoNaoExisteException, MesmoElementoException {
        this.controllerReproducaoConteudo.atualizarTempoAssistido(idReprodutora, duracao);
    }


    public Usuario getUsuariologado() {
        return usuariologado;
    }

    public Perfil getPerfilLogado() {
        return perfilLogado;
    }
}

