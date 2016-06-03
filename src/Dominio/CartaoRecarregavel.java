package Dominio;


public class CartaoRecarregavel implements IPagamento{
    private String identificacao;
    private double saldo;
    
    @Override
    public void desconta(double valor) throws PagamentoException{
        if(saldo<valor) throw new PagamentoException("Saldo Insuficiente");
        saldo -= valor;
    }

    @Override
    public void deposita(double valor) {
        saldo += valor;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public double getTroco(double valor) {
        return 0;
    }
}
