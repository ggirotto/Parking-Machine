package Persistencia;

import Dominio.Ticket;
import java.util.List;


public interface TicketDAO {
    public List buscarTodos();
    public Ticket buscarSerial();   
    public void inserir(Ticket t);
    public void remover();
}
