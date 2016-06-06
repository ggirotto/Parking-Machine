package Dominio;


public class CartaoRecarregavel implements IPagamento{
    private final String tipo = "Cartão recarregável";
    private final String identificacao;
    private double saldo;
    
    public CartaoRecarregavel(String id){
        identificacao = id;
    }
    
    @Override
    public void desconta(double valor) throws PagamentoException{
        if(saldo<valor) throw new PagamentoException("Saldo Insuficiente");
        saldo -= valor;
    }

    @Override
    public void deposita(double valor) throws PagamentoException{
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

    @Override
    public String getTipo() {
        return tipo;
    }
}
