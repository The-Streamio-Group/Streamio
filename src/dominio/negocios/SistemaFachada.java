package dominio.negocios;

import dominio.exceptions.*;
import dominio.negocios.beans.*;

import java.util.List;

public class SistemaFachada implements ISistemaFachada {
    private final ControllerAssinatura controllerAssinatura;
    private final ControllerAvaliacao controllerAvaliacao;
    private final ControllerConteudo controllerConteudo;
    private final ControllerUsuario controllerUsuario;

    private static SistemaFachada instancia;
    private Usuario usuariologado; //Instância do usuário logado

    public SistemaFachada() {
        this.controllerUsuario = ControllerUsuario.getInstance();
        this.controllerAssinatura = ControllerAssinatura.getInstance();
        this.controllerAvaliacao = ControllerAvaliacao.getInstance();
        this.controllerConteudo = ControllerConteudo.getInstance();
    }

    public static SistemaFachada getInstance() {
        if (instancia == null) {
            instancia = new SistemaFachada();
        }
        return instancia;
    }

    public void realizarLogin(String email, String senha) throws ElementoNaoExisteException, UsuarioJaLogadoException, AssinaturaExpiradaException, SenhaErradaException {

        //Verificar se o usuário já está logado
        if (usuariologado != null) {
            throw new UsuarioJaLogadoException();
        }

        Usuario userLog = this.controllerUsuario.procurarUsuario(email);


        //Caso a assinatura expire
        if (userLog instanceof Assinante) {
            if (!((Assinante) userLog).getAssinatura().isStatusPagamento()) {
                throw new AssinaturaExpiradaException();
            }
        }


        //Verificar se a senha bate
        if (userLog.getSenha().equals(senha)) {
            this.usuariologado = userLog;
        } else {
            throw new SenhaErradaException(email);
        }
    }

    public void logoff() {
        this.usuariologado = null;
    }

    public void cadastrarUsuario(Usuario u) throws ElementoJaExisteException, ElementoNullException {
        this.controllerUsuario.cadastrarUsuario(u);
    }

    public void removerUsuario(String email) throws ElementoNaoExisteException {
        this.controllerUsuario.removerUsuario(email);
    }


    //Parte da Produtora
    public void adicionarConteudo(Conteudo adicionado) throws ElementoNullException, NaoProdutoraException {
        if (usuariologado instanceof Produtora) {
            this.controllerConteudo.cadastrarConteudo(adicionado);
            ((Produtora) usuariologado).getProduto().add(adicionado);


        } else {
            throw new NaoProdutoraException();
        }
    }

    public void removerConteudo(String titulo) throws ElementoNaoExisteException, NaoProdutoraException {
        if (usuariologado instanceof Produtora) {
            Conteudo r = this.controllerConteudo.procurarConteudo(titulo);
            this.controllerConteudo.removerConteudo(titulo);
            ((Produtora) usuariologado).getProduto().remove(r);
        } else {
            throw new NaoProdutoraException();
        }

    }

    public void atualizarConteudo(Conteudo antigo, Conteudo novo) throws ElementoJaExisteException, ElementoNullException, MesmoElementoException, NaoProdutoraException {
        if (usuariologado instanceof Produtora) {
            int index = ((Produtora) usuariologado).getProduto().indexOf(antigo);
            ((Produtora) usuariologado).getProduto().set(index, novo);
            this.controllerConteudo.atualizarConteudo(antigo, novo);
        } else {
            throw new NaoProdutoraException();
        }
    }

    public String gerarRelatorio() throws NaoProdutoraException {
        String resultado = "";
        if (usuariologado instanceof Produtora) {
            resultado += "-------------RELATÓRIO DOS PRODUTOS-------------\n";
            List<Conteudo> conteudo = ((Produtora) usuariologado).getProduto();
            for (Conteudo c : conteudo) {
                resultado += "Título: " + c.getTitulo() +
                        " | Nota do Público: " + c.getNotaGeral()
                        + " | Visualizações: " + c.getNumeroViews() + "\n";
            }
        } else {
            throw new NaoProdutoraException();
        }
        return resultado;
    }

    //Parte dos Assinantes
    public void assistirConteudo(String titulo) throws ElementoNaoExisteException, NaoAssinanteException {
        if (usuariologado instanceof Assinante) {
            Assinante u = (Assinante) this.controllerUsuario.procurarUsuario(usuariologado.getEmail());
            u.getHistorico().add(this.controllerConteudo.procurarConteudo(titulo));
            this.controllerConteudo.assistirConteudo(titulo);

        } else {
            throw new NaoAssinanteException();
        }

        //Colocar pra séries e filmes
    }

    public void adicionarFavorito(String titulo) throws ElementoNaoExisteException, NaoAssinanteException {
        if (usuariologado instanceof Assinante) {
            Assinante u = (Assinante) this.controllerUsuario.procurarUsuario(usuariologado.getEmail());
            u.getConteudosFavoritos().add(this.controllerConteudo.procurarConteudo(titulo));
        } else {
            throw new NaoAssinanteException();
        }

    }

    public void realizarAvaliacao(int nota, String titulo) throws ElementoNaoExisteException, ElementoNullException, ElementoJaExisteException, NaoAssinanteException {
        if (usuariologado instanceof Assinante) {
            Conteudo addNota = this.controllerConteudo.procurarConteudo(titulo);
            Avaliacao novaAV = new Avaliacao(nota, (Assinante) usuariologado);
            addNota.getAvaliacoes().add(novaAV);
            addNota.atualizarNota();
            this.controllerAvaliacao.cadastrarAvaliacao(novaAV);
        } else {
            throw new NaoAssinanteException();
        }
    }


    public void realizarAssinatura(String email, String numCartao) throws ElementoNaoExisteException, ElementoNullException, NaoAssinanteException {
        if (this.controllerUsuario.procurarUsuario(email) instanceof Assinante) {
            if (this.controllerUsuario.existeUsuario(email)) {

                Assinante m = (Assinante) this.controllerUsuario.procurarUsuario(email);
                m.getAssinatura().setStatusPagamento(true); //Verificar
                m.getAssinatura().setNumeroCartao(numCartao);
                this.controllerAssinatura.cadastrarAssinatura(m.getAssinatura());

            }
        } else {
            throw new NaoAssinanteException();
        }
    }

    public Usuario getUsuariologado() {
        return usuariologado;
    }

    public String getUsuarioString(String email) throws ElementoNaoExisteException {
        return this.controllerUsuario.procurarUsuario(email).toString();
    }

}

