package Dominio;

public interface RelatorioDAO {

    public void imprimeTicket(Ticket t, IPagamento p) throws RelatorioDAOException;
    public void geraRelatorioParquimetro(Parquimetro p) throws RelatorioDAOException;
    
}