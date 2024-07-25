package dominio;

import java.util.ArrayList;

public class BarraPesquisa {

    private ArrayList<Conteudo> conteudos;

    public BarraPesquisa() {
        this.conteudos = new ArrayList<>();
    }

    //Pesquisa básica usando nome
    public ArrayList<Conteudo> pesquisarPorNome (String nome) {
        ArrayList<Conteudo> filmesEncontrados = new ArrayList<>();
        for (Conteudo conteudo : conteudos) {
            if (conteudo.getTitulo().toLowerCase().contains(nome.toLowerCase())) {
                filmesEncontrados.add(conteudo);
            }
        }
        return filmesEncontrados;
    }

    //Pesquisa básica usando gênero
    public ArrayList<Conteudo> pesquisarPorGenero (TipoGenero genero) {
        ArrayList<Conteudo> filmesEncontrados = new ArrayList<>();
        for (Conteudo conteudo : conteudos) {
            if (conteudo.getGenero() == genero) {
                filmesEncontrados.add(conteudo);
            }
        }
        return filmesEncontrados;
    }

    //Pesquisa básica usando faxa etária
    public ArrayList<Conteudo> pesquisarPorIdade (int idade) {
        ArrayList<Conteudo> filmesEncontrados = new ArrayList<>();
        for (Conteudo conteudo : conteudos) {
            //Esse if só adiciona como idade exata. Depois reimplementar.
            if (conteudo.getClassificacaoIdade() == idade) {
                filmesEncontrados.add(conteudo);
            }
        }

        return filmesEncontrados;
    }


}
