package Dominio;


public enum EnumCoin {
    
    CINCO(0.05),DEZ(0.10),VINTECINCO(0.25),CINQUENTA(0.50),UMREAL(1.0);
    
    private final double value;
    
    EnumCoin(double value){
        this.value = value;
    }
    
    public double getValue(){
        return value;
    }
}
