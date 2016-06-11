package Persistencia;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TicketDTO implements Serializable{
    
    private final String serial;
    private final LocalDateTime chegada;
    private final LocalDateTime saida;
    private final String parqId;
    private final String parqAddres;
    private final String tipoPagamento;
    private final double valorPago;
    
    public TicketDTO(String serial, LocalDateTime chegada, LocalDateTime saida,
                String parqId, String parqAddress, String tipoPagamento, double valorPago){
        
        this.serial = serial;
        this.chegada = chegada;
        this.saida = saida;
        this.parqId = parqId;
        this.parqAddres = parqAddress;
        this.tipoPagamento = tipoPagamento;
        this.valorPago = valorPago;
        
    }
    
    public String getSerial(){ return serial; }
    
    public LocalDateTime getChegada(){ return chegada; }
    
    public LocalDateTime getSaida(){ return saida; }
    
    public String getParqId(){ return parqId; }
    
    public String getParqAddres(){ return parqAddres; }
    
    public double getValorPago(){ return valorPago; }
    
    public String getTipoPagamento(){ return tipoPagamento; }
}
