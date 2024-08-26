package dominio.exceptions;

public class UsuarioJaLogadoException extends Exception {
    public UsuarioJaLogadoException() {
        System.out.println("Erro: Usuário já está logado!");
    }
}
