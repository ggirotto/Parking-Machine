package Dominio;


public class RelatorioDAOException extends Exception{
    
    public RelatorioDAOException(String message){
        super(message);
    }
    
    public RelatorioDAOException(String message, Exception e){
        super(message,e);
    }
}
