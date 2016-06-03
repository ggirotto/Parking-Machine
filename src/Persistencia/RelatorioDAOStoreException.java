package Persistencia;


public class RelatorioDAOStoreException extends Exception{
    public RelatorioDAOStoreException(String message){
        super(message);
    }
    
    public RelatorioDAOStoreException(String message, Exception e){
        super(message,e);
    }
}
