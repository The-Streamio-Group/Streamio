package dominio.dados;

import dominio.dados.interfaces.IRepositorioUsuario;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.ReproducaoConteudo;
import dominio.negocios.beans.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class RepositorioUsuarioList extends RepositorioGenericoList<Usuario> implements IRepositorioUsuario {
    /*
     * Classe que contém o repositório de todos os usuários
     * e seus respectivos CRUDs.
     */
    @Override
    protected UUID getId(Usuario usuario) {
        return usuario.getUsuarioID();
    }

    private static RepositorioUsuarioList instance;

    private RepositorioUsuarioList() {
        super();
    }

    //Instância do repositório
    public static RepositorioUsuarioList getInstance() {
        if (instance == null) {
            instance = new RepositorioUsuarioList();
        }
        return instance;
    }

    @Override
    public Usuario procurarPorEmail(String email) throws ElementoNaoExisteException {
        for (Usuario usuario : this.objectList ) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        //Caso ele não exista, exceção
        throw new ElementoNaoExisteException();
    }
}

