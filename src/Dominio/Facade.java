package Dominio;

import java.time.LocalDateTime;

public class Facade {
    private IPagamento tipoPagamento;
    private final Parquimetro parquimetro;
    private static LocalDateTime chegada;
    private static LocalDateTime saida;
    private CartaoRecarregavel cartao = null;
    private static Facade facade = null;
    
    public static Facade getInstance(LocalDateTime chegada,LocalDateTime saida){
        if(facade == null) facade = new Facade(chegada, saida);
        Facade.chegada = chegada;
        Facade.saida = saida;
        return facade;        
    }
    
    private Facade(LocalDateTime chegada,LocalDateTime saida){
        this.chegada = chegada;
        this.saida = saida;
        parquimetro = new Parquimetro();
        tipoPagamento = parquimetro.getCoinCollector();
    }
    
    public void cartaoInserido(CartaoRecarregavel card){
        cartao = card;
        tipoPagamento = card;
    }
    /*
        Gera e imprime o ticket
    */
    public Ticket geraTicket(double valorPago) throws ParquimetroException, PagamentoException, TicketException{
        parquimetro.registraPagamento(chegada, saida, valorPago, tipoPagamento);
        return parquimetro.geraTicket(chegada, saida,tipoPagamento,valorPago);
    }
    
    /*
        Gera o log do parquimetro com todas as informações necessárias
    */
    public void geraLogParquimetro() throws ParquimetroException{
        parquimetro.geraLogParquimetro();
    }
    
    public void insereMoeda(double valor) throws PagamentoException{
        parquimetro.inserirMoeda(valor);
    }
    
    public double getSaldoMaquina(){
        return parquimetro.getCoinCollector().getSaldo();
    }
}
