package Dominio;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class Ticket {
    
    private /*@ non_null @*/ final String serial;
    private /*@ non_null @*/ final LocalDateTime chegada;
    private /*@ non_null @*/ final LocalDateTime saida;
    private /*@ non_null @*/ final String parqId;
    private /*@ non_null @*/ final String parqAddress;
    private static int SERIAL_CONT = 0;
    private static LocalDate serialController = LocalDate.now();
    
    public Ticket(LocalDateTime newChegada, LocalDateTime newSaida, String newParqId, String newParqAddress) throws TicketException{
        
        /*
            Verifica se trocou o dia, se sim, zera o número de serial
        */
        if(!(serialController.equals(newChegada.toLocalDate()))){
            SERIAL_CONT = 0;
            serialController = newChegada.toLocalDate();
        }
        serial = calculaSerial();
        chegada = newChegada;
        saida = newSaida;
        parqId = newParqId;
        parqAddress = newParqAddress;
        
    }
    
    /*
        Calcula o número de serial do ticket atual
    */
    /*@ ensures SERIAL_CONT == \old(SERIAL_CONT)+1;
      @ signals (TicketException e) SERIAL_CONT > 99999;
    @*/
    private String calculaSerial() throws TicketException{
        
        String retorno = "";
        int serialSize = (SERIAL_CONT+"").length();
        for(int i=0; i<5-serialSize; i++){
            retorno += "0";
        }
        SERIAL_CONT++;
        if(SERIAL_CONT > 99999) throw new TicketException("Número máximo de tickets diários atingido");
        return retorno+SERIAL_CONT;
        
    }
    
    /*
        getters
    */
    public /*@pure@*/ String getSerial(){ return serial; }
    public /*@pure@*/ LocalDateTime getChegada(){ return chegada; }
    public /*@pure@*/ LocalDateTime getSaida(){ return saida; }
    public /*@pure@*/ String getParqId(){ return parqId; }
    public /*@pure@*/ String getParqAddress(){ return parqAddress; }
}
