package dominio.negocios.beans;

import java.time.LocalDate;

import java.util.UUID;

public class Assinatura {
    private final UUID assinaturaID;
    private boolean statusPagamento;
    private String numeroCartao;
    private LocalDate dataAssinatura;
    //Cálculo da expiração da Data

    public Assinatura() {
        this.assinaturaID = UUID.randomUUID();
        this.statusPagamento = false;
        this.numeroCartao = "0000-0000-0000-0000";
        //this.assinante = assinante;
    }

    public Assinatura(String numeroCartao) {
        this.assinaturaID = UUID.randomUUID();
        this.statusPagamento = true;
        this.numeroCartao = numeroCartao;
    }

    //Getters e setters
    public UUID getAssinaturaID() {
        return assinaturaID;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public boolean isStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDate getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(LocalDate dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

//    public Assinante getAssinante() {
//        return assinante;
//    }

    @Override
    public String toString() {
        return "Assinatura{" +
                "statusPagamento=" + statusPagamento +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", dataAssinatura=" + dataAssinatura;
    }

    // * statusPagamento torna-se false após 30 dias (LocalDate), sem que seja feita a renovação
    // * revisar LocalDate para que, quando a assinatura for feita, avançar no tempo para instanciar

}

