package Persistencia;

import TicketDTO.TicketDTO;
import java.io.IOException;

public interface RelatorioDAO {
    /*@ requires t != null; @ */
    public /*@pure@*/ void armazenaTicket(TicketDTO t) throws RelatorioDAOException;
    public /*@pure@*/ void geraRelatorioParquimetro() throws RelatorioDAOException, IOException;
    
}