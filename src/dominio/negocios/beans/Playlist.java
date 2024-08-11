package dominio.negocios.beans;

import dominio.exceptions.PerfilNullException;

import java.util.*;

public class Playlist {

    private List<Conteudo> playlist = new ArrayList<>();
    private Perfil perfil;

    public Playlist(Perfil perfil,String nome) throws PerfilNullException {
        if(perfil == null){ throw new PerfilNullException();}
        this.perfil = perfil;

    }


    public List<Conteudo> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Conteudo> playlist) {
        this.playlist = playlist;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
