package Dominio;


public enum EnumCoin {
    
    CINCO(5),DEZ(10),VINTECINCO(25),CINQUENTA(50),UMREAL(100);
    
    private final int value;
    
    EnumCoin(int value){
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
}
