package dominio.negocios;

import dominio.dados.RepositorioConteudoList;
import dominio.dados.interfaces.IRepositorioConteudo;
import dominio.exceptions.ElementoJaExisteException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.exceptions.MesmoElementoException;
import dominio.negocios.beans.Conteudo;

import java.util.List;
import java.util.UUID;

public class ControllerConteudo {

    private static ControllerConteudo instancia;
    private final IRepositorioConteudo repositorio;

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
            if (!existeConteudo(c.getConteudoID())) {
                this.repositorio.cadastrar(c);
            }

        } else {
            throw new ElementoNullException();
        }

    }

    //DELETE
    public void removerConteudo(UUID id) throws ElementoNaoExisteException {
        Conteudo removido = procurarConteudo(id);

        if (removido != null) {
            this.repositorio.remover(id);
        }
    }

    //UPDATE
    public void atualizarConteudo(UUID antigoid, Conteudo novo) throws MesmoElementoException, ElementoNullException, ElementoJaExisteException, ElementoNaoExisteException {
        if (!procurarConteudo(antigoid).equals(novo.getConteudoID())) {
            if (!existeConteudo(novo.getConteudoID())) {
                repositorio.atualizar(antigoid, novo);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new MesmoElementoException();
        }
    }

    //READ
    public Conteudo procurarConteudo(UUID id) throws ElementoNaoExisteException {
        return this.repositorio.procurar(id);
    }

    public List<Conteudo> procurarPorTitulo(String titulo){
        return this.repositorio.procurarPorTitulo(titulo);
    }


    public boolean existeConteudo(UUID id) {
        return this.repositorio.existe(id);
    }


    public void assistirConteudo(UUID id) throws ElementoNaoExisteException {
        Conteudo conteudo = procurarConteudo(id);
        conteudo.setNumeroViews(1);

    }
}
