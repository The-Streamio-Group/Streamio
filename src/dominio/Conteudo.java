package dominio;
public class Conteudo {

    private int conteudoID;
    private String titulo;
    private int anoLancamento;
    private String descricao;
    private TipoGenero genero;
    private int classificacaoIdade;
    private int numeroViews;

    public Conteudo(int conteudoID, String titulo, int anoLancamento, String descricao, TipoGenero genero, int classificacaoIdade, int numeroViews) {
        this.conteudoID = conteudoID;
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.descricao = descricao;
        this.genero = genero;
        this.classificacaoIdade = classificacaoIdade;
        this.numeroViews = numeroViews;
    }

    // getters e setters
    public int getConteudoID() {
        return conteudoID;
    }
    public void setConteudoID(int conteudoID) {
        this.conteudoID = conteudoID;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
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
    public int getClassificacaoIdade() {
        return classificacaoIdade;
    }
    public void setClassificacaoIdade(int classificacaoIdade) {
        this.classificacaoIdade = classificacaoIdade;
    }
    public int getNumeroViews() {
        return numeroViews;
    }
    public void setNumeroViews(int numeroViews) {
        this.numeroViews = numeroViews;
    }

    // Faltam métodos
}
