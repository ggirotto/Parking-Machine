package Persistencia;

import TicketDTO.TicketDTO;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class RelatorioDAODTOStore implements RelatorioDAO, Serializable{
    
    private final File arquivo;
    private final ArrayList<TicketDTO> listaTickets;
    
    public RelatorioDAODTOStore(){
        arquivo = new File("relatorioParquimetro.txt");
        listaTickets = new ArrayList<>();
    }
    
    /*
        Armazena o ticket na lista de tickets
    */
    @Override
    public void armazenaTicket(TicketDTO t) throws RelatorioDAOException{
        
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
            BufferedOutputStream(new FileOutputStream("listaTickets.bin")));
            out.writeObject(listaTickets);
        } finally {
            out.close();
        }
        
    }
    
}
