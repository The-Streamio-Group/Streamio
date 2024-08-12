package dominio.negocios.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usuario {
    private String usuarioID;
    private Assinatura assinatura;
    private String email;
    private String senha;

    public Usuario(Assinatura assinatura, String email, String number) {
        this.assinatura = assinatura;
        this.email = email;
        this.senha = number;
    }

    // getters e setters

    public String getID() { return usuarioID; };
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

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }




}
