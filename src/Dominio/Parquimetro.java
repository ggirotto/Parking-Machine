package Dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Parquimetro {
    private final String identificacao = "00001";
    private final String endereco = "Rua da Minha Casa, 321";
    private final LocalDateTime inicioTarifa = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 30));
    private final LocalDateTime fimTarifa = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 30));
    private final LocalTime tempoMinimo = LocalTime.of(0, 30);
    private final LocalTime tempoMaximo = LocalTime.of(2, 00);
    private final LocalTime tempoIncremento = LocalTime.of(0, 10);
    private final double valorMinimo = 0.75;
    private final double valorMaximo = 3.0;
    private final double valorIncremento = 0.25;
    private final AdaptadorNegocioPersistencia adapter;
    private final CoinCollector coinMachine;
    
    public Parquimetro (){
        adapter = new AdaptadorNegocioPersistencia();
        coinMachine = new CoinCollector();
    }
    
    public void inserirMoeda(double valor) throws PagamentoException{
        coinMachine.insereMoeda(valor);
    }
    /*
        Gera e imprime o ticket
    */
    public Ticket geraTicket(LocalDateTime chegada, LocalDateTime saida, IPagamento tipoPagamento, 
                             double valorPago) throws ParquimetroException, TicketException{
        
        Ticket t;
        //if(isTarifying()){
            verificaTempoEstadia(chegada, saida);
            t = new Ticket(saida,identificacao,endereco);
        //} else throw new ParquimetroException("O parquimetro não está operando");
        armazenaTicket(t,tipoPagamento,valorPago);
        return t;
        
    }
    
    /*
        Armazena o ticket na camada de Persistencia
    */
    public void armazenaTicket(Ticket t, IPagamento tipoPagamento,double valorPago) throws ParquimetroException {
        
        try{
            
            adapter.armazenaTicket(t, tipoPagamento, valorPago);
            
        }catch(Exception e) {
            throw new ParquimetroException("Erro na hora de armazena o ticket");
        }
        
    }
    
    /*
        Imprime o relatório do parquímetro
    */
    public void geraLogParquimetro() throws ParquimetroException {
        
        try{
            
            adapter.geraRelatorioParquimetro();
            
        }catch(Exception e) {
            throw new ParquimetroException("Erro na hora de armazena o ticket");
        }
        
    }
    
    /*
        Adiciona o tipo de pagamento na instancia do parquimetro;
        Se o pagamento for através de moedas, verifica:
        - Se o valor pago é maior ou igual ao valor necessário
        - Se for, verifica se é maior, se for devolve o troco
        Se for através de cartão, somente desconta o valor do saldo do cartão
        (se o saldo for insuficiente, é tratado no método desconta da classe cartao)
    */
    public void registraPagamento(LocalDateTime chegada, LocalDateTime saida,
                                    double valorPago, IPagamento tipoPagamento) throws PagamentoException{
        if(tipoPagamento instanceof CoinCollector){
            ((CoinCollector)tipoPagamento).verificaValorPago(calculaValorNecessario(chegada,saida));
        }
        else
            ((CartaoRecarregavel)tipoPagamento).desconta(calculaValorNecessario(chegada, saida));
    }
    
    /*
        Calcula o valor a ser pago a partir do tempo de estadia
    */
    private double calculaValorNecessario(LocalDateTime chegada, LocalDateTime saida){
        
        int diferencaTempo = diferencaTempo(chegada,saida);
        double valorInicial = valorMinimo;
        int diferenca = (diferencaTempo - 30)/10;
        valorInicial += valorIncremento*diferenca;
        return valorInicial;
        
    }
    /*
        Verifica se o tempo de permanencia está entre os limites mínimo e máximo
    */
    private void verificaTempoEstadia(LocalDateTime chegada, LocalDateTime saida) throws ParquimetroException{
        
        int diferencaTempo = diferencaTempo(chegada, saida);
        if(diferencaTempo > 120) throw new ParquimetroException("Tempo de estadia além do tempo máximo");
        if(diferencaTempo < 30) throw new ParquimetroException("Tempo de estadia menor do que o tempo minimo");
        
    }
    /*
        Retorna a diferença de tempo em minutos entre a chegada e a hora de saida
    */
    private int diferencaTempo(LocalDateTime chegada, LocalDateTime saida){
        
        return ((saida.getHour() * 60) + saida.getMinute()) -
                        ((chegada.getHour() * 60) + chegada.getMinute());
        
    }
    
    /*
        Verifca se o parquimetro está tarifando no horário atual
    */
    private boolean isTarifying(){
        
        return LocalDateTime.now().isAfter(inicioTarifa) && LocalDateTime.now().isBefore(fimTarifa);
        
    }
    
    /*
        getters
    */
    public String getIdentificacao(){ return identificacao; }
    public String getEndereco(){ return endereco; }
    public IPagamento getCoinCollector(){ return coinMachine; }
    

}
