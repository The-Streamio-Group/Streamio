package dominio.negocios.services;

import dominio.exceptions.AssinaturaExpiradaException;
import dominio.exceptions.ElementoNaoExisteException;
import dominio.exceptions.SenhaErradaException;
import dominio.exceptions.UsuarioJaLogadoException;
import dominio.negocios.ControllerUsuario;
import dominio.negocios.beans.Assinante;
import dominio.negocios.beans.Perfil;
import dominio.negocios.beans.Usuario;

public class ServiceLogin {
    private static ServiceLogin instance;

    private ControllerUsuario controllerUsuario;
    private Usuario usuariologado; //Instância do usuário logado
    private Perfil perfilLogado;  //Instância do Perfil logado

    private ServiceLogin() {
        this.controllerUsuario = ControllerUsuario.getInstance();
    }

    public static ServiceLogin getInstance() {
        if (instance == null) {
            instance = new ServiceLogin();
        }
        return instance;
    }

    public void realizarLogin(String email, String senha) throws ElementoNaoExisteException, UsuarioJaLogadoException, AssinaturaExpiradaException, SenhaErradaException {

        //Verificar se o usuário já está logado
        if (usuariologado != null) {
            throw new UsuarioJaLogadoException();
        }

        Usuario userLog = this.controllerUsuario.procurarUsuarioPorEmail(email);


        //Verificar se a senha bate


        if (userLog instanceof Assinante) {
            //Caso a assinatura expire
            if (((Assinante) userLog).getAssinatura().estaExpirada()) {
                throw new AssinaturaExpiradaException();
            }

        }
        if (userLog.getSenha().equals(senha)) {
            this.usuariologado = userLog;
        } else {
            throw new SenhaErradaException(email);
        }
    }

    public void logoff() {
        this.usuariologado = null;
        this.perfilLogado = null;
    }



}
