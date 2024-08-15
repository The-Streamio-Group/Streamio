package dominio.exceptions;

public class AssinaturaExpiradaException extends Exception {
    public AssinaturaExpiradaException() {
        System.out.println("Erro: Assinatura Expirada!");
    }
}
