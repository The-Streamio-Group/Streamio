package dominio.negocios.beans;

import dominio.exceptions.UsuarioNullException;

import java.util.*;

public class Playlist {

    private List<Conteudo> playlist = new ArrayList<>();
    private Usuario usuario;

    public Playlist(Usuario usuario,String nome) throws UsuarioNullException {
        if(usuario == null){ throw new UsuarioNullException();}
        this.usuario = usuario;

    }


    public List<Conteudo> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Conteudo> playlist) {
        this.playlist = playlist;
    }

    public Usuario getPerfil() {
        return usuario;
    }

    public void setPerfil(Usuario perfil) {
        this.usuario = perfil;
    }
}
