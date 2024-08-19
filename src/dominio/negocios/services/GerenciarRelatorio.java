package dominio.negocios.services;

import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.NaoProdutoraException;
import dominio.negocios.ControllerAssinatura;
import dominio.negocios.ControllerConteudo;
import dominio.negocios.ControllerPerfil;
import dominio.negocios.ControllerUsuario;
import dominio.negocios.beans.Avaliacao;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Produtora;
import dominio.negocios.beans.Usuario;

public class GerenciarRelatorio {
    private static GerenciarRelatorio instance;

    private final ControllerUsuario controleUsuario;
    private final ControllerPerfil controlePerfil;
    private final ControllerConteudo controleConteudo;

    private GerenciarRelatorio() {
        this.controleUsuario = ControllerUsuario.getInstance();
        this.controlePerfil = ControllerPerfil.getInstance();
        this.controleConteudo = ControllerConteudo.getInstance();
    }

    public static GerenciarRelatorio getInstance(){
        if(instance == null){
            instance = new GerenciarRelatorio();
        }
        return instance;
    }

    private float retornarFaixaEtaria(Conteudo conteudo){
        int soma = 0;
        if(conteudo.tamanhoAvaliacoes() > 0) {
            for (Avaliacao avaliacao : conteudo.getAvaliacoes()) {
                soma += avaliacao.getPerfil().getIdade();
            }
            return (float) soma / conteudo.tamanhoAvaliacoes();
        }
        return (float) soma;
    }

    public String GerarRelatorio(Usuario usuario) throws ElementoNaoExisteException, NaoProdutoraException {
        if(usuario instanceof Produtora) {
            Produtora produtoraRelatorio = (Produtora) this.controleUsuario.procurarUsuario(usuario.getUsuarioID());
            String resultado = "\n     ** Relatório da Produtora ** \n";
            for (Conteudo conteudo : produtoraRelatorio.getProduto()) {
                resultado += "************************************\n";
                resultado += String.format("* %16s %15s *\n", "Título:", conteudo.getTitulo());
                resultado += String.format("* %16s %15d *\n", "Número de Views:", conteudo.getNumeroViews());
                resultado += String.format("* %16s %15.1f *\n", "Faixa Etária:", retornarFaixaEtaria(conteudo));
                resultado += String.format("* %16s %15.2f *\n", "Notas Gerais:", conteudo.getNotaGeral());
                resultado += "************************************\n";
            }
            return resultado;
        }
        else{
            throw new NaoProdutoraException();
        }
    }




}
