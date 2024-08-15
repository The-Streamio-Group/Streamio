package dominio.dados;

import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.negocios.beans.Perfil;

import java.util.ArrayList;
import java.util.UUID;

public class RepositorioPerfilList implements IRepositorioGeneric<Perfil> {

    private final ArrayList<Perfil> perfilList;

    private static RepositorioPerfilList instance;

    private RepositorioPerfilList() {
        this.perfilList = new ArrayList<>();
    }

    //Instância do repositório
    public static IRepositorioGeneric<Perfil> getInstance() {
        if (instance == null) {
            instance = new RepositorioPerfilList();
        }
        return instance;
    }

    //CREATE
    @Override
    public void cadastrar(Perfil p) {
        perfilList.add(p);
    }


    //READ
    @Override
    public Perfil procurar(UUID perfilID) throws ElementoNaoExisteException {
        for (Perfil perfil : this.perfilList) {
            if (perfil.getPerfilID().equals(perfilID)) {
                return perfil;
            }
        }
        //Caso ele não exista, exceção
        throw new ElementoNaoExisteException();
    }

    //Método que procura o índice a partir do email
    public int procurarIndice(UUID perfilID) throws ElementoNaoExisteException {
        for (int i = 0; i < this.perfilList.size(); i++) {
            if (perfilList.get(i).getPerfilID().equals(perfilID)) {
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }

    //DELETE
    @Override
    public void remover(UUID perfilID) throws ElementoNaoExisteException {
        Perfil removido = procurar(perfilID);
        if (removido == null) {
            throw new ElementoNaoExisteException();
        }
        this.perfilList.remove(removido);
    }

    //UPDATE
    @Override
    public void atualizar(UUID antigoid, Perfil novo) throws ElementoNullException, ElementoNaoExisteException {
        if (novo == null) {
            throw new ElementoNullException();
        }
        boolean antigoE = existe(antigoid);
        if (antigoE) {
            int indice = perfilList.indexOf(antigoid);
            this.perfilList.set(indice, novo);
        } else {
            throw new ElementoNaoExisteException();
        }
    }

    //Método que pergunta se existe o usuário para o Repositório
    @Override
    public boolean existe(UUID id) {
        for (Perfil perfil : this.perfilList) {
            if (perfil.getPerfilID().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
