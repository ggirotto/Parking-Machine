package Dominio;

import java.util.List;


public interface TicketDAO {
    public List<Ticket> buscarTodos() throws TicketDAOException;
    public Ticket buscarSerial(int serial) throws TicketDAOException;   
    public void inserir(Ticket t) throws TicketDAOException;
    public void remover() throws TicketDAOException;
}
