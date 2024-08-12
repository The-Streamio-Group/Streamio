package dominio.exceptions;

public class UsuarioJaExisteException extends Exception{
    public UsuarioJaExisteException() {
        System.out.println("Erro: O usuário já existe!");
    }
}
