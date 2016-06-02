package Dominio;

import java.util.Date;


public class Ticket {
    private final int serial;
    private final double valorPago;
    private final Date chegada;
    private Date saida;
    private final int parqId;
    private final String parqAddres;
    private int SERIAL_CONT = 0;
    
    public Ticket(double value, Date saida, int newParqId, String newParqAddres){
        serial = SERIAL_CONT;
        SERIAL_CONT++;
        valorPago = value;
        chegada = new Date();
        parqId = newParqId;
        parqAddres = newParqAddres;
    }
    
    public int getSerial(){ return serial; }
    public double getValorPago(){ return valorPago; }
    public Date getChegada(){ return chegada; }
    public Date getSaida(){ return saida; }
    public int getParqId(){ return parqId; }
    public String getParqAddres(){ return parqAddres; }
}
