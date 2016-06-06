package Dominio;

import java.time.LocalDateTime;

public class Ticket {
    private final String serial;
    private final double valorPago;
    private final LocalDateTime chegada;
    private final LocalDateTime saida;
    private final String parqId;
    private final String parqAddres;
    private static int SERIAL_CONT = 0;
    
    public Ticket(double value, LocalDateTime newSaida, String newParqId, String newParqAddres){
        serial = calculaSerial();
        valorPago = value;
        chegada = LocalDateTime.now();
        saida = newSaida;
        parqId = newParqId;
        parqAddres = newParqAddres;
    }
    
    private String calculaSerial(){
        String retorno = "";
        int serialSize = (SERIAL_CONT+"").length();
        for(int i=0; i<5-serialSize; i++){
            retorno += "0";
        }
        SERIAL_CONT++;
        return retorno+SERIAL_CONT;
    }
    
    public String getSerial(){ return serial; }
    public double getValorPago(){ return valorPago; }
    public LocalDateTime getChegada(){ return chegada; }
    public LocalDateTime getSaida(){ return saida; }
    public String getParqId(){ return parqId; }
    public String getParqAddres(){ return parqAddres; }
}
