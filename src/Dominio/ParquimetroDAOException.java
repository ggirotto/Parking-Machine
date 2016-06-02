package Dominio;


public class ParquimetroDAOException extends Exception{
    
    public ParquimetroDAOException(String message){
        super(message);
    }
    
    public ParquimetroDAOException(String message, Exception e){
        super(message, e);
    }
}
