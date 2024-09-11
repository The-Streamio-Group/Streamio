package dominio.dados;

import dominio.dados.interfaces.IRepositorioReproducaoConteudo;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Perfil;
import dominio.negocios.beans.ReproducaoConteudo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositorioReproducaoConteudoList extends RepositorioGenericoList<ReproducaoConteudo> implements IRepositorioReproducaoConteudo, Serializable {

    private static RepositorioReproducaoConteudoList instance;

    //Implementação do getId específico do repositório
    @Override
    protected UUID getId(ReproducaoConteudo reproducao) {
        return reproducao.getReprodutoraConteudoID();
    }

    private RepositorioReproducaoConteudoList() {
        super();
    }

    //Instância do repositório
    public static RepositorioReproducaoConteudoList getInstance() {
        if (instance == null) {
            instance = RepositorioReproducaoConteudoList.lerArquivo();
        }
        return instance;
    }

    private static RepositorioReproducaoConteudoList lerArquivo() {
        RepositorioReproducaoConteudoList instanciaLocal;

        File in = new File("reproducaoConteudos.dat");
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(in);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Object o = objectInputStream.readObject();
            instanciaLocal = (RepositorioReproducaoConteudoList) o;
        } catch (Exception e) {
            instanciaLocal = new RepositorioReproducaoConteudoList();
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
        File out = new File("reproducaoConteudo.dat");
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
    public List<ReproducaoConteudo> procurarDono(Perfil dono) {
        List<ReproducaoConteudo> resultado = new ArrayList<>();
        for (ReproducaoConteudo reproducaoConteudo : objectList) {
            if (reproducaoConteudo.getPerfil().equals(dono)) {
                resultado.add(reproducaoConteudo);
            }
        }
        return resultado;
    }

    @Override
    public List<ReproducaoConteudo> procurarConteudo(Conteudo conteudo) {
        List<ReproducaoConteudo> resultado = new ArrayList<>();
        for (ReproducaoConteudo reproducaoConteudo : objectList) {
            if (reproducaoConteudo.getPerfil().equals(conteudo)) {
                resultado.add(reproducaoConteudo);
            }
        }
        return resultado;
    }

    public void removerConteudoRelacionado(Conteudo conteudo){
        objectList.removeIf(reproducaoConteudo -> reproducaoConteudo.getConteudo().equals(conteudo));
    }
}
