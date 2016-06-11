package Dominio;

import java.time.LocalDateTime;

public class Facade {
    private IPagamento tipoPagamento;
    private final Parquimetro parquimetro;
    private final LocalDateTime chegada;
    private final LocalDateTime saida;
    private CartaoRecarregavel cartao = null;
    
    public Facade(LocalDateTime chegada,LocalDateTime saida){
        this.chegada = chegada;
        this.saida = saida;
        parquimetro = Parquimetro.getInstance();
        tipoPagamento = CoinCollector.getInstance();
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
}
