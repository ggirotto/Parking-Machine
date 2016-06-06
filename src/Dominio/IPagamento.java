package Dominio;

public interface IPagamento {
    
    public void desconta(double valor) throws PagamentoException;
    public void deposita(double valor) throws PagamentoException;
    public double getSaldo();
    public double getTroco(double valor) throws PagamentoException;
    public String getTipo();
    
}
