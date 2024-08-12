package dominio.exceptions;

public class UsuarioNullException extends Exception {
    public UsuarioNullException() {
        System.out.println("Erro: Usuário inválido!");
    }
}
