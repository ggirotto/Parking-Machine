package Persistencia;

import Dominio.IPagamento;
import Dominio.RelatorioDAO;
import Dominio.RelatorioDAOException;
import Dominio.Ticket;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
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
    public void armazenaTicket(Ticket t, IPagamento p) throws RelatorioDAOException, IOException{
        FileWriter writer = new FileWriter(arquivo);
        
        writer.write("\n-----");
        writer.write("\nTicket número: " + t.getSerial());
        writer.write("\nIdentificação do Parquimetro: " + t.getParqId());
        writer.write("\nEndereço do Parquimetro: " + t.getParqAddres());
        writer.write("\nMétodo de pagamento: " + p.getTipo());
        writer.write("\nHora de Chegada: " + t.getChegada().toString());
        writer.write("\nHora de Saída: " + t.getSaida().toString());
        writer.write("\nValor Pago: " + t.getValorPago());
        writer.write("\n-----");
        writer.close();
        
    }

    @Override
    public void armazenaPagamento(IPagamento p) throws RelatorioDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
