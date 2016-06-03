package Dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public class Parquimetro {
    private final String identificacao = "00001";
    private final String endereco = "Rua da Minha Casa, 321";
    private List<Ticket> tickets;
    private final LocalDateTime inicioTarifa = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 30));
    private final LocalDateTime fimTarifa = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 30));
    private final LocalTime tempoMinimo = LocalTime.of(0, 30);
    private final LocalTime tempoMaximo = LocalTime.of(2, 00);
    private final LocalTime incremento = LocalTime.of(0, 10);
    private final double valorMinimo = 0.75;
    private final double valorMaximo = 3.0;
    private final double valorIncremento = 0.25;
    private IPagamento pagamento;
    private double totalPago = 0;
    private static Parquimetro instance = null;
    
    private Parquimetro() {}
    
    public static Parquimetro getInstance()
    {
        if(instance == null) instance = new Parquimetro();
        return instance;
    }
    
    /*
        gera o ticket com as informações fornecidas
    */
    public Ticket geraTicket(LocalDateTime saida) throws Exception{
        Ticket t;
        if(isTarifying()){
            LocalDateTime chegada = LocalDateTime.now();
            boolean verificaPagamento = verificaValorPago(diferencaTempo(chegada,saida));
            if(!verificaPagamento) throw new PagamentoException("Valor pago insuficiente");
            t = new Ticket(totalPago,saida,identificacao,endereco);
        } else throw new ParquimetroException("O parquimetro não está operando");
        return t;
    }
    
    /*
        Registra o valor e o tipo de pagamento realziado no parquimetro
    */
    public void pagamentoEfetuado(IPagamento tipo, double valor){
        pagamento = tipo;
        totalPago = valor;
    }
    
    /*
        Verifica se o valor pago condiz com o tempo de estadia escolhido
    */
    private boolean verificaValorPago(int diferencaTempo){
        double valorInicial = valorMinimo;
        int diferenca = (diferencaTempo - 30)/10;
        valorInicial += valorIncremento*diferenca;
        if(valorInicial > valorMaximo) return false;
        return valorInicial <= totalPago;
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
        LocalDateTime chegada = LocalDateTime.now();
        if(chegada.isAfter(inicioTarifa) && chegada.isBefore(fimTarifa)) return true;
        return false;
    }
    
    public String getIdentificacao(){ return identificacao; }
    public String getEndereco(){ return endereco; }
    public List<Ticket> getTickets(){ return tickets; }
    

}
