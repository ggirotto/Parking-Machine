package Dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CoinCollector implements IPagamento{
    private /*@spec_public@*/ double saldo;
    private /*@spec_public@*/ double saldoOperacao;
    private /*@ non_null @*/ final String tipo = "Pagamento em moedas";
    private /*@spec_public@*/ final HashMap<EnumCoin, Integer> listaMoedas = new HashMap<EnumCoin, Integer>();
    
    /*@ ensures saldo == 0;
      @ ensures saldoOperacao == 0;
    @*/
    public CoinCollector(){
        saldo = 0;
        saldoOperacao = 0;
    }
    
    /*@ requires valor == 0.05 ||  valor == 0.1 ||  valor == 0.25 ||  valor == 0.5 ||  valor == 1.0;
      @ requires listaMoedas != null;
      @ ensures getSaldo() == \old(getSaldo()-valor);
    @*/
    private void retiraMoeda(double valor) throws PagamentoException {
        
        EnumCoin enumerador = null;
        /*
            Busca um enumerador que possua valor igual à variável valor
        */
        for(EnumCoin entry : EnumCoin.values()){
            if(entry.getValor() == valor){
                enumerador = entry;
                break;
            }
        }
        
        /*
            Se não achou, não existe moeda com este valor
        */
        if(enumerador == null) throw new PagamentoException("Moeda inválida");
        
        /*
            Se achou porém ela não existe na lista de moedas, ela é válida
            porém não existe nenhuma moeda deste valor na máquina de moedas do
            parquimetro
        */
        if(listaMoedas.get(enumerador) == null) throw new PagamentoException("Moeda inexsistente na máquina");
        else
            /*
                Se achou e existe na lista, retira uma unidade dela
            */
            listaMoedas.put(enumerador, listaMoedas.get(enumerador)-1);
        /*
            Retira o valor dela do saldo da máquina e decrementa o número
            de moedas em 1
        */
        if(saldoOperacao > 0) saldoOperacao -= valor;
        else saldo -= valor;
    }
    
    /*
        Assim que o ticket é impresso e a operação finalizada, o saldoOperacao
        É acumulado no saldo total da máquina e depois zerado
    */
    /*@ ensures saldo == \old(getSaldo()) + saldoOperacao;
      @ ensures saldoOperacao == 0;
    @*/
    private void arrumaSaldo(){
        saldo += saldoOperacao;
        saldoOperacao = 0;
    }
    
    /*
        Verifica se o saldo da operação está de acordo
        com a valor que necessita ser pago
    */
    /*@ requires valorNecessario >= 0.75 && valorNecessario <= 3.0;
      @ ensures \result >= 0;
      @ signals (PagamentoException e) saldoOperacao < valorNecessario;
    @*/
    public double verificaValorPago(double valorNecessario) throws PagamentoException{
        double troco = 0;
        if(saldoOperacao < valorNecessario) throw new PagamentoException("Valor Pago insuficiente!");
        if(saldoOperacao > valorNecessario){
            troco = getTroco(saldoOperacao - valorNecessario);
        }
        arrumaSaldo();
        return troco;
    }
    
    /*@ requires valor == 0.05 ||  valor == 0.1 ||  valor == 0.25 ||  valor == 0.5 ||  valor == 1.0;
      @ requires listaMoedas != null;
      @ ensures saldoOperacao == \old(saldoOperacao)+valor;
    @*/
    public void insereMoeda(double valor) throws PagamentoException{
        
        EnumCoin enumerador = null;
        
        /*
            Busca um enumerador que possua valor igual à variável valor
        */
        for(EnumCoin entry : EnumCoin.values()){
            if(entry.getValor() == valor){
                enumerador = entry;
                break;
            }
        }
        
        /*
            Se não achou, não existe moeda com este valor
        */
        if(enumerador == null) throw new PagamentoException("Moeda inválida");
        
        /*
            Se achou porém ela não existe na lista de moedas, ela é válida
            porém não existe nenhuma moeda deste valor na máquina de moedas do
            parquimetro
        */
        if(listaMoedas.get(enumerador) == null)
            /*
                Adiciona uma moeda deste valor na máquina
            */
            listaMoedas.put(enumerador, 1);
        else
            /*
                Se achou e exsite na máquina, incrementa a quantidade desta
                moeda na máquian em 1
            */
            listaMoedas.put(enumerador, listaMoedas.get(enumerador)+1);
        
        /*
            Aumenta o saldo com o valor da moeda e incrementa o número de moedas.
        */
        saldoOperacao+=valor;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
    
    /*
        Retira o valor da variável -valor- da máquina de moedas.
        Começa retirando das maiores para as menores.
        Se a máquina não possui moedas suficientes, retira o maior
        valor possível menor que a variável -valor-
    */
    /*@ requires valor >= 0; @*/
    public double getTroco(double valor) throws PagamentoException {
        double troco = 0;
        
        List<EnumCoin> moedas = new ArrayList<EnumCoin>();
        
        moedas.addAll(Arrays.asList(EnumCoin.values()));
        Collections.reverse(moedas);

            for(EnumCoin coin : moedas){
                if(listaMoedas.get(coin) != null && listaMoedas.get(coin)>0 && valor>= coin.getValor()){
                    retiraMoeda(valor);
                    troco += 1;
                    valor -= 1;
                }
            }
        
        return troco;
    }

    @Override
    public String getTipo() {
        return tipo;
    }
    
    @Override
    public void zeraSaldo(){ 
        saldo = 0;
        saldoOperacao = 0;
        listaMoedas.clear();
    }
}