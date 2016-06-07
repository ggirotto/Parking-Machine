package Persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAODTOStore implements RelatorioDAO{
    
    private final File arquivo;
    private final List<TicketDTO> listaTickets;
    
    public RelatorioDAODTOStore(){
        arquivo = new File("relatorioParquimetro");
        listaTickets = new ArrayList<>();
    }
    
    /*
        Armazena o ticket na lista de tickets
    */
    @Override
    public void armazenaTicket(TicketDTO t) throws RelatorioDAOException{
        
        listaTickets.add(t);
        
    }
    
    /*
        Imprime o relatório do parquimetro
    */
    @Override
    public void geraRelatorioParquimetro() throws RelatorioDAOException {
        
        TicketDTO informacoes = listaTickets.get(0);
        double totalArrecadado = 0;
        
        try(FileWriter writer = new FileWriter(arquivo, true);
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter out = new PrintWriter(bw)){
        
            out.println("#");
            out.println("Identificação do Parquimetro: " + informacoes.getParqId());
            out.println("Endereço do Parquimetro: " + informacoes.getParqAddres());
            out.println("Tickets e informações: ");
            for(TicketDTO t : listaTickets){
                out.println("--Ticket: " + t.getSerial());
                out.println("\tData de Chegada: " + t.getChegada().toString());
                out.println("\tData de Saída: " + t.getSaida().toString());
                out.println("\tValor Pago: " + t.getValorPago());
                out.println("\tForma de Pagamento: " + t.getTipoPagamento());
                totalArrecadado += t.getValorPago();
            }
            // todo
            out.println("Número de Tickets emitidos: " + listaTickets.size());
            out.println("Total Arrecadado: " + totalArrecadado);
            out.println("\n");
        }
        catch (IOException e){}
        
    }
    
}
