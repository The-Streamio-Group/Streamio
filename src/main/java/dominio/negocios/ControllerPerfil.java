package dominio.negocios;

import dominio.dados.RepositorioPerfilList;
import dominio.dados.interfaces.IRepositorioPerfil;
import dominio.exceptions.*;
import dominio.negocios.beans.Perfil;

import java.util.UUID;

public class ControllerPerfil {
    private static ControllerPerfil instancia;
    private final IRepositorioPerfil repositorio;

    private ControllerPerfil() {
        this.repositorio = RepositorioPerfilList.getInstance();
    }

    public static ControllerPerfil getInstance() {
        if (instancia == null) {
            instancia = new ControllerPerfil();
        }
        return instancia;
    }

    //CREATE
    public void cadastrarPerfil(Perfil p) throws ElementoNullException {
        if (p != null) {
            if (!existePerfil(p.getPerfilID())) {
                this.repositorio.cadastrar(p);
            }

        } else {
            throw new ElementoNullException();
        }

    }

    //DELETE
    public void removerPerfil(UUID id) throws ElementoNaoExisteException {
        Perfil removido = procurarPerfil(id);

        if (removido != null) {
            this.repositorio.remover(id);
        }
    }

    //UPDATE
    public void atualizarPerfil(UUID antigoid, Perfil novo) throws MesmoElementoException, ElementoNullException, ElementoJaExisteException, ElementoNaoExisteException {
        if (procurarPerfil(antigoid).equals(novo)) {
            if (!existePerfil(novo.getPerfilID())) {
                repositorio.atualizar(antigoid, novo);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new MesmoElementoException();
        }
    }

    public void mudarNomePerfil(UUID id, String nomeNovo) throws ElementoNaoExisteException, MesmoNomeException {
        if (this.repositorio.existe(id)) {
            Perfil nomeMudado = this.repositorio.procurar(id);
            if (!nomeNovo.equals(nomeMudado.getNick())) {
                nomeMudado.setNick(nomeNovo);
            } else {
                throw new MesmoNomeException();
            }
        } else {
            throw new ElementoNaoExisteException();
        }
    }

    public void mudarFaixaEtaria(UUID id, int idadeNova) throws ElementoNaoExisteException, MesmoElementoException {
        if (existePerfil(id)) {
            Perfil faixa = procurarPerfil(id);
            if (!(idadeNova == faixa.getIdade())) {
                faixa.setIdade(idadeNova);
            } else {
                throw new MesmoElementoException();
            }
        } else {
            throw new ElementoNaoExisteException();
        }
    }

    //READ
    public Perfil procurarPerfil(UUID id) throws ElementoNaoExisteException {
        return this.repositorio.procurar(id);
    }

    public Perfil procurarPerfilPorNick(String nickname) throws ElementoNaoExisteException {
        return this.repositorio.procurarPorNick(nickname);
    }

    public boolean existePerfil(UUID id) {
        return this.repositorio.existe(id);
    }


}
