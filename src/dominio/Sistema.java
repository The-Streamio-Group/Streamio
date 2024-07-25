package dominio;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Usuario> usuariosDoSistema;
    private ArrayList<Conteudo> conteudosDoSistema;
    private ArrayList<Assinatura> assinaturasDoSistemas;

    public Sistema(ArrayList<Usuario> usuariosDoSistema, ArrayList<Conteudo> conteudosDoSistema, ArrayList<Assinatura> assinaturasDoSistemas) {
        this.usuariosDoSistema = usuariosDoSistema;
        this.conteudosDoSistema = conteudosDoSistema;
        this.assinaturasDoSistemas = assinaturasDoSistemas;
    }

    //Adcionar usuario e conteudo tem os ifs para verificar que o objeto adcionado não seja null e que eles não estejam já presentes na lista
    //Remover usuario e conteudo

    public void adicionarUsuario(Usuario novoUsuario) {
        if (novoUsuario != null && !usuariosDoSistema.contains(novoUsuario)) {
            usuariosDoSistema.add(novoUsuario);
        }
    };

    public void removerUsuario(Usuario usuario) {
        if (usuario != null && usuariosDoSistema.contains(usuario)) {
            usuariosDoSistema.remove(usuario);
        }
    };

    public void adicionarConteudo(Conteudo novoConteudo) {
        if (novoConteudo != null && !conteudosDoSistema.contains(novoConteudo)) {
            conteudosDoSistema.add(novoConteudo);
        }
    };

    public void removerConteudo(Conteudo conteudo) {
        if (conteudo != null && conteudosDoSistema.contains(conteudo)) {
            conteudosDoSistema.remove(conteudo);
        }

    };

}

}
