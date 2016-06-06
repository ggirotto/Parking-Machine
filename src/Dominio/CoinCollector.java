package Dominio;

import java.util.HashMap;

public class CoinCollector implements IPagamento{
    private double saldo;
    private final String tipo = "Pagamento em moedas";
    private final HashMap<EnumCoin, Integer> listaMoedas = new HashMap<>();

    @Override
    public void desconta(double valor) throws PagamentoException {
        
    }

    @Override
    public void deposita(double valor) throws PagamentoException{
        saldo+=valor;
        EnumCoin enumerador = null;
        for(EnumCoin entry : EnumCoin.values()){
            if(entry.getValue() == valor){
                enumerador = entry;
                break;
            }
        }
        if(enumerador == null) throw new PagamentoException("Moeda inválida");
        if(listaMoedas.get(enumerador) == null)
            listaMoedas.put(enumerador, 1);
        else
            listaMoedas.put(enumerador, listaMoedas.get(enumerador)+1);
        
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
    
    //TODO
    @Override
    public double getTroco(double valor) throws PagamentoException {
        
        EnumCoin aux = EnumCoin.UMREAL;
        double troco = 0;
        
        while(valor>0){
            if(listaMoedas.get(aux) != null && listaMoedas.get(aux)>0 && valor>= 1){
                troco += 1;
                valor -= 1;
                continue;
            }
            else aux = EnumCoin.CINQUENTA;
            if(listaMoedas.get(aux) != null && listaMoedas.get(aux)>0 && valor>= 0.5){
                troco += 0.5;
                valor -= 0.5;
                continue;
            }
            else aux = EnumCoin.VINTECINCO;
            if(listaMoedas.get(aux) != null && listaMoedas.get(aux)>0 && valor>= 0.25){
                troco += 0.25;
                valor -= 0.25;
                continue;
            }
            else aux = EnumCoin.DEZ;
            if(listaMoedas.get(aux) != null && listaMoedas.get(aux)>0 && valor>= 0.1){
                troco += 0.1;
                valor -= 0.1;
                continue;
            }
            else aux = EnumCoin.CINCO;
            if(listaMoedas.get(aux) != null && listaMoedas.get(aux)>0 && valor>= 0.05){
                troco += 0.05;
                valor -= 0.05;
                continue;
            }
            if(valor>0) break;
        }
        
        if(valor>0) throw new PagamentoException("Moedas Insuficientes");
        return troco;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

}
