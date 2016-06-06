package Persistencia;

import Dominio.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RelatorioDAOStore implements RelatorioDAO{
    
    private final File arquivo;
    
    public RelatorioDAOStore(File arq){
        arquivo = arq;
    }
    
    /*
        Imprime o ticket
    */
    @Override
    public void imprimeTicket(Ticket t, IPagamento p) throws RelatorioDAOException{
        
        try(FileWriter writer = new FileWriter(arquivo, true);
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter out = new PrintWriter(bw)){
        
            out.println("#");
            out.println("Identificação do Parquimetro: " + t.getParqId());
            out.println("Endereço do Parquimetro: " + t.getParqAddres());
            out.println("Serial do Ticket: " + t.getSerial());
            out.println("Data de Chegada: " + t.getChegada().toString());
            out.println("Data de Saída: " + t.getSaida().toString());
            out.println("Valor Pago: " + t.getValorPago());
            out.println("\n");
        }
        catch (IOException e){}
        
    }
    
    /*
        Imprime o relatório do parquimetro
    */
    @Override
    public void geraRelatorioParquimetro(Parquimetro p) throws RelatorioDAOException {
        
        try(FileWriter writer = new FileWriter(arquivo, true);
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter out = new PrintWriter(bw)){
        
            out.println("#");
            out.println("Identificação do Parquimetro: " + p.getIdentificacao());
            out.println("Endereço do Parquimetro: " + p.getEndereco());
            out.println("Total Arrecadado: " + p.getTotalArrecadado());
            out.println("Número de Tickets emitidos: " + p.getTickets().size());
            out.println("Número de Moedas: " + CoinCollector.getInstance().getNroMoedas());
            out.println("Tickets e informações: ");
            for(Ticket t : p.getTickets()){
                out.println("--Ticket: " + t.getSerial());
                out.println("\tData de Chegada: " + t.getChegada().toString());
                out.println("\tData de Saída: " + t.getSaida().toString());
                out.println("\tValor Pago: " + t.getValorPago());
                out.println("\tForma de Pagamento: " + t.getTipoPagamento().getTipo());
            }
            out.println("\n");
        }
        catch (IOException e){}
        
    }
    
}
