package dominio.exceptions;

public class PerfilNaoPertencenteException extends Exception {
    public PerfilNaoPertencenteException() {
        System.out.println("Erro: Perfil não pertencente à Assinante!");
    }
}
