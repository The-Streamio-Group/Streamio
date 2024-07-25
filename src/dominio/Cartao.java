package dominio;

import java.time.LocalDate;
public class Cartao {

    private String numero;
    private String validade;
    private String cvv;
    private String titular;

    public Cartao(String numero, String validade, String cvv, String titular) {
        this.numero = numero;
        this.validade = validade;
        this.cvv = cvv;
        this.titular = titular;
    }

    // Getters e setters


    public boolean validarCartao() {
        return validarNumeroCartao() && validarDataValidade() && validarCVV();
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

    //Para a verificação do CVV, só precisamos confirmar que ele tenha 3, e somente 3, números
    private boolean validarCVV() {
        return cvv.length() == 3 && cvv.chars().allMatch(Character::isDigit);
    }
}
