package Dominio;

import java.util.List;

public interface RelatorioDAO {
    
    public List buscarTodosTickets() throws RelatorioDAOException;
    public Ticket buscarTicket(String seral) throws RelatorioDAOException;
    public List buscarTodosPagamentos() throws RelatorioDAOException;
    public void armazenaTicket(Ticket t) throws RelatorioDAOException;
    public void armazenaPagamento(IPagamento p) throws RelatorioDAOException;
    
}