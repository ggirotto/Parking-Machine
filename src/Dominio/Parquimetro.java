package Dominio;

import java.util.List;


public class Parquimetro {
    private int identification;
    private String addres;
    private List<Ticket> tickets;
    
    public Parquimetro(int newId, String newAddres){
        identification = newId;
        addres = newAddres;
    }
    
    public int getIdentification(){ return identification; }
    public String getAddres(){ return addres; }
    public List getTickets(){ return tickets; }
    
    public void setId(int newId){ identification = newId; }
    public void setAddres(String newAddres){ addres = newAddres; }
    

}
