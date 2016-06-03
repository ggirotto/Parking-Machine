package Dominio;


public class ParquimetroException extends Exception{
    public ParquimetroException(String message){
        super(message);
    }
    
    public ParquimetroException(String message, Exception e){
        super(message,e);
    }
}
