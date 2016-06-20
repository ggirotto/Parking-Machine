package Dominio;

public class CartaoRecarregavel implements IPagamento{
    private /*@ spec_public @*/ /*@ non_null @*/ final String identificacao;
    private /*@ spec_public @*/ double saldo;
    private /*@ spec_public @*/ /*@ non_null @*/ final String tipo = "Cartao recarregavel";
    
    /*@ requires id != null;
      @ signals (PagamentoException e) id.length() != 128;
      @ ensures identificacao.length() == 128;
    @*/
    public CartaoRecarregavel(String id) throws PagamentoException{
        if(id.length() != 128){
            throw new PagamentoException("O ID do cartão deve possuir 128 caracteres");
        }
        identificacao = id;
    }
    
    /*@ requires valor > 0;
      @ requires saldo > 0;
      @ ensures saldo < \old(saldo);
      @ signals (PagamentoException e) saldo < valor;
    @*/
    public void desconta(double valor) throws PagamentoException{
        if(saldo<valor) throw new PagamentoException("Saldo Insuficiente");
        saldo -= valor;
    }
    
    /*@ requires valor > 0;
      @ ensures saldo > \old(saldo);
    @*/
    public void deposita(double valor) {
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