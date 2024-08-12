package dominio.dados;

import dominio.exceptions.UsuarioNullException;
import dominio.negocios.beans.Usuario;

public interface IRepositorioUsuario {
    void cadastrar(Usuario u);

    //void cadastrar(String usuarioID, String email, String senha);

    Usuario procurar(String usuarioID) throws UsuarioNullException;

    boolean existe(String usuarioID);

    void remover(String usuarioID) throws UsuarioNullException;


}
