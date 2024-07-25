package dominio;
public class Avaliacao {
    private int avaliacaoID;
    private int nota;
    private Perfil perfil;
    private Conteudo conteudo;

    public Avaliacao(int avaliacaoID, int nota, Perfil perfil, Conteudo conteudo) {
        this.avaliacaoID = avaliacaoID;
        this.nota = nota;
        this.perfil = perfil;
        this.conteudo = conteudo;
    }

    // getters e setters
    public int getAvaliacaoID() {
        return avaliacaoID;
    }
    public void setAvaliacaoID(int avaliacaoID) {
        this.avaliacaoID = avaliacaoID;
    }
    public int getNota() {
        return nota;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }
    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public Conteudo getConteudo() {
        return conteudo;
    }
    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

}
