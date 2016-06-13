package Persistencia;

import TicketDTO.TicketDTO;
import java.io.IOException;

public interface RelatorioDAO {

    public void armazenaTicket(TicketDTO t) throws RelatorioDAOException;
    public void geraRelatorioParquimetro() throws RelatorioDAOException, IOException, ClassNotFoundException;
    
}