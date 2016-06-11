package Testes;

import Ticket.*;
import org.junit.*;
import Dominio.*;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class ParquimetroTest {
    private CoinCollector coinMachine;

    @Before
    public void setUp() throws PagamentoException {
        coinMachine = CoinCollector.getInstance();
    }
    
    @After
    public void zeraSaldo(){
        coinMachine.zeraSaldo();
    }
    
    @Test(expected = PagamentoException.class)
    public void testInsereMoedaErrada() throws PagamentoException{
        coinMachine.insereMoeda(0.01);
    }
    
    @Test
    public void simulaTempoMinimoComValorCorreto() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);
        Facade f = new Facade(LocalDateTime.now(),saida);
        f.insereMoeda(0.25);
        f.insereMoeda(0.25);
        f.insereMoeda(0.25);
        
        Ticket t = f.geraTicket(0.75);
        assertNotNull(t);
        assertEquals(0.75f,coinMachine.getSaldo(),0.0f);
        
    }
    
    @Test(expected = PagamentoException.class)
    public void simulaTempoMinimoComValorInferior() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);
        Facade fachada = new Facade(LocalDateTime.now(),saida);
        fachada.insereMoeda(0.25);
        fachada.insereMoeda(0.25);
        
        Ticket t = fachada.geraTicket(0.50);
    }
    
    @Test
    public void simulaTempoMaximoComValorCorreto() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = new Facade(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        
        Ticket t = fachada.geraTicket(3.0);
        assertNotNull(t);
        assertEquals(3.0f,coinMachine.getSaldo(),0.0f);
        
    }
    
    @Test(expected = PagamentoException.class)
    public void simulaTempoMaximoComValorInferior() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = new Facade(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(0.05);
        
        Ticket t = fachada.geraTicket(2.95);
    }
    
    @Test
    public void simulaTempoIntermediarioComValorCorreto() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(80);
        Facade fachada = new Facade(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        
        Ticket t = fachada.geraTicket(2.0);
        assertNotNull(t);
        assertEquals(2.0f,coinMachine.getSaldo(),0.0f);
        
    }
    
    @Test(expected = PagamentoException.class)
    public void simulaTempoIntermediarioComValorInferior() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(80);
        Facade fachada = new Facade(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(0.5);
        
        Ticket t = fachada.geraTicket(1.5);
        
    }
    
    @Test
    public void simulaTrocoCorreto() throws Exception{
        
        
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(90);
        Facade fachada = new Facade(LocalDateTime.now(),saida);
        // Adiciona para a máquina possuir moeda para dar de troco
        fachada.insereMoeda(0.25);
        coinMachine.arrumaSaldo();
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(0.5);
        
        Ticket t = fachada.geraTicket(2.5);
        assertNotNull(t);
        assertEquals(2.50f,coinMachine.getSaldo(),0.0f);
        
    }
    
    @Test
    public void simulaTrocoComMaquinaSemMoedas() throws Exception{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(90);
        Facade fachada = new Facade(LocalDateTime.now(),saida);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(1.0);
        fachada.insereMoeda(0.5);
        
        Ticket t = fachada.geraTicket(2.5);
        assertNotNull(t);
        assertEquals(2.50f,coinMachine.getSaldo(),0.0f);
        
    }
    
    @Test
    public void simulaUsoDoCartao() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel();
        cartao.deposita(3);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = new Facade(LocalDateTime.now(),saida);
        fachada.cartaoInserido(cartao);
        Ticket t = fachada.geraTicket(3);
        assertNotNull(t);
        assertEquals(0,cartao.getSaldo(),0.0f);
        
    }
    
    @Test(expected = PagamentoException.class)
    public void simulaUsoDoCartaoComSaldoInsuficiente() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel();
        cartao.deposita(2);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = new Facade(LocalDateTime.now(),saida);
        fachada.cartaoInserido(cartao);
        Ticket t = fachada.geraTicket(3);
        
    }
    
    @Test
    public void simulaUsoDoCartaoComSaldoSuperior() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel();
        cartao.deposita(10);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = new Facade(LocalDateTime.now(),saida);
        fachada.cartaoInserido(cartao);
        Ticket t = fachada.geraTicket(3);
        assertNotNull(t);
        assertEquals(7.0f,cartao.getSaldo(),0.0f);
        fachada.geraLogParquimetro();
        
    }
    
    
}
