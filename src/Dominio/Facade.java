package Dominio;

import java.time.LocalDateTime;

public class Facade {
    private final IPagamento pagamento;
    private final double valor;
    private final Parquimetro parquimetro;
    
    public Facade(IPagamento ip,double valor){
        this.pagamento = ip;
        this.valor = valor;
        parquimetro = Parquimetro.getInstance();
    }
    
    public Ticket geraTicket() throws Exception{
        registraPagamento();
        return parquimetro.geraTicket(LocalDateTime.now());
    }
    
    public void registraPagamento() throws Exception{
        parquimetro.pagamentoEfetuado(pagamento, valor);
        pagamento.desconta(valor);
    }
}
