package dominio.exceptions;

public class TempoInsuficienteException extends Exception {
    public TempoInsuficienteException() {
        System.out.println("Erro: Tempo Insuficiente para fazer a avaliação!");
    }
}
