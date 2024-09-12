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

public class RepositorioReproducaoConteudoList extends RepositorioGenericoList<ReproducaoConteudo> implements IRepositorioReproducaoConteudo {

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
            instance = new RepositorioReproducaoConteudoList();
        }
        return instance;
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
