package dominio.dados;

import dominio.dados.interfaces.IRepositorioGeneric;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
import dominio.negocios.beans.Assinatura;


import java.util.ArrayList;

public class RepositorioAssinaturaList implements IRepositorioGeneric<Assinatura> {

    /*
     * Classe que contém o repositório de todas as Assinaturas
     * e seus respectivos CRUDs.
     */

    private ArrayList<Assinatura> assinaturasList;

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
    public Assinatura procurar(String numeroCartao) throws ElementoNaoExisteException {
        for (Assinatura assinatura : assinaturasList) {
            if (assinatura.getNumeroCartao().equals(numeroCartao)) {
                return assinatura;
            }
        }
        throw new ElementoNaoExisteException();
    }

    //DELETE
    @Override
    public void remover(String numeroCartao) throws ElementoNaoExisteException {
            Assinatura removido = procurar(numeroCartao);
        if (removido == null) {
            throw new ElementoNaoExisteException();
        } else {
            this.assinaturasList.remove(removido);
        }
    }

    //UPDATE
    @Override
    public void atualizar(Assinatura antigo, Assinatura novo) throws ElementoNullException {
        if(novo == null){throw new ElementoNullException();}

        boolean antigoE = existe(antigo.getNumeroCartao());
        if(antigoE){
            int indice = assinaturasList.indexOf(antigo);
            this.assinaturasList.set(indice, novo);

        }
    }

    //Método que procura assinaturas apartir do numeroCartao
    @Override
    public boolean existe(String numeroCartao) {
        boolean existe = false;
        for (Assinatura assinatura : assinaturasList) {
            if(assinatura.getNumeroCartao().equals(numeroCartao)) {
                existe = true;
                break;
            }
        }
        return existe;
    }


    //Método que procura o índice a partir do numeroCartao
    @Override
    public int procurarIndice(String numeroCartao) throws ElementoNaoExisteException {
        for(int i = 0; i < this.assinaturasList.size(); i++){
            if(assinaturasList.get(i).getNumeroCartao().equals(numeroCartao)){
                return i;
            }
        }
        throw new ElementoNaoExisteException();
    }


    public int totalUsuarios(){

        return this.assinaturasList.size();
    }



}

