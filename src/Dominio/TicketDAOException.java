package Dominio;


public class TicketDAOException extends Exception{
    
    public TicketDAOException(String message){
        super(message);
    }
    
    public TicketDAOException(String message, Exception e){
        super(message, e);
    }
}
