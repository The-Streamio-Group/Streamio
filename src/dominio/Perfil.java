package dominio;

import java.util.ArrayList;

public class Perfil {
    private int perfilID;
    private String nickname;
    private boolean tipo;
    private ArrayList<Conteudo> conteudosFavoritos;
    private ArrayList<Conteudo> watchHistory;

    public Perfil(int id, String nickname, boolean tipo, ArrayList<Conteudo> conteudosFavoritos, ArrayList<Conteudo> watchHistory) {
        this.perfilID = id;
        this.nickname = nickname;
        this.tipo = tipo;
        this.conteudosFavoritos = conteudosFavoritos;
        this.watchHistory = watchHistory;
    }

    public void editarPerfil (String nickname) {
        /**/
    }

    public void editarPerfil (boolean tipo) {
        /**/
    }

    public void adicionarFavorito(Conteudo conteudo) {
        /**/
    }

    public void retirarFavorito(Conteudo conteudo){
        /**/
    }

    public void retirarHistorico(Conteudo conteudo){
        /**/
    }
}
