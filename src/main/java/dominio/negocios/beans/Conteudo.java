package dominio.negocios.beans;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.List;

public class Conteudo {

    private final UUID conteudoID;
    private String titulo;
    private String descricao;
    private TipoGenero genero;
    private String classificacaoIdade;
    private List<Avaliacao> avaliacoes;
    private int numeroViews;
    private float notaGeral;
    private Duration duracao; //50% do conteúdo - Avaliação
    private File path;

    public Conteudo(String titulo, String descricao, TipoGenero genero, String  classificacaoIdade, long minutos, File path) {
        this.conteudoID = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.genero = genero;
        this.classificacaoIdade = classificacaoIdade;
        this.numeroViews = 0;
        this.notaGeral = 0;
        this.avaliacoes = new ArrayList<>();
        this.duracao = Duration.ofMinutes(minutos);
        this.path = path;

    }

    //Getters e setters

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public void setNotaGeral(float notaGeral) {
        this.notaGeral = notaGeral;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    //Getters e Setters
    public UUID getConteudoID() {
        return conteudoID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoGenero getGenero() {
        return genero;
    }

    public void setGenero(TipoGenero genero) {
        this.genero = genero;
    }

    public String getClassificacaoIdade() {
        return classificacaoIdade;
    }

    public void setClassificacaoIdade(String classificacaoIdade) {
        this.classificacaoIdade = classificacaoIdade;
    }

    public int getNumeroViews() {
        return numeroViews;
    }

    public void setNumeroViews(int numeroViews) {
        this.numeroViews += numeroViews;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public float getNotaGeral() {
        return notaGeral;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    //Métodos específicos
    public void adicionarAvalicao(Avaliacao nova) {
        this.avaliacoes.add(nova);
    }

    public void removerAvalicao(Avaliacao a) {
        this.avaliacoes.remove(a);
    }

    public boolean possuiAvaliacao(Avaliacao a) {
        return this.avaliacoes.contains(a);
    }

    public int tamanhoAvaliacoes(){
        return this.avaliacoes.size();
    }

    public void atualizarNota() {
        int soma = 0;
        for (Avaliacao avaliacao : avaliacoes) {
            soma += avaliacao.getNota();
        }

        this.notaGeral = (float) soma / this.avaliacoes.size();

    }



    @Override
    public String toString() {
        return titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conteudo conteudo)) return false;
        return Objects.equals(getConteudoID(), conteudo.getConteudoID());
    }
}
