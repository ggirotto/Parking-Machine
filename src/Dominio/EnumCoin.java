package Dominio;


public enum EnumCoin {
    
    CINCO(0.05),DEZ(0.10),VINTECINCO(0.25),CINQUENTA(0.50),UMREAL(1.0);
    
    private final double valor;
    
    EnumCoin(double valor){
        this.valor = valor;
    }
    
    public double getValor(){
        return valor;
    }
}
