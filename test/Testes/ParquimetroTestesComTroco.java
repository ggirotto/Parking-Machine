package Testes;

import org.junit.Before;
import org.junit.Test;
import Dominio.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParquimetroTestesComTroco {
    
    private static LocalDateTime saida;
    private static LocalDateTime chegada;
    private static LocalDateTime saidaTroco;
    private static List<Double> moedasPagas;
    private static List<Double> moedasTroco;
    private static Facade f;
    
    @Parameters
    public static Collection data() {
        Collection retorno = Arrays.asList(new Object[][]{
            {LocalDateTime.now().plusMinutes(90),LocalDateTime.now().plusMinutes(30),Arrays.asList(0.25, 0.25, 0.25),Arrays.asList(1.0, 1.0, 0.5)}
            
        });
        return retorno;
    }
    
    public ParquimetroTestesComTroco(LocalDateTime saida, LocalDateTime saidaTroco, List moedasTroco, List moedasPagas) {
        this.chegada = LocalDateTime.now();
        this.saida = saida;
        this.saidaTroco = saidaTroco;
        this.moedasPagas = moedasPagas;
        this.moedasTroco = moedasTroco;
        f = Facade.getInstance(chegada,saidaTroco);
    }
    
    @Test
    public void simulaTrocoCorreto() throws PagamentoException, ParquimetroException, TicketException{
        // Trecho que adiciona moedas para o troco
        double saldo = 0;
        double cont = 0;
        for(double d : moedasTroco){
            f.insereMoeda(d);
            cont+=d;
            saldo += d;
        }
        Ticket t = f.geraTicket(cont);
        // Trecho que adiciona moedas para o troco
        f = Facade.getInstance(chegada,saida);
        cont = 0;
        for(double d : moedasPagas){
            f.insereMoeda(d);
            cont+=d;
            saldo += d;
        }
        
        double necessario = calculaValorNecessario(chegada,saida);
        necessario = cont - necessario;
        
        Ticket p = f.geraTicket(cont);
        assertNotNull(p);

        assertEquals(saldo-necessario,f.getSaldoMaquina(),0.0f);
        
    }
    
    private static int diferencaTempo(LocalDateTime chegada, LocalDateTime saida){
        
        return ((saida.getHour() * 60) + saida.getMinute()) -
                        ((chegada.getHour() * 60) + chegada.getMinute());
        
    }
    
    private static double calculaValorNecessario(LocalDateTime chegada, LocalDateTime saida){
        
        int diferencaTempo = diferencaTempo(chegada,saida);
        double valorInicial = 0.75;
        int diferenca = (diferencaTempo - 30)/10;
        valorInicial += 0.25*diferenca;
        return valorInicial;
        
    }
    
}
