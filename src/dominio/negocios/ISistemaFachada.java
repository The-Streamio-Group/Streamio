package dominio.negocios;

import dominio.exceptions.*;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Usuario;

public interface ISistemaFachada {

    void realizarLogin(String email, String senha) throws ElementoNaoExisteException, UsuarioJaLogadoException, AssinaturaExpiradaException, SenhaErradaException;

    void logoff();

    void cadastrarUsuario(Usuario u) throws ElementoJaExisteException, ElementoNullException;

    void removerUsuario(String email) throws ElementoNaoExisteException;

    void adicionarConteudo(Conteudo adicionado) throws ElementoNullException, NaoProdutoraException;

    void removerConteudo(String titulo) throws ElementoNaoExisteException, NaoProdutoraException;

    void atualizarConteudo(Conteudo antigo, Conteudo novo) throws ElementoJaExisteException, ElementoNullException, MesmoElementoException, NaoProdutoraException;

    String gerarRelatorio() throws NaoProdutoraException;

    void assistirConteudo(String titulo) throws ElementoNaoExisteException, NaoAssinanteException;

    void adicionarFavorito(String titulo) throws ElementoNaoExisteException, NaoAssinanteException;

    void realizarAvaliacao(int nota, String titulo) throws ElementoNaoExisteException, ElementoNullException, ElementoJaExisteException, NaoAssinanteException;

    void realizarAssinatura(String email, String numCartao) throws ElementoNaoExisteException, ElementoNullException, NaoAssinanteException;

    Usuario getUsuariologado();

    String getUsuarioString(String email) throws ElementoNaoExisteException;

}
