package dominio.negocios.beans;

import dominio.exceptions.UsuarioNullException;

import java.util.*;



public class Perfil {

    private int perfilID;
    private String nickname;
    private boolean tipo;
    private Usuario usuario;
    private Playlist watchHistory;
    private Playlist conteudosFavoritos;

    public Perfil(int PerfilID, String nickname, boolean tipo, Usuario usuario) throws UsuarioNullException {
        if(usuario == null){
            throw new UsuarioNullException();
        }
        this.perfilID = PerfilID;
        this.nickname = nickname;
        this.tipo = tipo;

    }

    // getters e setters
    public int getPerfilID() {
        return perfilID;
    }
    public void setPerfilID(int perfilID) {
        this.perfilID = perfilID;
    }
    public String getNickname() {
        return nickname;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public boolean isTipo() {
        return tipo;
    }
    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Playlist getWatchHistory() {
        return watchHistory;
    }

    public void setWatchHistory(Playlist watchHistory) {
        this.watchHistory = watchHistory;
    }

    public Playlist getConteudosFavoritos() {
        return conteudosFavoritos;
    }

    public void setConteudosFavoritos(Playlist conteudosFavoritos) {
        this.conteudosFavoritos = conteudosFavoritos;
    }
}
