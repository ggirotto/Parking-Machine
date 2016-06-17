package Dominio;

public interface IPagamento {
    
    /*@
        @ ensures getSaldo() == \old(getSaldo());
        @ ensures \result >= 0;
    @*/
    public /*@ pure @*/ double getSaldo();
    
    /*@
        @ ensures getTipo() == \old(getTipo());
        @ ensures \result != null;
    @*/
    public /*@ pure @*/ String getTipo();
    
    /*@
        @ ensures getSaldo() == 0;
    @*/
    public void zeraSaldo();
    
}
