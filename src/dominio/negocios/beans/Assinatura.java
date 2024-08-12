package dominio.negocios.beans;

import dominio.exceptions.CartaoNullException;

public class Assinatura {
    private String assinaturaID; //Exemplo de Ideia pra facilitar a generalização do projeto
    private String tipo; //Pode ser retirado
    private boolean statusPagamento;
    private String numeroCartao;

    public Assinatura(int assinaturaID, String tipo, boolean statusPagamento, String numeroCartao) throws CartaoNullException {
        if(numeroCartao == null){throw new CartaoNullException(); }
        this.assinaturaID = "AS" + assinaturaID;
        this.tipo = tipo; //Pode ser retirado
        this.statusPagamento = statusPagamento;
        this.numeroCartao = numeroCartao;
    }

    // getters e setters
    public String getAssinaturaID() {
        return assinaturaID;
    }
    public void setAssinaturaID(String assinaturaID) {
        this.assinaturaID = assinaturaID;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean isStatusPagamento() {
        return statusPagamento;
    }
    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
    public String getNumeroCartao() {
        return numeroCartao;
    }
    public void setNumeroCartao(String cartaoUsuario) {
        this.numeroCartao = cartaoUsuario;
    }

}

