package dominio.negocios;

import dominio.exceptions.*;
import dominio.negocios.beans.*;
import dominio.negocios.services.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

public class SistemaFachada implements ISistemaFachada {
    private final ControllerPerfil controllerPerfil;
    private final ControllerReproducaoConteudo controllerReproducaoConteudo;
    private final ControllerUsuario controllerUsuario;
    private final ServiceAssinatura serviceAssinatura;
    private final ServiceAvaliacao serviceAvaliacao;
    private final ServicePerfil servicePerfil;
    private final ServiceRelatorio serviceRelatorio;
    private final ServiceLogin serviceLogin;
    private final ServiceConteudo serviceConteudo;

    private static SistemaFachada instancia;
    private Usuario usuariologado; //Instância do usuário logado
    private Perfil perfilLogado;  //Instância do Perfil logado

    public SistemaFachada() {
        this.controllerPerfil = ControllerPerfil.getInstance();
        this.controllerReproducaoConteudo = ControllerReproducaoConteudo.getInstance();
        this.serviceAssinatura = ServiceAssinatura.getInstance();
        this.serviceAvaliacao = ServiceAvaliacao.getInstance();
        this.servicePerfil = ServicePerfil.getInstance();
        this.controllerUsuario = ControllerUsuario.getInstance();
        this.serviceRelatorio = ServiceRelatorio.getInstance();
        this.serviceLogin = ServiceLogin.getInstance();
        this.serviceConteudo = ServiceConteudo.getInstance();
    }

    public static SistemaFachada getInstance() {
        if (instancia == null) {
            instancia = new SistemaFachada();
        }
        return instancia;
    }


    public void realizarLogin(String email, String senha) throws ElementoNaoExisteException, UsuarioJaLogadoException, AssinaturaExpiradaException, SenhaErradaException {
        this.usuariologado = this.serviceLogin.realizarLogin(email, senha, usuariologado);
    }

    public void logoff() {
        usuariologado = null;
        perfilLogado = null;
    }


    //Perfil
    public void cadastrarPerfil(Perfil p) throws ElementoNullException, MaxPerfilException, NaoAssinanteException, ElementoNaoExisteException {
        this.servicePerfil.adicionarPerfilAssinante(p, usuariologado.getUsuarioID());
    }

    public void removerPerfil(UUID idPerfil) throws ElementoNaoExisteException, NaoAssinanteException {
        this.servicePerfil.removerPerfilAssinante(idPerfil, usuariologado.getUsuarioID());
    }

    public void trocarPerfil(String nickname) throws NaoAssinanteException, ElementoNaoExisteException {
        perfilLogado = this.servicePerfil.trocarPerfil(nickname, usuariologado, perfilLogado);
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
        this.serviceConteudo.adicionarConteudo(adicionado, usuariologado);
    }

    public void removerConteudo(UUID id) throws ElementoNaoExisteException, NaoProdutoraException {
        this.serviceConteudo.removerConteudo(id, usuariologado);
    }

    public void atualizarConteudo(UUID antigoid, Conteudo novo) throws ElementoJaExisteException, ElementoNullException, MesmoElementoException, NaoProdutoraException, ElementoNaoExisteException {
        this.serviceConteudo.atualizarConteudo(antigoid, novo, usuariologado);
    }

    public String gerarRelatorioProdutora() throws NaoProdutoraException, ElementoNaoExisteException {
        return this.serviceRelatorio.GerarRelatorio(usuariologado);
    }


    //Parte dos Assinantes
    public void assistirConteudo(Conteudo conteudo, long minutosAssistidos) throws ElementoNaoExisteException, NaoAssinanteException, ElementoNullException, ElementoJaExisteException {
        this.serviceConteudo.assistirConteudoPerfil(perfilLogado.getPerfilID(), conteudo, minutosAssistidos, usuariologado);
    }

    public void adicionarFavorito(Conteudo conteudo) throws ElementoNaoExisteException, NaoAssinanteException, NaoViuException {
        this.serviceConteudo.adicionarFavoritoPerfil(perfilLogado.getPerfilID(), conteudo);
    }


    //Avaliacao
    public void realizarAvaliacao(Avaliacao a, UUID reproducaoConteudoID) throws ElementoNaoExisteException, ElementoNullException, ElementoJaExisteException, NaoAssinanteException, TempoInsuficienteException {
        this.serviceAvaliacao.realizarAvaliacao(a, reproducaoConteudoID, perfilLogado);
    }

    public void atualizarAvaliacao(UUID idAvaliacao, Avaliacao avaliacao) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException {
        this.serviceAvaliacao.atualizarAvaliacao(idAvaliacao, avaliacao);
    }


    //Assinatura
    public void realizarAssinatura(UUID idConta, String numCartao) throws ElementoNaoExisteException, NaoAssinanteException, ElementoNullException {
        this.serviceAssinatura.atualizarCartaoAssinaturaUsuario(idConta, numCartao);
    }


    public void renovarAssinatura(UUID idConta) throws ElementoNaoExisteException, AssinaturaNaoExpiradaException, NaoAssinanteException {
        this.serviceAssinatura.renovarAssinaturaUsuario(idConta);
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

