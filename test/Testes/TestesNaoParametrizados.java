package Testes;

import org.junit.*;
import Dominio.*;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class TestesNaoParametrizados {
    
    @Test(expected = PagamentoException.class)
    public void testInsereMoedaErrada() throws PagamentoException{
        Facade f = Facade.getInstance(LocalDateTime.now(),LocalDateTime.now());
        f.insereMoeda(0.01);
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
    
    @Test(expected = PagamentoException.class)
    public void simulaTempoMinimoComValorInferior() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.insereMoeda(0.25);
        fachada.insereMoeda(0.25);
        
        Ticket t = fachada.geraTicket(0.50);
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
    
    @Test(expected = PagamentoException.class)
    public void simulaTempoMaximoComValorInferior() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(0.05);
        
        Ticket t = fachada.geraTicket(2.95);
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
    
    @Test(expected = PagamentoException.class)
    public void simulaTempoIntermediarioComValorInferior() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(80);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(0.5);
        
        Ticket t = fachada.geraTicket(1.5);
        
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
        assertEquals(2.50f,fachada.getSaldoMaquina(),0.0f);
        
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
        
        CartaoRecarregavel cartao = new CartaoRecarregavel("1");
        cartao.deposita(3);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.cartaoInserido(cartao);
        Ticket t = fachada.geraTicket(3);
        assertNotNull(t);
        assertEquals(0,cartao.getSaldo(),0.0f);
        
    }
    
    @Test(expected = PagamentoException.class)
    public void simulaUsoDoCartaoComSaldoInsuficiente() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel("2");
        cartao.deposita(2);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.cartaoInserido(cartao);
        Ticket t = fachada.geraTicket(3);
        
    }
    
    @Test(expected = PagamentoException.class)
    public void simulaUsoDoCartaoComSaldoInsuficientePor1Centavo() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel("3");
        cartao.deposita(2.99);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = Facade.getInstance(LocalDateTime.now(),saida);
        fachada.cartaoInserido(cartao);
        Ticket t = fachada.geraTicket(3);
        
    }
    
    @Test
    public void simulaUsoDoCartaoComSaldoSuperior() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel("4");
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