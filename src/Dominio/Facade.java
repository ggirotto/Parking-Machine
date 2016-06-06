package Dominio;

import java.io.IOException;
import java.time.LocalDateTime;

public class Facade {
    private final IPagamento pagamento;
    private final Parquimetro parquimetro;
    private final LocalDateTime saida;
    
    public Facade(IPagamento ip,LocalDateTime saida){
        pagamento = ip;
        this.saida = saida;
        parquimetro = Parquimetro.getInstance();
    }
    
    /*
        Gera e imprime o ticket
    */
    public void geraTicket(double valorPago) throws Exception{
        registraPagamento(valorPago);
        Ticket t = parquimetro.geraTicket(saida);
    }
    
    /*
        Adiciona o tipo de pagamento na instancia do parquimetro;
        Se o pagamento for através de moedas, verifica:
        - Se o valor pago é maior ou igual ao valor necessário
        - Se for, verifica se é maior, se for devolve o troco
        Se for através de cartão, somente desconta o valor do saldo do cartão
        (se o saldo for insuficiente, é tratado no método desconta da classe cartao)
    */
    public void registraPagamento(double valorPago) throws PagamentoException{
        parquimetro.pagamentoEfetuado(pagamento);
        if(pagamento instanceof CoinCollector){
            double valorNecessario = parquimetro.calculaValor(saida);
            if(!(valorPago >= valorNecessario)) throw new PagamentoException("Valor Insuficiente");
            if(valorPago > valorNecessario) System.out.println("Troco: " + pagamento.getTroco(valorPago - valorNecessario));
        }
        else
            pagamento.desconta(parquimetro.calculaValor(saida));
    }
    
    /*
        Gera o log do parquimetro com todas as informações necessárias
    */
    public void geraLogParquimetro() throws IOException,RelatorioDAOException{
        parquimetro.geraLogParquimetro();
    }
}
