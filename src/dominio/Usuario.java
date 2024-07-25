package dominio;

import java.util.ArrayList;

public class Usuario {
    private Assinatura assinatura;
    private String email;
    private String senha;
    private ArrayList<Perfil> perfis;

    public Usuario(Assinatura assinatura, boolean tipoPerfil, String email, String number) {
        this.assinatura = assinatura;
        this.email = email;
        this.senha = number;
        perfis = new ArrayList<>();
    }

    // getters e setters
    public Assinatura getAssinatura() {
        return assinatura;
    }
    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public ArrayList<Perfil> getPerfis() {
        return perfis;
    }
    public void setPerfis(ArrayList<Perfil> perfis) {
        this.perfis = perfis;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // métodos específicos
    public void adicionarPerfil(Perfil perfil) {
        perfis.add(perfil);
    };

    public void removerPerfil(Perfil perfil) {
        perfis.remove(perfil);
    }

    public void renovarAssinatura() {

    }

    // O cadastro vai ter que ser via construtor no momento
}
