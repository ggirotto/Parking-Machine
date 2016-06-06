package Dominio;

import java.util.Random;

public class CartaoRecarregavel implements IPagamento{
    private final String tipo = "Cartão recarregável";
    private String identificacao = "";
    private double saldo;
    
    public CartaoRecarregavel(){
        Random randomGenerator = new Random();
        for(int i=0; i<128; i++)
            identificacao += randomGenerator.nextInt(9);
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
    
    public String getId(){ return identificacao; }

    @Override
    public double getTroco(double valor) {
        return 0;
    }

    @Override
    public String getTipo() {
        return tipo;
    }
}
