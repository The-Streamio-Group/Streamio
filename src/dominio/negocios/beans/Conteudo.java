package dominio.negocios.beans;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.List;

public class Conteudo {

    private final UUID conteudoID;
    private String titulo;
    private String descricao;
    private TipoGenero genero;
    private int classificacaoIdade;
    private List<Avaliacao> avaliacoes; //Melhor relacionamento
    private int numeroViews;
    private float notaGeral;

    public Conteudo(String titulo, String descricao, TipoGenero genero, int classificacaoIdade) {
        this.conteudoID = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.genero = genero;
        this.classificacaoIdade = classificacaoIdade;
        this.numeroViews = 0;
        this.notaGeral = 0;
        this.avaliacoes = new ArrayList<>();
    }

    //Getters e setters
    public String getConteudoID() { return conteudoID.toString(); }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo;}

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public TipoGenero getGenero() { return genero; }
    public void setGenero(TipoGenero genero) { this.genero = genero; }

    public int getClassificacaoIdade() { return classificacaoIdade; }
    public void setClassificacaoIdade(int classificacaoIdade) { this.classificacaoIdade = classificacaoIdade; }

    public int getNumeroViews() { return numeroViews; }
    public void setNumeroViews(int numeroViews) { this.numeroViews += numeroViews; }


    public void atualizarNota() {
        int soma = 0;
        for(Avaliacao avaliacao : avaliacoes) {
            soma += avaliacao.getNota();
        }

            this.notaGeral = (float) soma / this.avaliacoes.size();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conteudo conteudo)) return false;
        return Objects.equals(getConteudoID(), conteudo.getConteudoID());
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public float getNotaGeral() {
        return notaGeral;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
