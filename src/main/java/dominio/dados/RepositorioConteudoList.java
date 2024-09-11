package dominio.dados;

import dominio.dados.interfaces.IRepositorioConteudo;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Perfil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositorioConteudoList extends RepositorioGenericoList<Conteudo> implements IRepositorioConteudo, Serializable {

    /*
     * Classe que contém o repositório de todos os Conteúdos
     * e seus respectivos CRUDs.
     */

    protected UUID getId(Conteudo conteudo) {
        return conteudo.getConteudoID();
    }

    private static RepositorioConteudoList instance;

    private RepositorioConteudoList() {
        super();
    }

    //Instância do repositório
    public static RepositorioConteudoList getInstance() {
        if (instance == null) {
            instance = RepositorioConteudoList.lerArquivo();
        }
        return instance;
    }

    private static RepositorioConteudoList lerArquivo() {
        RepositorioConteudoList instanciaLocal;

        File in = new File("conteudo.dat");
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(in);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Object o = objectInputStream.readObject();
            instanciaLocal = (RepositorioConteudoList) o;
        } catch (Exception e) {
            instanciaLocal = new RepositorioConteudoList();
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
        File out = new File("conteudo.dat");
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
    public List<Conteudo> procurarPorTitulo(String titulo){
        List<Conteudo> encontrados = new ArrayList<>();

        for (Conteudo conteudo : objectList) {
            if (conteudo.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                encontrados.add(conteudo);
            }
        }
        return encontrados;
    }
}
