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

public class RepositorioUsuarioList extends RepositorioGenericoList<Usuario> implements IRepositorioUsuario, Serializable {
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
            instance = RepositorioUsuarioList.lerArquivo();
        }
        return instance;
    }

    private static RepositorioUsuarioList lerArquivo() {
        RepositorioUsuarioList instanciaLocal;

        File in = new File("usuarios.dat");
        FileInputStream fis;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanciaLocal = (RepositorioUsuarioList) o;
        } catch (Exception e) {
            instanciaLocal = new RepositorioUsuarioList();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {/* Silent exception */
                }
            }
        }

        return instanciaLocal;
    }

    public void salvarArquivo() {
        if (instance == null) {
            return;
        }
        File out = new File("cadernos.dat");
        FileOutputStream fos;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(instance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    /* Silent */
                }
            }
        }
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

