package dominio.exceptions;

public class PerfilNullException extends Exception{
    public PerfilNullException() {
        System.out.println("Erro: Perfil inválido!");
    }
}
