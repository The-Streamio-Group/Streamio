package dominio.negocios.beans;

public class Filme extends Conteudo{

    public Filme(String titulo, String descricao, TipoGenero genero, int classificacaoIdade, long minutos) {
        super(titulo, descricao, genero, classificacaoIdade, minutos);
    }
}
