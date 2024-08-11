package dominio.exceptions;

public class CartaoNullException extends Exception {
    public CartaoNullException() {
        System.out.println("Erro: Cartão Inválido");
    }
}
