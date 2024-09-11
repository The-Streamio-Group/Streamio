package dominio.dados;

import dominio.dados.interfaces.IRepositorioPerfil;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.negocios.beans.Perfil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class RepositorioPerfilList extends RepositorioGenericoList<Perfil> implements IRepositorioPerfil, Serializable {

    private static RepositorioPerfilList instance;

    //Implementação do getId específico do repositório
    @Override
    protected UUID getId(Perfil perfil) {
        return perfil.getPerfilID();
    }

    private RepositorioPerfilList() {
        super();
    }

    //Instância do repositório
    public static RepositorioPerfilList getInstance() {
        if (instance == null) {
            instance = new RepositorioPerfilList();
        }
        return instance;
    }
    @Override
    public Perfil procurarPorNick(String nickname) throws ElementoNaoExisteException {
        for (Perfil perfil : this.objectList) {
            if (perfil.getNick().equals(nickname)) {
                return perfil;
            }
        }
        //Caso ele não exista, exceção
        throw new ElementoNaoExisteException();
    }
}
