package dominio.dados;

import dominio.dados.interfaces.IRepositorioConteudo;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.ElementoNullException;
// import ElementoJaExisteException para o método cadastrar [?]
import dominio.negocios.beans.Conteudo;
import dominio.negocios.beans.Perfil;

import java.io.Serializable;
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
            instance = new RepositorioConteudoList();
        }
        return instance;
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
