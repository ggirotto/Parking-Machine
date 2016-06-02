package Dominio;


public class CartaoRecarregavelDAOException extends Exception{
    
    public CartaoRecarregavelDAOException(String message){
        super(message);
    }
    
    public CartaoRecarregavelDAOException(String message, Exception e){
        super(message, e);
    }
}
