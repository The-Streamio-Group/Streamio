package dominio.exceptions;



public class NaoAssinanteException extends Exception {
    public NaoAssinanteException() {
        System.out.println("Erro: Não é um assinante!");
    }
}
