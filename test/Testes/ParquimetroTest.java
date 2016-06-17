package Testes;

import org.junit.Before;
import org.junit.Test;
import Dominio.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParquimetroTest {
    
    private int param;
    private int result;
    
    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{ {10, 0}, {9, 1}, {11, -1}, {4, -5} });
    }
    
    public void TesteParametrizado(int param, int result) {
        this.param = param;
        this.result = result;
    }
    
    @Before
    public void zeraFacade(){
        Facade f = Facade.getInstance(LocalDateTime.MAX, LocalDateTime.MIN);
        f.zeraFacade();
    }
    
    @Test
    public void simulaTempoMinimoComValorCorreto() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);
        Facade f = Facade.getInstance(LocalDateTime.now(),saida);
        f.insereMoeda(0.25);
        f.insereMoeda(0.25);
        f.insereMoeda(0.25);
        
        Ticket t = f.geraTicket(0.75);
        assertNotNull(t);
        assertEquals(0.75f,f.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaTempoMaximoComValorCorreto() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        
        Ticket t = fachada.geraTicket(3.0);
        assertNotNull(t);
        assertEquals(3.0f,fachada.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaTempoIntermediarioComValorCorreto() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(80);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        
        Ticket t = fachada.geraTicket(2.0);
        assertNotNull(t);
        assertEquals(2.0f,fachada.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaTrocoCorreto() throws Exception{

        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        // Adiciona para a máquina possuir moeda para dar de troco
        fachada.insereMoeda(0.25);
        fachada.insereMoeda(0.25);
        fachada.insereMoeda(0.25);
        Ticket t = fachada.geraTicket(2.5);
        saida = LocalDateTime.now().plusMinutes(90);
        fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(0.5);
        
        t = fachada.geraTicket(2.5);
        assertNotNull(t);
        assertEquals(3.0f,fachada.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaTrocoComMaquinaSemMoedas() throws Exception{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(90);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(0.5);
        
        Ticket t = fachada.geraTicket(2.5);
        assertNotNull(t);
        assertEquals(2.50f,fachada.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaUsoDoCartao() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel("01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768");
        cartao.deposita(3);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.cartaoInserido(cartao);
        Ticket t = fachada.geraTicket(3);
        assertNotNull(t);
        assertEquals(0,cartao.getSaldo(),0.0f);
        
    }
    
    @Test
    public void simulaUsoDoCartaoComSaldoSuperior() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel("01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768");
        cartao.deposita(10);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.cartaoInserido(cartao);
        Ticket t = fachada.geraTicket(3);
        assertNotNull(t);
        assertEquals(7.0f,cartao.getSaldo(),0.0f);
        fachada.geraLogParquimetro();
        
    }
    
    
}
