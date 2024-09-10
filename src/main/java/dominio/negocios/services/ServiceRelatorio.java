package dominio.negocios.services;

import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.NaoProdutoraException;
import dominio.negocios.ControllerConteudo;
import dominio.negocios.ControllerPerfil;
import dominio.negocios.ControllerUsuario;
import dominio.negocios.beans.Avaliacao;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Produtora;
import dominio.negocios.beans.Usuario;

public class ServiceRelatorio {
    private static ServiceRelatorio instance;

    private final ControllerUsuario controleUsuario;

    private ServiceRelatorio() {
        this.controleUsuario = ControllerUsuario.getInstance();
    }

    public static ServiceRelatorio getInstance() {
        if (instance == null) {
            instance = new ServiceRelatorio();
        }
        return instance;
    }

    private float retornarFaixaEtaria(Conteudo conteudo) {
        int soma = 0;
        if (conteudo.tamanhoAvaliacoes() > 0) {
            for (Avaliacao avaliacao : conteudo.getAvaliacoes()) {
                soma += avaliacao.getPerfil().getIdade();
            }
            return (float) soma / conteudo.tamanhoAvaliacoes();
        }
        return (float) soma;
    }

    //Código Visual
    public String GerarRelatorio(Usuario usuario) throws ElementoNaoExisteException, NaoProdutoraException {
        if (usuario instanceof Produtora) {
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
        } else {
            throw new NaoProdutoraException();
        }
    }


}
