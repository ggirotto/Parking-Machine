package Dominio;

import java.util.Random;

public class CartaoRecarregavel implements IPagamento{
    private String identificacao = "";
    private double saldo;
    private final String tipo = "Cartao recarregavel";
    
    public CartaoRecarregavel(){
        /*
            Cria uma identificacao aleatória de 128 caracteres para o cartão
        */
        Random randomGenerator = new Random();
        for(int i=0; i<128; i++)
            identificacao += randomGenerator.nextInt(9);
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
