package dominio.exceptions;

public class ElementoJaExisteException extends Exception {
    public ElementoJaExisteException() {
        System.out.println("Erro: Elemento já existente!");
    }
}
