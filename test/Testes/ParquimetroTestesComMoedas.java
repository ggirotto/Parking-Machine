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
    
    private final LocalDateTime saida;
    private final Facade f;
    private final List<Double> moedas;
    
    @Parameters
    public static Collection data() {
        Collection retorno = Arrays.asList(new Object[][]{
            {LocalDateTime.now().plusMinutes(30),Arrays.asList(0.25, 0.25, 0.25)},
            {LocalDateTime.now().plusMinutes(40),Arrays.asList(0.5, 0.5)},
            {LocalDateTime.now().plusMinutes(50),Arrays.asList(0.5, 0.5, 0.25)},
            {LocalDateTime.now().plusMinutes(60),Arrays.asList(1.0, 0.5)},
            {LocalDateTime.now().plusMinutes(70),Arrays.asList(1.0, 0.5, 0.25)},
            {LocalDateTime.now().plusMinutes(80),Arrays.asList(1.0, 1.0)},
            {LocalDateTime.now().plusMinutes(90),Arrays.asList(0.5, 0.5, 0.5, 0.5, 0.25)},
            {LocalDateTime.now().plusMinutes(100),Arrays.asList(1.0, 1.0, 0.5)},
            {LocalDateTime.now().plusMinutes(110),Arrays.asList(1.0, 1.0, 0.5, 0.25)},
            {LocalDateTime.now().plusMinutes(120),Arrays.asList(1.0, 1.0, 1.0)}
        });
        return retorno;
    }
    
    public ParquimetroTestesComMoedas(LocalDateTime saida, List moedas) {
        this.saida = saida;
        this.f = Facade.getInstance(LocalDateTime.now(),saida);
        this.moedas = moedas;
    }
    
    @Before
    public void zeraFacade(){
        f.zeraFacade();
    }
    
    @Test
    public void TestesComMoedas() throws PagamentoException, ParquimetroException, TicketException{
        
        double cont = 0;
        for(double d : moedas){
            f.insereMoeda(d);
            cont+=d;
        }
            
        
        Ticket t = f.geraTicket(cont);
        assertNotNull(t);
        assertEquals(cont,f.getSaldoMaquina(),0.0f);
        
    }
    
    
}
