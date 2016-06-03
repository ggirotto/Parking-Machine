package Dominio;

public interface IPagamento {
    
    public void desconta(double valor) throws PagamentoException;
    public void deposita(double valor);
    public double getSaldo();
    public double getTroco(double valor) throws PagamentoException;
    
}
