package dominio.dados;

import dominio.dados.interfaces.IRepositorioUsuario;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.Usuario;

import java.util.ArrayList;
import java.util.UUID;

public class RepositorioUsuarioList implements IRepositorioUsuario {

    /*
     * Classe que contém o repositório de todos os usuários
     * e seus respectivos CRUDs.
     */

    private final ArrayList<Usuario> usuariosList;

    private static RepositorioUsuarioList instance;

    private RepositorioUsuarioList() {
        this.usuariosList = new ArrayList<>();
    }

    //Instância do repositório
    public static RepositorioUsuarioList getInstance() {
        if (instance == null) {
            instance = new RepositorioUsuarioList();
        }
        return instance;
    }

    //CREATE
    @Override
    public void cadastrar(Usuario u) {
        usuariosList.add(u);
    }

    //READ
    @Override
    public Usuario procurar(UUID usuarioID) throws ElementoNaoExisteException {
        for (Usuario usuario : this.usuariosList) {
            if (usuario.getUsuarioID().equals(usuarioID)) {
                return usuario;
            }
        }
        //Caso ele não exista, exceção
        throw new ElementoNaoExisteException();
    }

    @Override
    public Usuario procurarPorEmail(String email) throws ElementoNaoExisteException {
        for (Usuario usuario : this.usuariosList) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        //Caso ele não exista, exceção
        throw new ElementoNaoExisteException();
    }


    //Método que procura o índice a partir do email
    private int procurarIndice(UUID usuarioID) throws ElementoNaoExisteException {
        for (int i = 0; i < this.usuariosList.size(); i++) {
            if (usuariosList.get(i).getUsuarioID().equals(usuarioID)) {
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }

    //DELETE
    @Override
    public void remover(UUID usuarioID) throws ElementoNaoExisteException {
        Usuario removido = procurar(usuarioID);
        if (removido == null) {
            throw new ElementoNaoExisteException();
        }
        this.usuariosList.remove(removido);
    }

    //UPDATE
    @Override
    public void atualizar(UUID antigoid, Usuario novo) throws ElementoNullException, ElementoNaoExisteException {
        if (novo == null) {
            throw new ElementoNullException();
        }
        boolean antigoE = existe(antigoid);
        if (antigoE) {
            int indice = procurarIndice(antigoid);
            this.usuariosList.set(indice, novo);
        } else {
            throw new ElementoNaoExisteException();
        }
    }

    //Método que pergunta se existe o usuário para o Repositório
    @Override
    public boolean existe(UUID id) {
        for (Usuario usuario : this.usuariosList) {
            if (usuario.getUsuarioID().equals(id)) {
                return true;
            }
        }
        return false;
    }
}

