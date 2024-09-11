package dominio.dados;

import dominio.dados.interfaces.IRepositorioAvaliacao;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.Perfil;
import dominio.negocios.beans.Avaliacao;
// removido o import de Conteudo

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositorioAvaliacaoList extends RepositorioGenericoList<Avaliacao> implements IRepositorioAvaliacao, Serializable {

    /*
     * Classe que contém o repositório de todas as Avaliações
     * e seus respectivos CRUDs.
     */

    @Override
    protected UUID getId(Avaliacao avaliacao) {
        return avaliacao.getAvaliacaoID();
    }

    private static RepositorioAvaliacaoList instance;

    private RepositorioAvaliacaoList() {
        super();
    }

    //Instância do repositório
    public static RepositorioAvaliacaoList getInstance() {
        if (instance == null) {
            instance = RepositorioAvaliacaoList.lerArquivo();
        }
        return instance;
    }

    private static RepositorioAvaliacaoList lerArquivo() {
        RepositorioAvaliacaoList instanciaLocal;

        File in = new File("avaliacoes.dat");
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(in);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Object o = objectInputStream.readObject();
            instanciaLocal = (RepositorioAvaliacaoList) o;
        } catch (Exception e) {
            instanciaLocal = new RepositorioAvaliacaoList();
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
        File out = new File("avaliacoes.dat");
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
    public List<Avaliacao> procurarDono(Perfil dono) {
        List<Avaliacao> resultado = new ArrayList<>();
        for (Avaliacao avaliacao : objectList) {
            if (avaliacao.getPerfil().equals(dono)) {
                resultado.add(avaliacao);
            }
        }

        return resultado;
    }
}