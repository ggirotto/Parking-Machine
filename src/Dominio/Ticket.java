package Dominio;

import java.time.LocalTime;
import java.util.Date;

public class Ticket {
    private final int serial;
    private final double valorPago;
    private final LocalTime chegada;
    private LocalTime saida;
    private final int parqId;
    private final String parqAddres;
    private int SERIAL_CONT = 0;
    
    public Ticket(double value, LocalTime saida, int newParqId, String newParqAddres){
        serial = SERIAL_CONT;
        SERIAL_CONT++;
        valorPago = value;
        chegada = LocalTime.now();
        parqId = newParqId;
        parqAddres = newParqAddres;
    }
    
    public int getSerial(){ return serial; }
    public double getValorPago(){ return valorPago; }
    public LocalTime getChegada(){ return chegada; }
    public LocalTime getSaida(){ return saida; }
    public int getParqId(){ return parqId; }
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
