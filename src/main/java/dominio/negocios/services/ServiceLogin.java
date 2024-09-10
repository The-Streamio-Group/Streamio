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

    private final ControllerUsuario controllerUsuario;


    private ServiceLogin() {
        this.controllerUsuario = ControllerUsuario.getInstance();
    }

    public static ServiceLogin getInstance() {
        if (instance == null) {
            instance = new ServiceLogin();
        }
        return instance;
    }

    public Usuario realizarLogin(String email, String senha, Usuario usuariologado) throws ElementoNaoExisteException, UsuarioJaLogadoException, AssinaturaExpiradaException, SenhaErradaException {

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
            return userLog;
        } else {
            throw new SenhaErradaException(email);
        }
    }




}
