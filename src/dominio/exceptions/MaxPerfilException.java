package dominio.exceptions;

public class MaxPerfilException extends Exception {
    public MaxPerfilException() {
        System.out.println("Erro: Quantidade de Perfis já foi alcançada!");
    }
}
