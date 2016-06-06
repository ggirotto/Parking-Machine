package Persistencia;

import Dominio.IPagamento;
import Dominio.RelatorioDAO;
import Dominio.RelatorioDAOException;
import Dominio.Ticket;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAOStore implements RelatorioDAO{
    
    private final File arquivo;
    
    public RelatorioDAOStore(File arq){
        arquivo = arq;
    }
    
    @Override
    public List buscarTodosTickets() throws RelatorioDAOException {
        List<Ticket> resultado = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))){
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
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))){
            resultado = (ArrayList<IPagamento>)ois.readObject();
            return resultado;
        } catch(Exception e) {
            throw new RelatorioDAOException("Falha na consulta de pagamentos", e);
        }
    }

    @Override
    public void armazenaTicket(Ticket t, IPagamento p) throws RelatorioDAOException{
        try(FileWriter writer = new FileWriter(arquivo, true);
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter out = new PrintWriter(bw)){
        
            out.println("#");
            out.println("Ticket número: " + t.getSerial());
            out.println("Identificação do Parquimetro: " + t.getParqId());
            out.println("Endereço do Parquimetro: " + t.getParqAddres());
            out.println("Método de pagamento: " + p.getTipo());
            out.println("Hora de Chegada: " + t.getChegada().toString());
            out.println("Hora de Saída: " + t.getSaida().toString());
            out.println("Valor Pago: " + t.getValorPago());
            out.println("\n");
        }
        catch (IOException e){}
    }

    @Override
    public void armazenaPagamento(IPagamento p) throws RelatorioDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
