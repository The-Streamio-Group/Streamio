package dominio.negocios.beans;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Usuario implements Serializable {
    private final UUID usuarioID;
    private String nickname;
    private String email;
    private String senha;

    public Usuario() {
        usuarioID = UUID.randomUUID();
    }

    public Usuario(String nickname, String email, String senha) {
        this();
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
    }

    //Equals e toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getUsuarioID(), usuario.getUsuarioID()) && Objects.equals(getEmail(), usuario.getEmail());
    }

    @Override
    public String toString() {
        return "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'';
    }

    //Getters e Setters

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
