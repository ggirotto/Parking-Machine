package Dominio;


public class TicketException extends Exception{
    public TicketException(String message){
        super(message);
    }
    
    public TicketException(String message, Exception e){
        super(message,e);
    }
}
