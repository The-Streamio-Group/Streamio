package dominio.exceptions;

public class AssinaturaNaoExpiradaException extends Exception {
    public AssinaturaNaoExpiradaException() {
        System.out.println("Erro: Assinatura em andamento ainda!");
    }
}
