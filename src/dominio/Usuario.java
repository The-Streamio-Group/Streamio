package dominio;
import java.util.ArrayList;

public class Usuario {
    private String id;
    private String email;
    private String senha;
    private Assinatura assinatura;
    private ArrayList<Perfil> perfis;


    public Usuario(String id, String email, String senha, Assinatura assinatura, ArrayList<Perfil> perfis) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.assinatura = assinatura;
        this.perfis = perfis;
    }

    public void adicionarPerfil (Perfil novoPerfil) {
        /**/
    }

    public void removerPerfil (Perfil novoPerfil) {
        /**/
    }

}
