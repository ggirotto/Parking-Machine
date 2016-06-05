package Dominio;

import java.util.HashMap;
import java.util.Map;

public class CoinCollector implements IPagamento{
    private double saldo;
    private final HashMap<EnumCoin, Integer> listaMoedas = new HashMap<>();

    @Override
    public void desconta(double valor) throws PagamentoException {
        for(Map.Entry<EnumCoin, Integer> entry : listaMoedas.entrySet()){
            if(entry.getValue() == valor){
                listaMoedas.put(entry.getKey(), entry.getValue()-1 );
                break;
            }
        }
    }

    @Override
    public void deposita(double valor) {
        saldo+=valor;
        listaMoedas.put(EnumCoin.valueOf(valor+""), listaMoedas.get(EnumCoin.valueOf(valor+""))+1);
        
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
    
    //TODO
    @Override
    public double getTroco(double valor) {
        return 0;
    }

}
