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

public class RepositorioAvaliacaoList extends RepositorioGenericoList<Avaliacao> implements IRepositorioAvaliacao {

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
            instance = new RepositorioAvaliacaoList();
        }
        return instance;
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