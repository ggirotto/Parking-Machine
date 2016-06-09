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
        arquivo = new File("relatorioParquimetro.txt");
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
        
            out.print(informacoes.getParqId()+";");
            out.print(informacoes.getParqAddres()+";");
            for(TicketDTO t : listaTickets){
                out.print("#");
                out.print(t.getSerial()+";");
                out.print(t.getChegada().toString()+";");
                out.print(t.getSaida().toString()+";");
                out.print(t.getValorPago()+";");
                out.print(t.getTipoPagamento()+";");
                out.print("#");
                totalArrecadado += t.getValorPago();
            }
            out.print(listaTickets.size()+";");
            out.print(totalArrecadado+";");
            out.print("\n");
        }
        catch (IOException e){}
        
    }
    
}
