package dominio.dados;

import dominio.dados.interfaces.IRepositorioAssinatura;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositorioAssinaturaList extends RepositorioGenericoList<Assinatura> implements IRepositorioAssinatura, Serializable {

    /*
     * Classe que contém o repositório de todas as Assinaturas
     * e seus respectivos CRUDs.
     */
    @Override
    protected UUID getId(Assinatura assinatura) {
        return assinatura.getAssinaturaID();
    }

    private static RepositorioAssinaturaList instance;

    private RepositorioAssinaturaList() {
        super();
    }

    //Instância do repositório
    public static RepositorioAssinaturaList getInstance() {
        if (instance == null) {
            instance = RepositorioAssinaturaList.lerArquivo();
        }
        return instance;
    }

    private static RepositorioAssinaturaList lerArquivo() {
        RepositorioAssinaturaList instanciaLocal;

        File in = new File("assinatura.dat");
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(in);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Object o = objectInputStream.readObject();
            instanciaLocal = (RepositorioAssinaturaList) o;
        } catch (Exception e) {
            instanciaLocal = new RepositorioAssinaturaList();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {/* Silent exception */
                }
            }
        }
        return instanciaLocal;
    }

    public void salvarArquivos() {
        if (instance == null) {
            return;
        }
        File out = new File("assinatura.dat");
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(out);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(instance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    /* Silent */
                }
            }
        }
    }

    @Override
    public Assinatura procurarPorNumCartao(String numCartao) throws ElementoNaoExisteException {
        for (Assinatura assinatura : objectList) {
            if (assinatura.getNumeroCartao().equals(numCartao)) {
                return assinatura;
            }
        }
        throw new ElementoNaoExisteException();
    }

    @Override
    public void cancelar(UUID assinaturaID) throws ElementoNaoExisteException{
        Assinatura cancelado = procurar(assinaturaID);
        if(cancelado == null){
            throw new ElementoNaoExisteException();
        } else{
            cancelado.setStatusPagamento(false);
            cancelado.setDataExpiracao(LocalDate.of(2020,2,13));
        }
    }

}

