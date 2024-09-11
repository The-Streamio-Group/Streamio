package dominio.negocios;

import dominio.exceptions.*;
import dominio.negocios.beans.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ISistemaFachada {
    void realizarLogin(String email, String senha) throws ElementoNaoExisteException, UsuarioJaLogadoException, AssinaturaExpiradaException, SenhaErradaException;

    void logoff();

    void logoffPerfil();

    void cadastrarPerfil(Perfil p) throws ElementoNullException, MaxPerfilException, NaoAssinanteException, ElementoNaoExisteException;

    void removerPerfil(UUID idPerfil) throws ElementoNaoExisteException, NaoAssinanteException;

    void trocarPerfil(String nickname) throws NaoAssinanteException, ElementoNaoExisteException;

    void mudarNomePerfil(UUID idPerfil, String novoNome) throws MesmoNomeException, ElementoNaoExisteException;

    void mudarFaixaEtaria(UUID idPerfil, int novaIdade) throws ElementoNaoExisteException, MesmoElementoException;

    void atualizarPerfil(UUID antigoId, Perfil p) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException;

    Perfil procurarPerfil(UUID idPerfil) throws ElementoNaoExisteException;

    Perfil procurarPerfilPorNome(String nome) throws ElementoNaoExisteException;

    Usuario procurarPorEmail(String email) throws ElementoNaoExisteException;

    void cadastrarUsuario(Usuario u) throws ElementoNullException;

    void removerUsuario(UUID id) throws ElementoNaoExisteException;

    void procurarUsuario(UUID idUsuario) throws ElementoNaoExisteException;

    void alterarNomeUsuario(UUID antigoID, String nome) throws MesmoNomeException, ElementoNaoExisteException;

    void alterarSenhaUsuario(UUID antigoID, String senha) throws MesmoNomeException, ElementoNaoExisteException;

    void adicionarConteudo(Conteudo adicionado) throws ElementoNullException, NaoProdutoraException;

    void removerConteudo(UUID id) throws ElementoNaoExisteException, NaoProdutoraException;

    void atualizarConteudo(UUID antigoid, Conteudo novo) throws ElementoJaExisteException, ElementoNullException, MesmoElementoException, NaoProdutoraException, ElementoNaoExisteException;

    List<Conteudo> procurarConteudoPorTitulo(String titulo);

    void conteudoSelecionado(UUID idConteudo) throws ElementoNaoExisteException;

    String gerarRelatorioProdutora() throws NaoProdutoraException, ElementoNaoExisteException;


    void assistirConteudo(Conteudo Conteudo, long minutosAssistidos) throws ElementoNaoExisteException, NaoAssinanteException, ElementoNullException, ElementoJaExisteException;

    void assistirConteudo(ReproducaoConteudo reproducaoConteudo) throws ElementoNaoExisteException, NaoAssinanteException, ElementoNullException, ElementoJaExisteException;

    void adicionarFavorito(Conteudo Conteudo) throws ElementoNaoExisteException, NaoAssinanteException, NaoViuException;

    void removerFavorito(Conteudo conteudo) throws ElementoNaoExisteException;

    void realizarAvaliacao(Avaliacao a, UUID conteudoID) throws ElementoNaoExisteException, ElementoNullException, ElementoJaExisteException, NaoAssinanteException, TempoInsuficienteException;

    void atualizarAvaliacao(UUID idAvaliacao, Avaliacao avaliacao) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException;


    void realizarAssinatura(UUID idConta, String numCartao) throws ElementoNaoExisteException, NaoAssinanteException, ElementoNullException;

    void renovarAssinatura(UUID idConta) throws ElementoNaoExisteException, AssinaturaNaoExpiradaException, NaoAssinanteException;

    void cancelarAssinatura(UUID idConta) throws NaoAssinanteException, ElementoNaoExisteException;

    void atualizarDataReprodutora(UUID idReprodutora, LocalDate data) throws ElementoNaoExisteException, MesmoElementoException;

    void atualizarTempoAssistido(UUID idReprodutora, Duration duracao) throws ElementoNaoExisteException, MesmoElementoException;

    ReproducaoConteudo procurarReproducaoConteudo(UUID idReprodutora) throws ElementoNaoExisteException;

    void reproducaoMomento(ReproducaoConteudo reproducaoConteudo) throws ElementoNaoExisteException;

    List<ReproducaoConteudo> filtrarHistorico(Perfil dono);

    void removerReproducaoConteudo(UUID idReproducaoConteudo) throws ElementoNaoExisteException;

    Usuario getUsuariologado();

    Perfil getPerfilLogado();

    Conteudo getConteudoSelecionado();

    ReproducaoConteudo getReproducao();
}
