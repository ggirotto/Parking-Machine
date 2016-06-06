package Dominio;

import java.io.IOException;
import java.util.List;

public interface RelatorioDAO {
    
    public List buscarTodosTickets() throws RelatorioDAOException;
    public Ticket buscarTicket(String seral) throws RelatorioDAOException;
    public List buscarTodosPagamentos() throws RelatorioDAOException;
    public void armazenaTicket(Ticket t, IPagamento p) throws RelatorioDAOException, IOException;
    public void armazenaPagamento(IPagamento p) throws RelatorioDAOException;
    
}