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
public class ParquimetroTestesComMoedas {
    
    private final LocalDateTime chegada;
    private final int saida;
    private static Facade f;
    private final List<Double> moedas;

    @Parameters
    public static Collection data() {
        Collection retorno = Arrays.asList(new Object[][]{
            {LocalDateTime.now(),30,Arrays.asList(0.25, 0.25, 0.25)},
            {LocalDateTime.now(),40,Arrays.asList(0.5, 0.5)},
            {LocalDateTime.now(),50,Arrays.asList(0.5, 0.5, 0.25)},
            {LocalDateTime.now(),60,Arrays.asList(1.0, 0.5)},
            {LocalDateTime.now(),70,Arrays.asList(1.0, 0.5, 0.25)},
            {LocalDateTime.now(),80,Arrays.asList(1.0, 1.0)},
            {LocalDateTime.now(),90,Arrays.asList(0.5, 0.5, 0.5, 0.5, 0.25)},
            {LocalDateTime.now(),100,Arrays.asList(1.0, 1.0, 0.5)},
            {LocalDateTime.now(),110,Arrays.asList(1.0, 1.0, 0.5, 0.25)},
            {LocalDateTime.now(),120,Arrays.asList(1.0, 1.0, 1.0)},
            {LocalDateTime.of(1997,1,21,8,50),30,Arrays.asList(0.25, 0.25, 0.25)},
            {LocalDateTime.of(1998,2,25,9,00),50,Arrays.asList(0.5, 0.5, 0.25)},
            {LocalDateTime.of(1999,3,26,10,20),100,Arrays.asList(1.0, 1.0, 0.5)},
            {LocalDateTime.of(2001,5,29,11,30),40,Arrays.asList(0.5, 0.5)},
            {LocalDateTime.of(2005,9,14,11,40),110,Arrays.asList(1.0, 1.0, 0.5, 0.25)},
            {LocalDateTime.of(2002,12,12,10,20),90,Arrays.asList(0.5, 0.5, 0.5, 0.5, 0.25)},
            {LocalDateTime.of(2003,11,11,9,50),70,Arrays.asList(1.0, 0.5, 0.25)},
            {LocalDateTime.of(2010,10,8,8,40),60,Arrays.asList(1.0, 0.5)},
            {LocalDateTime.of(2011,8,9,13,30),30,Arrays.asList(0.25, 0.25, 0.25)},
            {LocalDateTime.of(2002,7,3,14,40),110,Arrays.asList(1.0, 1.0, 0.5, 0.25)},
            {LocalDateTime.of(1999,9,2,15,10),70,Arrays.asList(1.0, 0.5, 0.25)},
            {LocalDateTime.of(2008,4,1,14,20),50,Arrays.asList(0.5, 0.5, 0.25)},
            {LocalDateTime.of(2004,3,4,13,50),40,Arrays.asList(0.5, 0.5)},
            {LocalDateTime.of(2005,2,18,15,40),120,Arrays.asList(1.0, 1.0, 1.0)},
            {LocalDateTime.of(2015,8,19,16,20),110,Arrays.asList(1.0, 1.0, 0.5, 0.25)},
            {LocalDateTime.of(2014,9,1,17,10),120,Arrays.asList(1.0, 1.0, 1.0)},
            {LocalDateTime.of(2013,6,2,18,00),80,Arrays.asList(1.0, 1.0)},
            {LocalDateTime.of(1997,2,11,18,00),70,Arrays.asList(1.0, 0.5, 0.25)},
            {LocalDateTime.of(1997,1,16,16,30),30,Arrays.asList(0.25, 0.25, 0.25)},
            {LocalDateTime.of(1998,4,17,17,40),120,Arrays.asList(1.0, 1.0, 1.0)},
            {LocalDateTime.of(1999,8,18,12,50),120,Arrays.asList(1.0, 1.0, 1.0)},
            {LocalDateTime.of(2000,10,10,18,30),110,Arrays.asList(1.0, 1.0, 0.5, 0.25)},
            {LocalDateTime.of(2000,11,11,12,10),100,Arrays.asList(1.0, 1.0, 0.5)},
        });
        return retorno;
    }
    
    public ParquimetroTestesComMoedas(LocalDateTime chegada, int saida, List moedas) {
        this.chegada = chegada;
        this.saida = saida;
        this.f = Facade.getInstance(chegada,chegada.plusMinutes(saida));
        this.moedas = moedas;
    }
    
    @Before
    public void zeraFacade(){
        f.voltaPadrao();
    }
    
    @Test
    public void TestesComMoedas() throws PagamentoException, ParquimetroException, TicketException{
        double saldoAnterior = f.getSaldoMaquina();
        
        double cont = 0;
        for(double d : moedas){
            f.insereMoeda(d);
            cont+=d;
        }
            
        
        Ticket t = f.geraTicket(cont);
        assertNotNull(t);
        assertEquals(saldoAnterior+cont,f.getSaldoMaquina(),0.0f);
        
    }
    
    public static void geraRelatorio() throws Exception{
        f.geraLogParquimetro();
    }
    
    
}
