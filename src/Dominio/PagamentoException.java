package Dominio;


public class PagamentoException extends Exception {
    public PagamentoException(String message){
        super(message);
    }
    
    public PagamentoException(String message, Exception e){
        super(message,e);
    }
}
