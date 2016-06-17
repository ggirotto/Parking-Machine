package Dominio;

import TicketDTO.TicketDTO;
import Persistencia.*;
import java.io.IOException;

public class AdaptadorNegocioPersistencia {
    
    private final RelatorioDAODTOStore relatorio;
    
    public AdaptadorNegocioPersistencia(){
        
        relatorio = new RelatorioDAODTOStore();
        
    }
    /*@ requires t != null && p != null && valorPago>0;
      @ ensures ticket != null;
    @*/
    public /*@ pure @*/ void armazenaTicket(Ticket t, IPagamento p, double valorPago) throws RelatorioDAOException {
        
        TicketDTO ticket = new TicketDTO(t.getSerial(),t.getChegada(),t.getSaida(),
                                        t.getParqId(), t.getParqAddress(),p.getTipo(),
                                        valorPago);
        relatorio.armazenaTicket(ticket);
        
    }

    public /*@ pure @*/ void geraRelatorioParquimetro() throws RelatorioDAOException, IOException {
        
        relatorio.geraRelatorioParquimetro();
        
    }
    
}
