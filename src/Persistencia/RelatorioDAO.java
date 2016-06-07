package Persistencia;

public interface RelatorioDAO {

    public void armazenaTicket(TicketDTO t) throws RelatorioDAOException;
    public void geraRelatorioParquimetro() throws RelatorioDAOException;
    
}