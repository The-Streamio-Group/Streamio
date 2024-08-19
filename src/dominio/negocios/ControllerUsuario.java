package dominio.negocios;

import dominio.dados.RepositorioUsuarioList;
import dominio.dados.interfaces.IRepositorioUsuario;
import dominio.exceptions.*;
import dominio.negocios.beans.Usuario;

import java.util.UUID;

public class ControllerUsuario {
    private static ControllerUsuario instance;

    private IRepositorioUsuario repositorio;

    private ControllerUsuario() {
        this.repositorio = RepositorioUsuarioList.getInstance();
    }

    public static ControllerUsuario getInstance() {
        if (instance == null) {
            instance = new ControllerUsuario();
        }
        return instance;
    }

    public void cadastrarUsuario(Usuario u) throws ElementoNullException {
        if (u != null) {
            if (!existeUsuario(u.getUsuarioID())) {
                this.repositorio.cadastrar(u);
            }
        } else {
            throw new ElementoNullException();
        }

    }


    public void removerUsuario(UUID id) throws ElementoNaoExisteException {
        Usuario removido = procurarUsuario(id);
        if (removido != null) {
            this.repositorio.remover(id);
        } else {
            throw new ElementoNaoExisteException();
        }
    }

    public Usuario procurarUsuario(UUID id) throws ElementoNaoExisteException {
        return this.repositorio.procurar(id);
    }

    public Usuario procurarUsuarioPorEmail(String email) throws ElementoNaoExisteException {
        return this.repositorio.procurarPorEmail(email);
    }

    public boolean existeUsuario(UUID id) {
        return this.repositorio.existe(id);
    }

    public void atualizarUsuario(UUID antigoid, Usuario novo) throws ElementoNaoExisteException, ElementoJaExisteException, ElementoNullException, MesmoElementoException {
        if (procurarUsuario(antigoid).equals(novo)) {
            if (!this.repositorio.existe(novo.getUsuarioID())) {
                repositorio.atualizar(antigoid, novo);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new MesmoElementoException();
        }

    }

    public void atualizarNomeUsuario(UUID id, String nomeNovo) throws ElementoNaoExisteException, MesmoNomeException {
        Usuario novo = procurarUsuario(id);
        if (!novo.getNickname().equals(nomeNovo)) {
            novo.setNickname(nomeNovo);
        } else {
            throw new MesmoNomeException();
        }
    }

    public void alterarSenhaUsuario(UUID id, String senhaNova) throws ElementoNaoExisteException, MesmoNomeException {
        Usuario novo = procurarUsuario(id);
        if (!novo.getSenha().equals(senhaNova)) {
            novo.setSenha(senhaNova);
        } else {
            throw new MesmoNomeException();
        }
    }


}
