package Dominio;

import java.time.LocalDateTime;
import java.util.Date;

public class Ticket {
    private final String serial;
    private final double valorPago;
    private final LocalDateTime chegada;
    private final LocalDateTime saida;
    private final String parqId;
    private final String parqAddres;
    private int SERIAL_CONT = 0;
    
    public Ticket(double value, LocalDateTime newChegada, LocalDateTime newSaida, String newParqId, String newParqAddres){
        serial = SERIAL_CONT+"";
        SERIAL_CONT++;
        valorPago = value;
        chegada = newChegada;
        saida = newSaida;
        parqId = newParqId;
        parqAddres = newParqAddres;
    }
    
    public String getSerial(){ return serial; }
    public double getValorPago(){ return valorPago; }
    public LocalDateTime getChegada(){ return chegada; }
    public LocalDateTime getSaida(){ return saida; }
    public String getParqId(){ return parqId; }
    public String getParqAddres(){ return parqAddres; }
    public Date getData(){ return new Date(); }
    
    public String imprimeTicket(){
        StringBuilder nota = new StringBuilder();
        nota.append(String.format("TICKET PARQUIMETRO%n"));
        nota.append(String.format("%d %2$tD %2$tR%n", getSerial(), getData()));
        
        nota.append(String.format("\nIdentificacao Parquimetro: %d", getParqId()));
        nota.append(String.format("\nEndereço do parquímetro: %s", getParqAddres()));
        nota.append(String.format("\nNúmero de Serial: %d", getSerial()));
        nota.append(String.format("\nData da emissão do tiquete: %2$tD %2$tR%n ", getData(), getChegada()));
        nota.append(String.format("\nData da validade do tiquete: %2$tD %2$tR%n ", getData(), getSaida()));

        return nota.toString();
    }
}
