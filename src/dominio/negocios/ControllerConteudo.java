package dominio.negocios;

import dominio.dados.RepositorioConteudoList;
import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoJaExisteException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.exceptions.MesmoElementoException;
import dominio.negocios.beans.Conteudo;

import java.util.UUID;

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
            if (!repositorio.existe(c.getConteudoID())){
                this.repositorio.cadastrar(c);
            }

        } else {
            throw new ElementoNullException();
        }

    }

    //DELETE
    public void removerConteudo(UUID id) throws ElementoNaoExisteException {
        Conteudo removido = this.repositorio.procurar(id);

        if (removido != null) {
            this.repositorio.remover(id);
        }
    }

    //UPDATE
    public void atualizarConteudo(UUID antigoid, Conteudo novo) throws MesmoElementoException, ElementoNullException, ElementoJaExisteException, ElementoNaoExisteException {
        if (this.repositorio.procurar(antigoid).equals(novo)) {
            if (!this.repositorio.existe(novo.getConteudoID())) {
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


    public boolean existeConteudo(UUID id) {
        return this.repositorio.existe(id);
    }


    public void assistirConteudo(UUID id) throws ElementoNaoExisteException {
        Conteudo conteudo = this.repositorio.procurar(id);
        conteudo.setNumeroViews(1);
    }
}
