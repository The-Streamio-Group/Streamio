package dominio.dados;

import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.Assinatura;

import java.util.ArrayList;
import java.util.UUID;

public class RepositorioAssinaturaList implements IRepositorioGeneric<Assinatura> {

    /*
     * Classe que contém o repositório de todas as Assinaturas
     * e seus respectivos CRUDs.
     */
    // não usa mais numeroCartao, usa UUID

    private final ArrayList<Assinatura> assinaturasList;

    private static RepositorioAssinaturaList instance;

    private RepositorioAssinaturaList() {
        this.assinaturasList = new ArrayList<>();
    }

    public static IRepositorioGeneric<Assinatura> getInstance() {
        if (instance == null) {
            instance = new RepositorioAssinaturaList();
        }
        return (IRepositorioGeneric<Assinatura>) instance;
    }

    //CREATE
    @Override
    public void cadastrar(Assinatura assinatura) {
        this.assinaturasList.add(assinatura);
    }

    //READ
    @Override
    public Assinatura procurar(UUID assinaturaID) throws ElementoNaoExisteException {
        for (Assinatura assinatura : assinaturasList) {
            if (assinatura.getAssinaturaID().equals(assinaturaID)) {
                return assinatura;
            }
        }
        throw new ElementoNaoExisteException();
    }

    //DELETE
    @Override
    public void remover(UUID assinaturaID) throws ElementoNaoExisteException {
        Assinatura removido = procurar(assinaturaID);
        if (removido == null) {
            throw new ElementoNaoExisteException();
        } else {
            this.assinaturasList.remove(removido);
        }
    }

    //UPDATE
    @Override
    public void atualizar(UUID antigoid, Assinatura novo) throws ElementoNullException, ElementoNaoExisteException {
        if (novo == null) {
            throw new ElementoNullException();
        }
        boolean antigoE = existe(antigoid);
        if (antigoE) {
            int indice = procurarIndice(antigoid);
            this.assinaturasList.set(indice, novo);
        } else {
            throw new ElementoNaoExisteException();
        }
    }

    //Método que procura assinaturas a partir do --numeroCartao-- (agora UUID)
    @Override
    public boolean existe(UUID assinaturaID) {
        boolean existe = false;
        for (Assinatura assinatura : assinaturasList) {
            if (assinatura.getAssinaturaID().equals(assinaturaID)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    //Método que procura o índice a partir do --numeroCartao-- (agora UUID)
    private int procurarIndice(UUID assinaturaID) throws ElementoNaoExisteException {
        for (int i = 0; i < this.assinaturasList.size(); i++) {
            if (assinaturasList.get(i).getAssinaturaID().equals(assinaturaID)) {
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }

    public int totalUsuarios() {
        return this.assinaturasList.size();
    }
}

