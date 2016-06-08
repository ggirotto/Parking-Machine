package Dominio;

import java.time.LocalDateTime;

public class Facade {
    private final IPagamento pagamento;
    private final Parquimetro parquimetro;
    private final LocalDateTime chegada;
    private final LocalDateTime saida;
    
    public Facade(IPagamento ip,LocalDateTime chegada,LocalDateTime saida){
        pagamento = ip;
        this.chegada = chegada;
        this.saida = saida;
        parquimetro = new Parquimetro();
    }
    
    /*
        Gera e imprime o ticket
    */
    public Ticket geraTicket(double valorPago) throws ParquimetroException, PagamentoException, TicketException{
        parquimetro.registraPagamento(chegada, saida, valorPago, pagamento);
        return parquimetro.geraTicket(chegada, saida);
    }
    
    /*
        Gera o log do parquimetro com todas as informações necessárias
    */
    public void geraLogParquimetro() throws ParquimetroException{
        parquimetro.geraLogParquimetro();
    }
}
