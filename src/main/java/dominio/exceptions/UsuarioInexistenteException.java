package dominio.exceptions;

public class UsuarioInexistenteException extends Exception {
    public UsuarioInexistenteException() {
        System.out.println("Erro: Usuário já está logado!");
    }
}
