package dominio;

public class Avaliacao {
    private int avaliacaoID;
    private int nota;
    private Perfil usuario;
    private Conteudo conteudo;

    public Avaliacao(int id, int nota, Perfil usuario, Conteudo conteudo) {
        this.avaliacaoID = id;
        this.nota = nota;
        this.usuario = usuario;
        this.conteudo = conteudo;
    }

    public void modificarNota(int nota){
        /**/
    }
}
