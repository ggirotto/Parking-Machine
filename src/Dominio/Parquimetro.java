package Dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public class Parquimetro {
    private final String identification;
    private final String addres;
    private List<Ticket> tickets;
    private final LocalDateTime inicioTarifa = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 30));
    private final LocalDateTime fimTarifa = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 30));
    private final LocalTime tempoMinimo = LocalTime.of(0, 30);
    private final LocalTime tempoMaximo = LocalTime.of(2, 00);
    private final LocalTime incremento = LocalTime.of(0, 10);
    private final double valorMinimo = 0.75;
    private final double valorMaximo = 3.0;
    private final double valorIncremento = 0.25;
    
    
    public Parquimetro(String newId, String newAddres) throws ParquimetroDAOException{
        if(newId.length() == 5) identification = newId;
        else throw new ParquimetroDAOException("Formato Inválido");
        addres = newAddres;
    }
    
    /*
        gera o ticket com as informações fornecidas
    */
    public void geraTicket(LocalTime saida, double valorPago){
        
    }
    
    /*
        Verifica se o valor pago condiz com o tempo de estadia escolhido
    */
    private boolean verificaValorPago(int diferencaTempo, double valorPago){
        return false;
    }
    
    /*
        Retorna a diferença de tempo em minutos entre a chegada e a hora de saida
    */
    private int diferencaTempo(LocalTime primeiro, LocalTime segundo){
        return -1;
    }
    
    /*
        Verifca se o parquimetro está tarifando no horário atual
    */
    private boolean isTarifying(){
        return false;
    }
    
    public String getIdentification(){ return identification; }
    public String getAddres(){ return addres; }
    public List<Ticket> getTickets(){ return tickets; }
    

}
