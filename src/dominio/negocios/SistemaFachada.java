package dominio.negocios;

import dominio.exceptions.*;
import dominio.negocios.beans.*;

import java.util.List;

public class SistemaFachada implements ISistemaFachada {
    private final CadastroAssinatura cadastroAssinatura;
    private final CadastroAvaliacao cadastroAvaliacao;
    private final CadastroConteudo cadastroConteudo;
    private final CadastroUsuario cadastroUsuario;

    private static SistemaFachada instancia;
    private Usuario usuariologado; //Instância do usuário logado

    public SistemaFachada() {
        this.cadastroUsuario = CadastroUsuario.getInstance();
        this.cadastroAssinatura = CadastroAssinatura.getInstance();
        this.cadastroAvaliacao = CadastroAvaliacao.getInstance();
        this.cadastroConteudo = CadastroConteudo.getInstance();
    }

    public static SistemaFachada getInstance(){
        if(instancia == null){
            instancia = new SistemaFachada();
        }
        return instancia;
    }

    public void realizarLogin(String email, String senha) throws ElementoNaoExisteException, UsuarioJaLogadoException, AssinaturaExpiradaException, SenhaErradaException{

        //Verificar se o usuário já está logado
        if(usuariologado != null){
            throw new UsuarioJaLogadoException();
        }

        Usuario userLog = this.cadastroUsuario.procurarUsuario(email);


        //Caso a assinatura expire
        if(userLog instanceof Assinante){
           if(!((Assinante) userLog).getAssinatura().isStatusPagamento()) {
               throw new AssinaturaExpiradaException();
           }
        }


        //Verificar se a senha bate
        if(userLog.getSenha().equals(senha)){
            this.usuariologado = userLog;
        }else{throw new SenhaErradaException(email);}
    }

    public void logoff(){
        this.usuariologado = null;
    }

    public void cadastrarUsuario(Usuario u) throws ElementoJaExisteException, ElementoNullException{
        this.cadastroUsuario.cadastrarUsuario(u);
    }

    public void removerUsuario(String email) throws ElementoNaoExisteException{
        this.cadastroUsuario.removerUsuario(email);
    }


    //Parte da Produtora
    public void adicionarConteudo(Conteudo adicionado) throws ElementoNullException, NaoProdutoraException {
        if(usuariologado instanceof Produtora){
            this.cadastroConteudo.cadastrarConteudo(adicionado);
           ((Produtora) usuariologado).getProduto().add(adicionado);



        }
        else{
            throw new NaoProdutoraException();
        }
    }

    public void removerConteudo(String titulo) throws ElementoNaoExisteException, NaoProdutoraException {
        if(usuariologado instanceof Produtora) {
            Conteudo r = this.cadastroConteudo.procurarConteudo(titulo);
            this.cadastroConteudo.removerConteudo(titulo);
            ((Produtora) usuariologado).getProduto().remove(r);
        }
        else {
            throw new NaoProdutoraException();
        }

    }

    public void atualizarConteudo(Conteudo antigo, Conteudo novo) throws ElementoJaExisteException, ElementoNullException, MesmoElementoException, NaoProdutoraException {
        if(usuariologado instanceof Produtora) {
            int index = ((Produtora) usuariologado).getProduto().indexOf(antigo);
            ((Produtora) usuariologado).getProduto().set(index, novo);
            this.cadastroConteudo.atualizarConteudo(antigo, novo);
        }
        else {
            throw new NaoProdutoraException();
        }
    }

    public String gerarRelatorio() throws NaoProdutoraException {
        String resultado = "";
        if(usuariologado instanceof Produtora){
            resultado += "-------------RELATÓRIO DOS PRODUTOS-------------\n";
            List<Conteudo> conteudo = ((Produtora) usuariologado).getProduto();
            for (Conteudo c: conteudo){
                resultado += "Título: " + c.getTitulo() +
                        " | Nota do Público: " + c.getNotaGeral()
                        +" | Visualizações: " + c.getNumeroViews() + "\n";
            }
        }
        else{throw new NaoProdutoraException();}
        return resultado;
    }

    //Parte dos Assinantes
    public void assistirConteudo(String titulo) throws ElementoNaoExisteException, NaoAssinanteException {
        if(usuariologado instanceof Assinante){
            Assinante u = (Assinante) this.cadastroUsuario.procurarUsuario(usuariologado.getEmail());
            u.getHistorico().add(this.cadastroConteudo.procurarConteudo(titulo));
            this.cadastroConteudo.assistirConteudo(titulo);

        }
        else {
            throw new NaoAssinanteException();
        }
    }

    public void adicionarFavorito(String titulo) throws ElementoNaoExisteException, NaoAssinanteException {
        if(usuariologado instanceof Assinante){
            Assinante u = (Assinante) this.cadastroUsuario.procurarUsuario(usuariologado.getEmail());
            u.getConteudosFavoritos().add(this.cadastroConteudo.procurarConteudo(titulo));
        }
        else {
            throw new NaoAssinanteException();
        }

    }

    public void realizarAvaliacao(int nota, String titulo) throws ElementoNaoExisteException, ElementoNullException, ElementoJaExisteException, NaoAssinanteException {
        if(usuariologado instanceof Assinante){
            Conteudo addNota = this.cadastroConteudo.procurarConteudo(titulo);
            Avaliacao novaAV = new Avaliacao(nota, (Assinante) usuariologado);
            addNota.getAvaliacoes().add(novaAV);
            addNota.atualizarNota();
            this.cadastroAvaliacao.cadastrarAvaliacao(novaAV);
        }
        else {
            throw new NaoAssinanteException();
        }
    }



    public void realizarAssinatura(String email, String numCartao) throws ElementoNaoExisteException, ElementoNullException, NaoAssinanteException {
        if (this.cadastroUsuario.procurarUsuario(email) instanceof Assinante) {
            if (this.cadastroUsuario.existeUsuario(email)) {

                Assinante m = (Assinante) this.cadastroUsuario.procurarUsuario(email);
                m.getAssinatura().setStatusPagamento(true);
                m.getAssinatura().setNumeroCartao(numCartao);
                this.cadastroAssinatura.cadastrarAssinatura(m.getAssinatura());

            }
        }
        else{throw new NaoAssinanteException();}
    }

    public Usuario getUsuariologado() {
        return usuariologado;
    }

    public String getUsuarioString(String email) throws ElementoNaoExisteException {
        return this.cadastroUsuario.procurarUsuario(email).toString();
    }

}

