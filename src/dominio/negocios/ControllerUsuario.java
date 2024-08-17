package dominio.negocios;

import dominio.dados.RepositorioUsuarioList;
import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoJaExisteException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Produtora;
import dominio.negocios.beans.Usuario;

public class CadastroUsuario {

    private IRepositorioGeneric<Usuario> repositorio;

    public CadastroUsuario(IRepositorioGeneric<Usuario> repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Usuario u){
        if(u != null) {
            if (!repositorio.existe(u.getEmail())) {
                this.repositorio.cadastrar(u);
            }
        } else {
            throw new ElementoNullException();
        }

    }

    public void remover(String email) throws ElementoNaoExisteException {
        Usuario removido = this.repositorio.procurar(email);
        if(removido != null){
            this.repositorio.remover(email);
        }
        else{
            throw new ElementoNaoExisteException();
        }
    }

    public Usuario procurar(String email) throws ElementoNaoExisteException {
        return this.repositorio.procurar(email);
    }

    public int procurarIndice(String email) throws ElementoNaoExisteException{
        return this.repositorio.procurarIndice(email);
    }

    public boolean existe(String email){
        return this.repositorio.existe(email);
    }

    public void atualizar(Usuario antigo, Usuario novo) throws ElementoNaoExisteException, ElementoJaExisteException {
        if(!antigo.equals(novo)) {
            if(!this.repositorio.existe(novo.getEmail())) {
                this.repositorio.atualizar(antigo, novo);
            }
            else{throw new ElementoJaExisteException();}
        }
        else{throw new MesmoElementoException();}
    }

    public void imprimirDados(String email) throws ElementoNaoExisteException{
        this.repositorio.dadosString(email);
    }

}
