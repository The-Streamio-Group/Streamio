package dominio.dados;

import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoNaoExisteException;

import dominio.exceptions.ElementoNullException;
import dominio.negocios.beans.Usuario;

import java.util.ArrayList;

public class RepositorioUsuarioList implements IRepositorioGeneric<Usuario>{

    /*
     * Classe que contém o repositório de todos os usuários
     * e seus respectivos CRUDs.
     */

    private ArrayList<Usuario> usuariosList;

    private static RepositorioUsuarioList instance;

    private RepositorioUsuarioList() {
        this.usuariosList = new ArrayList<>();
    }

    //Instância do repositório
    public static IRepositorioGeneric<Usuario> getInstance() {
        if (instance == null) {
            instance = new RepositorioUsuarioList();
        }
        return (IRepositorioGeneric<Usuario>) instance;
    }

    //CREATE
    @Override
    public void cadastrar(Usuario u) {
        usuariosList.add(u);
    }

    //READ
    @Override
    public Usuario procurar(String email) throws ElementoNaoExisteException {
        for (Usuario usuario : this.usuariosList) {
            if(usuario.getEmail().equals(email)){
                return usuario;
            }
        }

        //Caso ele não exista, exceção
        throw new ElementoNaoExisteException();
    }

    //Método que procura o índice a partir do email
    @Override
    public int procurarIndice(String email) throws ElementoNaoExisteException {
        for(int i = 0; i < this.usuariosList.size(); i++){
            if(usuariosList.get(i).getEmail().equals(email)){
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }

    //DELETE
    @Override
    public void remover(String email) throws ElementoNaoExisteException {
        Usuario removido = procurar(email);
        if(removido == null){throw new ElementoNaoExisteException();}
        this.usuariosList.remove(removido);
    }

    //UPDATE
    @Override
    public void atualizar(Usuario antigo, Usuario novo) throws ElementoNullException {
        if(novo == null){throw new ElementoNullException();}

        boolean antigoE = existe(antigo.getEmail());
        if(antigoE){
            int indice = usuariosList.indexOf(antigo);
            this.usuariosList.set(indice, novo);

        }

    }

    //Método que pergunta se existe o usuário para o Repositório
    @Override
    public boolean existe(String email){
        for (Usuario usuario : this.usuariosList) {
            if(usuario.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }


}

