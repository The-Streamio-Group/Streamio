package dominio.negocios.beans;

public class Serie extends Conteudo{
    public Serie(String titulo, String descricao, TipoGenero genero, int classificacaoIdade, long minutos) {
        super(titulo, descricao, genero, classificacaoIdade, minutos);
    }
}
