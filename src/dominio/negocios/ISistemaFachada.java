package dominio.negocios;

import dominio.exceptions.*;
import dominio.negocios.beans.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

public interface ISistemaFachada {
    void realizarLogin(String email, String senha) throws ElementoNaoExisteException, UsuarioJaLogadoException, AssinaturaExpiradaException, SenhaErradaException;

    void logoff();

    void cadastrarPerfil(Perfil p) throws ElementoNullException;

    void removerPerfil(UUID idPerfil) throws ElementoNaoExisteException;
    void trocarPerfil(String nickname) throws NaoAssinanteException, ElementoNaoExisteException;

    void mudarNomePerfil(UUID idPerfil, String novoNome) throws MesmoNomeException, ElementoNaoExisteException;

    void mudarFaixaEtaria(UUID idPerfil, int novaIdade) throws ElementoNaoExisteException, MesmoElementoException;

    void atualizarPerfil(UUID antigoId, Perfil p) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException;

    Perfil procurarPerfil(UUID idPerfil) throws ElementoNaoExisteException;

    Perfil procurarPerfilPorNome(String nome) throws ElementoNaoExisteException;

    void cadastrarUsuario(Usuario u) throws ElementoNullException;

    void removerUsuario(UUID id) throws ElementoNaoExisteException;

    void procurarUsuario(UUID idUsuario) throws ElementoNaoExisteException;

    void alterarNomeUsuario(UUID antigoID, String nome) throws MesmoNomeException, ElementoNaoExisteException;

    void alterarSenhaUsuario(UUID antigoID, String senha) throws MesmoNomeException, ElementoNaoExisteException;

    void adicionarConteudo(Conteudo adicionado) throws ElementoNullException, NaoProdutoraException;

    void removerConteudo(UUID id) throws ElementoNaoExisteException, NaoProdutoraException;

    void atualizarConteudo(UUID antigoid, Conteudo novo) throws ElementoJaExisteException, ElementoNullException, MesmoElementoException, NaoProdutoraException, ElementoNaoExisteException;
    String gerarRelatorioProdutora() throws NaoProdutoraException, ElementoNaoExisteException;


    void assistirConteudo(ReprodutoraConteudo reprodutoraConteudo) throws ElementoNaoExisteException, NaoAssinanteException, ElementoNullException, ElementoJaExisteException;

    void adicionarFavorito(ReprodutoraConteudo reprodutoraConteudo) throws ElementoNaoExisteException, NaoAssinanteException, NaoViuException;

    void realizarAvaliacao(Avaliacao a, ReprodutoraConteudo reprodutoraConteudo) throws ElementoNaoExisteException, ElementoNullException, ElementoJaExisteException, NaoAssinanteException, TempoInsuficienteException;

    void atualizarAvaliacao(UUID idAvaliacao, Avaliacao avaliacao) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException;


    void realizarAssinatura(UUID idConta, String numCartao) throws ElementoNaoExisteException, NaoAssinanteException, ElementoNullException;

    void atualizarAssinatura(UUID idAssinatura, Assinatura assinatura) throws ElementoNullException, MesmoElementoException, ElementoNaoExisteException, ElementoJaExisteException;

    void atualizarDataReprodutora(UUID idReprodutora, LocalDate data) throws ElementoNaoExisteException, MesmoElementoException;

    void atualizarTempoAssistido(UUID idReprodutora, Duration duracao) throws ElementoNaoExisteException, MesmoElementoException;

    Usuario getUsuariologado();

    Perfil getPerfilLogado();
}
