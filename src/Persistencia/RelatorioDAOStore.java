package Persistencia;

import Dominio.IPagamento;
import Dominio.RelatorioDAO;
import Dominio.RelatorioDAOException;
import Dominio.Ticket;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAOStore implements RelatorioDAO{
    
    private File tickets;
    private File pagamentos;
    
    public File getInstanceTickets(){
        if(tickets == null) tickets = new File("tickets.txt");
        return tickets;
    }
    
    public File getInstancePagamentos(){
        if(pagamentos == null) pagamentos = new File("pagamentos.txt");
        return pagamentos;
    }
    
    @Override
    public List buscarTodosTickets() throws RelatorioDAOException {
        List<Ticket> resultado = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tickets))){
            resultado = (ArrayList<Ticket>)ois.readObject();
            return resultado;
        } catch(Exception e) {
            throw new RelatorioDAOException("Falha na consulta de tickets", e);
        }
    }

    @Override
    public Ticket buscarTicket(String seral) throws RelatorioDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List buscarTodosPagamentos() throws RelatorioDAOException {
        List<IPagamento> resultado = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pagamentos))){
            resultado = (ArrayList<IPagamento>)ois.readObject();
            return resultado;
        } catch(Exception e) {
            throw new RelatorioDAOException("Falha na consulta de pagamentos", e);
        }
    }

    @Override
    public void armazenaTicket(Ticket t) throws RelatorioDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void armazenaPagamento(IPagamento p) throws RelatorioDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
