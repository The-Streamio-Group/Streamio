package dominio.exceptions;

public class SenhaErradaException extends Exception {
    public SenhaErradaException(String email) {
        System.out.printf("Erro: O usuário %s não possui esta senha!", email);
    }
}
