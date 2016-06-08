package Dominio;
import Persistencia.*;

public class AdaptadorNegocioPersistencia {
    
    private final RelatorioDAODTOStore relatorio;
    
    public AdaptadorNegocioPersistencia(){
        
        relatorio = new RelatorioDAODTOStore();
        
    }
    
    public void armazenaTicket(Ticket t, IPagamento p, double valorPago) throws RelatorioDAOException {
        
        TicketDTO ticket = new TicketDTO(t.getSerial(),t.getChegada(),t.getSaida(),
                                        t.getParqId(), t.getParqAddres(),p.getTipo(),
                                        valorPago);
        relatorio.armazenaTicket(ticket);
        
    }

    public void geraRelatorioParquimetro() throws RelatorioDAOException {
        
        relatorio.geraRelatorioParquimetro();
        
    }
    
}