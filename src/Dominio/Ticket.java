package Dominio;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class Ticket {
    private final String serial;
    private final LocalDateTime chegada;
    private final LocalDateTime saida;
    private final String parqId;
    private final String parqAddres;
    private static int SERIAL_CONT = 0;
    private static LocalDate serialController = LocalDate.now();
    
    public Ticket(LocalDateTime newSaida, String newParqId, String newParqAddres){
        
        /*
            Verifica se trocou o dia, se sim, zera o número de serial
        */
        if(!(serialController.equals(LocalDate.now()))){
            SERIAL_CONT = 0;
            serialController = LocalDate.now();
        }
        serial = calculaSerial();
        chegada = LocalDateTime.now();
        saida = newSaida;
        parqId = newParqId;
        parqAddres = newParqAddres;
        
    }
    
    /*
        Calcula o número de serial do ticket atual
    */
    private String calculaSerial(){
        
        String retorno = "";
        int serialSize = (SERIAL_CONT+"").length();
        for(int i=0; i<5-serialSize; i++){
            retorno += "0";
        }
        SERIAL_CONT++;
        return retorno+SERIAL_CONT;
        
    }
    
    /*
        getters
    */
    public String getSerial(){ return serial; }
    public LocalDateTime getChegada(){ return chegada; }
    public LocalDateTime getSaida(){ return saida; }
    public String getParqId(){ return parqId; }
    public String getParqAddres(){ return parqAddres; }
}
