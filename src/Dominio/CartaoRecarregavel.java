package Dominio;

public class CartaoRecarregavel implements IPagamento{
    private final String identificacao;
    private double saldo;
    private final String tipo = "Cartao recarregavel";
    
    public CartaoRecarregavel(String id) throws PagamentoException{
        if(id.length() != 120){
            throw new PagamentoException("O ID do cartão deve possuir 128 caracteres");
        }
        identificacao = id;
    }
    
    public void desconta(double valor) throws PagamentoException{
        if(saldo<valor) throw new PagamentoException("Saldo Insuficiente");
        saldo -= valor;
    }

    public void deposita(double valor) throws PagamentoException{
        saldo += valor;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
    
    public String getId(){ return identificacao; }
    
    @Override
    public String getTipo() {
        return tipo;
    }
    
    @Override
    public void zeraSaldo(){ saldo = 0; }
}
