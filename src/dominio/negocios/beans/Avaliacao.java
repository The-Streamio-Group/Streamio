package dominio.negocios.beans;

import dominio.exceptions.ConteudoNullException;
import dominio.exceptions.UsuarioNullException;

public class Avaliacao {
    private int avaliacaoID;
    private int nota;
    private Usuario usuario;
    private Conteudo conteudo;

    public Avaliacao(int avaliacaoID, int nota, Usuario usuario, Conteudo conteudo)  throws UsuarioNullException, ConteudoNullException {
        if(usuario == null){throw new UsuarioNullException();}
        if(conteudo == null){throw new ConteudoNullException();}
        this.avaliacaoID = avaliacaoID;
        this.nota = nota;
        this.usuario = usuario;
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
    public Usuario getPerfil() {
        return usuario;
    }
    public void setPerfil(Usuario perfil) {
        this.usuario = perfil;
    }
    public Conteudo getConteudo() {
        return conteudo;
    }
    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

}
