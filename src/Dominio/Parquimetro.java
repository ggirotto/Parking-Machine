package Dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;


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
    private IPagamento tipoPagamento;
    private double valorPago = 0;
    private final AdaptadorNegocioPersistencia adapter;
    
    public Parquimetro() {
        adapter = new AdaptadorNegocioPersistencia();
    }
    
    /*
        Gera e imprime o ticket
    */
    public Ticket geraTicket(LocalDateTime saida) throws PagamentoException, ParquimetroException{
        
        Ticket t;
        //if(isTarifying()){
            LocalDateTime chegada = LocalDateTime.of(2016, Month.JUNE, 3, 12, 00);
            boolean verificaPagamento = verificaValorPago(diferencaTempo(chegada,saida));
            tempoEstadia(saida);
            if(!verificaPagamento) throw new PagamentoException("Valor pago insuficiente");
            t = new Ticket(saida,identificacao,endereco);
        // else throw new ParquimetroException("O parquimetro não está operando");
        armazenaTicket(t);
        return t;
        
    }
    
    /*
        Armazena o ticket na camada de Persistencia
    */
    public void armazenaTicket(Ticket t) throws ParquimetroException {
        
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
    public void registraPagamento(LocalDateTime saida, double valorPago, IPagamento pagamento) throws PagamentoException{
        tipoPagamento = pagamento;
        this.valorPago = valorPago;
        if(pagamento instanceof CoinCollector){
            double valorNecessario = calculaValor(saida);
            if(!(valorPago >= valorNecessario)) throw new PagamentoException("Valor Insuficiente");
            if(valorPago > valorNecessario) System.out.println("Troco: " + pagamento.getTroco(valorPago - valorNecessario));
        }
        else
            pagamento.desconta(calculaValor(saida));
    }
    
    /*
        Verifica se o valor pago condiz com o tempo de estadia escolhido
    */
    private boolean verificaValorPago(int diferencaTempo){
        
        double valorInicial = valorMinimo;
        int diferenca = (diferencaTempo - 30)/10;
        valorInicial += valorIncremento*diferenca;
        if(valorInicial > valorMaximo) return false;
        return valorInicial <= valorPago;
        
    }
    
    /*
        Calcula o valor a ser pago a partir do tempo de estadia
    */
    public double calculaValor(LocalDateTime saida){
        
        LocalDateTime chegada = LocalDateTime.of(2016, Month.JUNE, 3, 12, 00);
        int diferencaTempo = diferencaTempo(chegada,saida);
        double valorInicial = valorMinimo;
        int diferenca = (diferencaTempo - 30)/10;
        valorInicial += valorIncremento*diferenca;
        return valorInicial;
        
    }
    /*
        Verifica se o tempo de permanencia está entre os limites mínimo e máximo
    */
    private void tempoEstadia(LocalDateTime saida) throws ParquimetroException{
        
        int diferencaTempo = diferencaTempo(LocalDateTime.of(2016, Month.JUNE, 3, 12, 00), saida);
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
    public IPagamento getPagamento(){ return tipoPagamento; }
    

}
