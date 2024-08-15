package dominio.negocios;

import dominio.dados.RepositorioUsuarioList;
import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoJaExisteException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.exceptions.MesmoElementoException;
import dominio.negocios.beans.Usuario;

public class CadastroUsuario  {
    private static CadastroUsuario instancia;
    private final IRepositorioGeneric<Usuario> repositorio;

    private CadastroUsuario() {
        this.repositorio = RepositorioUsuarioList.getInstance();
    }

    public static CadastroUsuario getInstance() {
        if(instancia == null) {
            instancia = new CadastroUsuario();

        }
        return instancia;
    }

    public void cadastrarUsuario(Usuario u) throws ElementoJaExisteException, ElementoNullException {
        if(u != null) {
            if (!repositorio.existe(u.getEmail())) {
                this.repositorio.cadastrar(u);
            } else {
                throw new ElementoJaExisteException();
            }
        } else {
            throw new ElementoNullException();
        }
    }

    public void removerUsuario(String email) throws ElementoNaoExisteException {
        Usuario removido = this.repositorio.procurar(email);
        if(removido != null){
            this.repositorio.remover(email);
        }
    }

    public Usuario procurarUsuario(String email) throws ElementoNaoExisteException {
        return this.repositorio.procurar(email);
    }

    public int procurarIndiceUsuario(String email) throws ElementoNaoExisteException{
        return this.repositorio.procurarIndice(email);
    }

    public boolean existeUsuario(String email){
        return this.repositorio.existe(email);
    }

    public void atualizarUsuario(Usuario antigo, Usuario novo) throws ElementoNullException, MesmoElementoException, ElementoJaExisteException {
        if(!antigo.equals(novo)) {
            if(!this.repositorio.existe(novo.getEmail())) {
                this.repositorio.atualizar(antigo, novo);
            }
            else{throw new ElementoJaExisteException();}
        }
        else{throw new MesmoElementoException();}
    }



}
