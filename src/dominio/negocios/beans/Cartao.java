package dominio.negocios.beans;

import dominio.exceptions.UsuarioNullException;

import java.time.LocalDate;
public class Cartao {

    private String numero;
    private String validade; //Iremos mudar para qual classe?
    private String titular;
    private Usuario usuario;

    public Cartao(String numero, String validade, String titular, Usuario usuario) throws UsuarioNullException {
        if(usuario == null){
            throw new UsuarioNullException();
        }
        this.numero = numero;
        this.validade = validade;
        this.titular = titular;
    }

    // Getters e setters


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean validarCartao() {
        return validarNumeroCartao() && validarDataValidade();
    }

    //Validando o número do cartão
    private boolean validarNumeroCartao() {
        return true;
    }

    //Validando a data de validade
    private boolean validarDataValidade() {

        //Verificar se a data foi informada de modo correto
        if (validade.length() != 5 || validade.charAt(2) != '/') {
            return false;
        }

        //Verificar se os valores de data são possíveis
        String[] partesData = validade.split("/");
        int mesData = Integer.parseInt(partesData[0]);
        int anoData = Integer.parseInt(partesData[1]);

        if (mesData < 1 || mesData > 12) {
            return false;
        }

        //Checando o ano e a validade
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataValidade = LocalDate.of(anoData, mesData, 1).withDayOfMonth(LocalDate.of(anoData, mesData, 1).lengthOfMonth());

        if (dataValidade.isBefore(dataAtual) || dataValidade.isEqual(dataAtual)) {
            return false;
        }


        return true;
    }


}
