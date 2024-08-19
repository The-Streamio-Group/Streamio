package dominio.negocios.beans;

import java.time.LocalDate;

import java.util.UUID;

public class Assinatura {
    private final UUID assinaturaID;
    private boolean statusPagamento;
    private String numeroCartao;
    private LocalDate dataAssinatura;
    private LocalDate dataExpiracao;

    //Assinatura sem estar sendo paga
    public Assinatura() {
        this.assinaturaID = UUID.randomUUID();
        this.statusPagamento = false;
        this.numeroCartao = "0000-0000-0000-0000";

    }

    //Assinatura já feita com um cartão
    public Assinatura(String numeroCartao) {
        this.assinaturaID = UUID.randomUUID();
        this.statusPagamento = true;
        this.numeroCartao = numeroCartao;
        this.dataAssinatura = LocalDate.now();
        this.dataExpiracao = dataAssinatura.plusDays(30);
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

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    @Override
    public String toString() {
        return "Assinatura{" +
                "statusPagamento=" + statusPagamento +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", dataAssinatura=" + dataAssinatura;
    }

    // * statusPagamento torna-se false após 30 dias (LocalDate), sem que seja feita a renovação
    // * revisar LocalDate para que, quando a assinatura for feita, avançar no tempo para instanciar


    //Métodos específicos
    public boolean estaExpirada(){
        boolean TF = LocalDate.now().isAfter(dataExpiracao);
        if(TF) {
            this.setStatusPagamento(false);
        }
        return TF;
    }
}

