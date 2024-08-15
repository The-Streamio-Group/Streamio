package dominio.negocios;

import dominio.dados.RepositorioConteudoList;
import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoJaExisteException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.exceptions.MesmoElementoException;
import dominio.negocios.beans.Conteudo;

public class ControllerConteudo {

    private static ControllerConteudo instancia;
    private final IRepositorioGeneric<Conteudo> repositorio;

    private ControllerConteudo() {
        this.repositorio = RepositorioConteudoList.getInstance();
    }

    public static ControllerConteudo getInstance() {
        if (instancia == null) {
            instancia = new ControllerConteudo();
        }
        return instancia;
    }

    //CREATE
    public void cadastrarConteudo(Conteudo c) throws ElementoNullException {
        if (c != null) {
            if (!repositorio.existe(c.getTitulo())) {
                this.repositorio.cadastrar(c);
            }

        } else {
            throw new ElementoNullException();
        }

    }

    //DELETE
    public void removerConteudo(String titulo) throws ElementoNaoExisteException {
        Conteudo removido = this.repositorio.procurar(titulo);

        if (removido != null) {
            this.repositorio.remover(titulo);
        }
    }

    //UPDATE
    public void atualizarConteudo(Conteudo antigo, Conteudo novo) throws MesmoElementoException, ElementoNullException, ElementoJaExisteException {
        if (!antigo.equals(novo)) {
            if (!this.repositorio.existe(novo.getTitulo())) {
                repositorio.atualizar(antigo, novo);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new MesmoElementoException();
        }
    }

    //READ
    public Conteudo procurarConteudo(String titulo) throws ElementoNaoExisteException {
        return this.repositorio.procurar(titulo);
    }

    public int procurarIndiceConteudo(String titulo) throws ElementoNaoExisteException {
        return this.repositorio.procurarIndice(titulo);
    }

    public boolean existeConteudo(String titulo) {
        return this.repositorio.existe(titulo);
    }


    public void assistirConteudo(String titulo) throws ElementoNaoExisteException {
        Conteudo conteudo = this.repositorio.procurar(titulo);
        conteudo.setNumeroViews(1);
    }
}
