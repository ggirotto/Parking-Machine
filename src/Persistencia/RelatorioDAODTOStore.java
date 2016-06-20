package Persistencia;

import TicketDTO.TicketDTO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class RelatorioDAODTOStore implements RelatorioDAO, Serializable{
    
    private /*@ non_null @*/ final ArrayList<TicketDTO> listaTickets;
    private static ObjectOutputStream lista;
    
    public RelatorioDAODTOStore(){
        listaTickets = new ArrayList<>();
    }
    
    /*
        Armazena o ticket na lista de tickets
    */
    @Override
    public void armazenaTicket(TicketDTO t) {
        
        listaTickets.add(t);
        
    }
    
    /*
        Imprime o relatório do parquimetro
    */
    @Override
    public void geraRelatorioParquimetro() {
        try{
        if(lista!=null){
            lista.writeObject(listaTickets);
            lista.close();
        }else{
                lista = new ObjectOutputStream(new FileOutputStream("listaTickets.bin"));
                lista.writeObject(listaTickets);
                lista.close();
        }
        } catch (IOException e){
                System.out.println("Erro na hora de gerar o relatório");
            }
        
        
    }
    
}
