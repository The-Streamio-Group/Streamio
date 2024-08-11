package dominio.dados;

import dominio.exceptions.UsuarioNullException;
import dominio.negocios.beans.Usuario;
import dominio.negocios.beans.Perfil;

// Usuarios que adicionam perfis
public class RepositorioUsuarioArray {
    // TODO: Decidir se será feito uma concatenação de IDs entre Usuário e Perfil ou uso de UUID.
    // TODO: Decidir se será um repositório acoplado para @class Usuário e Perfil ou dividir em dois reps.
    private Usuario[] usuarios;
    private int proxima;

    private static RepositorioUsuarioArray instance;

    private RepositorioUsuarioArray(int tamanho) {
        this.usuarios = new Usuario[tamanho];
        this.proxima = 0;
    }

    public static IRepositorioUsuario getInstance() {
        if(instance == null) {
            instance = new RepositorioUsuarioArray(100); //Tamanho padrão = 100
        }
        return (IRepositorioUsuario) instance;
    }

    // incorporar UsuarioJaExisteException?
    public void cadastrar(Usuario u) {
        this.usuarios[this.proxima] = u;
        this.proxima = this.proxima + 1;
        if(this.proxima == this.usuarios.length) {
            this.duplicaArrayUsuarios();
        }
    }

    public void duplicaArrayUsuarios() {
        if(this.usuarios != null && this.usuarios.length > 0) {
            Usuario[] arrayDuplicado = new Usuario[this.usuarios.length * 2];
            for(int i = 0; i < this.usuarios.length; i++) {
                arrayDuplicado[i] = this.usuarios[i];
            }
            this.usuarios = arrayDuplicado;
        }
    }

    public Usuario procurar(String usuarioID) throws UsuarioNullException {
        int i = this.procurarIndice(usuarioID);
        Usuario resultado = null;
        if(i != this.proxima) {
            resultado = this.usuarios[i];
        } else {
            throw new UsuarioNullException();
        }
        return resultado;
    }

    public int procurarIndice(String usuarioID) {
        int i = 0;
        boolean achou = false;
        while((!achou) && (i < this.proxima)) {
            if(usuarioID.equals(this.usuarios[i].getID())) {
                achou = true;
            } else {
                i = i + 1;
            }
        }
        return i;
    }

    public boolean existe(String usuarioID) {
        boolean existe = false;
        int indice = this.procurarIndice(usuarioID);
        if(indice != proxima) {
            existe = true;
        }
        return existe;
    }

    public void remover(String usuarioID) throws UsuarioNullException {
        int i = this.procurarIndice(usuarioID);
        if(i != this.proxima) {
            this.usuarios[i] = this.usuarios[this.proxima - 1];
            this.usuarios[this.proxima - 1] = null;
            this.proxima = this.proxima - 1;
        } else {
            throw new UsuarioNullException();
        }
    }

    // public void adicionarPerfil(Perfil p)
    // public void adicionarPerfil(String perfilID, String nickname, boolean tipo)
    // public void removerPerfil(String perfilID)

    // Dois parâmetros para editar
    // public void editarPerfil(String nickname)
    // public void editarPerfil(boolean tipo)

}
