package Dominio;

import java.time.LocalDateTime;

public class Facade {
    private final IPagamento pagamento;
    private final Parquimetro parquimetro;
    private final LocalDateTime saida;
    
    public Facade(IPagamento ip,LocalDateTime saida){
        pagamento = ip;
        this.saida = saida;
        parquimetro = new Parquimetro();
    }
    
    /*
        Gera e imprime o ticket
    */
    public void geraTicket(double valorPago) throws ParquimetroException, PagamentoException{
        parquimetro.registraPagamento(saida, valorPago, pagamento);
        Ticket t = parquimetro.geraTicket(saida);
    }
    
    /*
        Gera o log do parquimetro com todas as informações necessárias
    */
    public void geraLogParquimetro() throws ParquimetroException{
        parquimetro.geraLogParquimetro();
    }
}
