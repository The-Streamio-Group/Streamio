package dominio;import java.util.ArrayList;


public class Perfil {

    private int perfilID;
    private String nickname;
    private boolean tipo;
    ArrayList<Conteudo> watchHistory;
    ArrayList<Conteudo> conteudosFavoritos;

    public Perfil(int PerfilID, String Nickname, boolean tipo) {
        this.perfilID = PerfilID;
        this.nickname = Nickname;
        this.tipo = tipo;
        this.watchHistory = new ArrayList<>();
        this.conteudosFavoritos = new ArrayList<>();
    }

    // getters e setters
    public int getPerfilID() {
        return perfilID;
    }
    public void setPerfilID(int perfilID) {
        perfilID = perfilID;
    }
    public String getNickname() {
        return nickname;
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
    public ArrayList<Conteudo> getWatchHistory() {
        return watchHistory;
    }
    public void setWatchHistory(ArrayList<Conteudo> watchHistory) {
        watchHistory = watchHistory;
    }
    public ArrayList<Conteudo> getConteudosFavoritos() {
        return conteudosFavoritos;
    }
    public void setConteudosFavoritos(ArrayList<Conteudo> conteudosFavoritos) {
        conteudosFavoritos = conteudosFavoritos;
    }

    // métodos específicos
    public void EditarPerfil (String nickname){
        if (nickname != null && !nickname.isEmpty()){
            this.nickname = nickname;
        }
    }

    public void EditarPerfil (boolean tipo){
        this.tipo = tipo;
    }

    public void adicionarFavorito (Conteudo conteudo){
        if (!conteudosFavoritos.contains(conteudo)); {
            conteudosFavoritos.add(conteudo);
        }
    }

    public void removerFavorito (Conteudo conteudo){
        if (conteudo != null && conteudosFavoritos.contains(conteudo)) {
            conteudosFavoritos.remove(conteudo);
        }
    }

    public void retirarHistorico (Conteudo conteudo){
        if (conteudo != null && watchHistory.contains (conteudo) ) {
            watchHistory.remove(conteudo);
        }
    }

}
