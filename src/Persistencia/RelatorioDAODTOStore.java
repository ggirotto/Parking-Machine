package Persistencia;

import TicketDTO.TicketDTO;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class RelatorioDAODTOStore implements RelatorioDAO, Serializable{
    
    private /*@ non_null @*/ final ArrayList<TicketDTO> listaTickets;
    
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
    public void geraRelatorioParquimetro() throws RelatorioDAOException, IOException {
        
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new
            BufferedOutputStream(new FileOutputStream("00001-tickets.bin")));
            out.writeObject(listaTickets);
        } finally {
            out.close();
        }
        
    }
}
    