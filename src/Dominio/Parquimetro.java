package Dominio;

import java.time.LocalTime;
import java.util.List;


public class Parquimetro {
    private int identification;
    private String addres;
    private List<Ticket> tickets;
    private final LocalTime inicioTarifa;
    private final LocalTime fimTarifa;
    private final LocalTime tempoMinimo;
    private final LocalTime tempoMaximo;
    private final LocalTime incremento;
    private final double valorMinimo;
    private final double valorMaximo;
    private final double valorIncremento;
    private double valorPago;
    
    
    public Parquimetro(int newId, String newAddres, LocalTime tempoMinimo, LocalTime tempoMaximo,
                        double valorMinimo, double valorMaximo, LocalTime incremento, double valorIncremento,
                        LocalTime inicioTarifa, LocalTime fimTarifa){
        if(String.valueOf(newId).length() == 5) identification = newId;
        this.inicioTarifa = inicioTarifa;
        this.fimTarifa = fimTarifa;
        this.tempoMinimo = tempoMinimo;
        this.tempoMaximo = tempoMaximo;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.incremento = incremento;
        this.valorIncremento = valorIncremento;
        addres = newAddres;
        
    }
    
    public void geraTicket(LocalTime saida, double valorPago){
        if(isTarifying() == false){
            Ticket t = new Ticket(0,saida,identification,addres);
            return;
        }
        
        /*
            Se o tempo desejado exceder o tempo máximo
        */
        if(diferencaTempo(saida) > 120 || diferencaTempo(saida) < 30) return;
        
        /*
            Verifica se o valor pago condiz com o tempo de estadia
        */
        int diferencaTempo = diferencaTempo(saida);
        if(!(verificaValorPago(diferencaTempo,valorPago))) return;
        
        /*
            Se tempo de estadia e valor pago estão ok, cria o ticket
        */
        
        Ticket t = new Ticket(valorPago, saida, identification, addres);
        t.imprimeTicket();
        
    }
    
    /*
        Verifica se o valor pago condiz com o tempo de estadia escolhido
    */
    private boolean verificaValorPago(int diferencaTempo, double valorPago){
        
        double valorTotal = valorMinimo;
        int tempoTotal = tempoMinimo.getMinute();
        while(tempoTotal < diferencaTempo){
            valorTotal += valorIncremento;
            tempoTotal += 10;
        }
        
        if(valorTotal == valorPago) return true;
        return false;
        
    }
    
    /*
        Retorna a diferença de tempo em minutos entre a chegada e a hora de saida
    */
    private int diferencaTempo(LocalTime saida){
        
        int hora = saida.getHour() * 60;
        int minuto = saida.getMinute();
        int tempoTotalSaida = hora+minuto;
        
        
        hora = LocalTime.now().getHour() * 60;
        minuto = LocalTime.now().getMinute();
        int tempoTotalAgora = hora+minuto;
        
        return tempoTotalSaida - tempoTotalAgora;  
        
    }
    
    public int getIdentification(){ return identification; }
    public String getAddres(){ return addres; }
    public List getTickets(){ return tickets; }
    
    /*
        Verifca se o parquimetro está tarifando no horário atual
    */
    private boolean isTarifying(){
        return LocalTime.now().isBefore(fimTarifa) && LocalTime.now().isAfter(fimTarifa);
    }
    
    public void setId(int newId){ identification = newId; }
    public void setAddres(String newAddres){ addres = newAddres; }
    

}
