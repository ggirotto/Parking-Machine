package Dominio;

import java.time.LocalDateTime;
import Persistencia.*;
import java.io.File;
import java.io.IOException;

public class Facade {
    private final IPagamento pagamento;
    private final Parquimetro parquimetro;
    private final RelatorioDAOStore relatorio;
    private final LocalDateTime saida;
    private final double valorPago;
    
    public Facade(IPagamento ip,LocalDateTime saida,double valorPago){
        pagamento = ip;
        this.saida = saida;
        parquimetro = Parquimetro.getInstance();
        File arq = new File("relatorio.txt");
        relatorio = new RelatorioDAOStore(arq);
        this.valorPago = valorPago;
    }
    
    public void geraTicket() throws Exception{
        registraPagamento();
        Ticket t = parquimetro.geraTicket(saida);
        geraRelatorio(t);
    }
    
    public void registraPagamento() throws PagamentoException{
        parquimetro.pagamentoEfetuado(pagamento);
        if(pagamento instanceof CoinCollector){
            double valorNecessario = parquimetro.calculaValor(saida);
            if(!(valorPago >= valorNecessario)) throw new PagamentoException("Valor Insuficiente");
            double saldo = pagamento.getSaldo();
            if(valorPago > valorNecessario) System.out.println("Troco: " + pagamento.getTroco(valorPago - valorNecessario));
        }
        else
            pagamento.desconta(parquimetro.calculaValor(saida));
    }
    
    private void geraRelatorio(Ticket t) throws RelatorioDAOException, IOException{
        relatorio.armazenaTicket(t,pagamento);
    }
}
