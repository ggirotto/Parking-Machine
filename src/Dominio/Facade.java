package Dominio;

import java.time.LocalDateTime;

public class Facade {
    
    private /*@ spec_public @*/ /*@ non_null @*/ IPagamento tipoPagamento;
    private /*@ spec_public @*/ /*@ non_null @*/ final Parquimetro parquimetro;
    private /*@ spec_public @*/ /*@ non_null @*/ static LocalDateTime chegada;
    private /*@ spec_public @*/ /*@ non_null @*/ static LocalDateTime saida;
    private CartaoRecarregavel cartao = null;
    private /*@ spec_public @*/ /*@ non_null @*/ static Facade facade = null;
    private static double troco;
    
    /*@ requires chegada != null && saida != null;
      @ ensures \result == facade;
      @ ensures !(chegada.equals(\old(chegada)));
      @ ensures !(saida.equals(\old(saida)));
    @*/
    public static Facade getInstance(LocalDateTime chegada,LocalDateTime saida){
        
        if(facade == null){
            facade = new Facade(chegada, saida);
            return facade;
        }
        Facade.chegada = chegada;
        Facade.saida = saida;
        return facade;
        
    }
    
    /*@ ensures chegada != \old(chegada);
      @ ensures saida != \old(saida);
      @ ensures parquimetro != null;
      @ ensures tipoPagamento != null;
      @ ensures tipoPagamento.getTipo() == "Pagamento em moedas";
    @*/
    private Facade(LocalDateTime chegada,LocalDateTime saida){
        Facade.chegada = chegada;
        Facade.saida = saida;
        parquimetro = new Parquimetro();
        tipoPagamento = parquimetro.getCoinCollector();
    }
    
    /*@ requires card != null;
      @ ensures tipoPagamento.getTipo() == "Cartao recarregavel";
    @*/
    public void cartaoInserido(CartaoRecarregavel card){
        cartao = card;
        tipoPagamento = card;
    }
    
    /*@ requires valorPago > 0;
      @ signals (PagamentoException e) valorPago <= 0;
    */
    public Ticket geraTicket(double valorPago) throws ParquimetroException, PagamentoException, TicketException{
        troco = parquimetro.registraPagamento(chegada, saida, valorPago, tipoPagamento);
        return parquimetro.geraTicket(chegada, saida,tipoPagamento,valorPago);
    }
    
    public /*@ pure @*/ void geraLogParquimetro() throws ParquimetroException{
        parquimetro.geraLogParquimetro();
    }
    
    /*@ requires valor == 0.05 ||  valor == 0.1 ||  valor == 0.25 ||  valor == 0.5 ||  valor == 1.0;
      @ ensures tipoPagamento.getSaldo() == \old(tipoPagamento.getSaldo())+valor;
    */
    public void insereMoeda(double valor) throws PagamentoException{
        parquimetro.inserirMoeda(chegada,valor);
    }
    
    /*@ requires tipoPagamento.getTipo() == "Pagamento em moedas";
      @ ensures \result == tipoPagamento.getSaldo();
    */
    public /*@ pure @*/ double getSaldoMaquina(){
        return parquimetro.getCoinCollector().getSaldo();
    }
    
    public /*@ pure @*/ double getTroco(){
        return troco;
    }
    
    public void voltaPadrao(){
        cartao = null;
        tipoPagamento = parquimetro.getCoinCollector();
    }
    
    public void zeraSaldo(){
        parquimetro.getCoinCollector().zeraSaldo();
    }
}