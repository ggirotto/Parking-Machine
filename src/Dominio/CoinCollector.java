package Dominio;

import java.util.HashMap;

public class CoinCollector implements IPagamento{
    private double saldo;
    private final HashMap<EnumCoin, Integer> listaMoedas = new HashMap<>();

    @Override
    public void desconta(double valor) throws PagamentoException {
        
    }

    @Override
    public void deposita(double valor) {
        
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
