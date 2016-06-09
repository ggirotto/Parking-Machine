package Testes;

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

    @Test
    public void testInsereMoeda() throws PagamentoException{
        coinMachine.deposita(0.05);
        coinMachine.deposita(0.10);
        coinMachine.deposita(0.25);
        coinMachine.deposita(0.50);
        coinMachine.deposita(1.0);
        assertEquals(1.9, coinMachine.getSaldo(), 0.01f);
    }
    
    @Test(expected = PagamentoException.class)
    public void testInsereMoedaErrada() throws PagamentoException{
        coinMachine.deposita(0.01);
    }

    @Test
    public void testGetSaldo() throws PagamentoException{
        coinMachine.deposita(0.5);
        coinMachine.deposita(0.05);
        double actual = coinMachine.getSaldo();
        assertEquals(0.55, actual, 0.0f);
    }

    @Test
    public void testTroco() throws PagamentoException{
        
        coinMachine.deposita(0.50);
        coinMachine.deposita(0.50);
        coinMachine.deposita(1.0);
        double troco = coinMachine.getTroco(1);
        assertEquals(1.0f, troco, 0.0f);
        double saldo = coinMachine.getSaldo();
        assertEquals(1.0f, saldo, 0.0f);
        
    }
    
    @Test
    public void simulaTempoMinimoComValorCorreto() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);
        coinMachine.deposita(0.25);
        coinMachine.deposita(0.25);
        coinMachine.deposita(0.25);
        Facade fachada = new Facade(coinMachine,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(0.75);
        assertNotNull(t);
        assertEquals(0.75f,coinMachine.getSaldo(),0.0f);
        fachada.geraLogParquimetro();
        
    }
    
    @Test(expected = PagamentoException.class)
    public void simulaTempoMinimoComValorInferior() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);
        coinMachine.deposita(0.25);
        coinMachine.deposita(0.25);
        Facade fachada = new Facade(coinMachine,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(0.50);
    }
    
    @Test
    public void simulaTempoMaximoComValorCorreto() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        coinMachine.deposita(1.0);
        coinMachine.deposita(1.0);
        coinMachine.deposita(1.0);
        Facade fachada = new Facade(coinMachine,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(3.0);
        assertNotNull(t);
        assertEquals(3.0f,coinMachine.getSaldo(),0.0f);
        fachada.geraLogParquimetro();
        
    }
    
    @Test(expected = PagamentoException.class)
    public void simulaTempoMaximoComValorInferior() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        coinMachine.deposita(1.0);
        coinMachine.deposita(1.0);
        coinMachine.deposita(0.05);
        Facade fachada = new Facade(coinMachine,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(2.95);
    }
    
    @Test
    public void simulaTempoIntermediarioComValorCorreto() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(80);
        coinMachine.deposita(1.0);
        coinMachine.deposita(1.0);
        Facade fachada = new Facade(coinMachine,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(2.0);
        assertNotNull(t);
        assertEquals(2.0f,coinMachine.getSaldo(),0.0f);
        fachada.geraLogParquimetro();
        
    }
    
    @Test(expected = PagamentoException.class)
    public void simulaTempoIntermediarioComValorInferior() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(80);
        coinMachine.deposita(1.0);
        coinMachine.deposita(0.5);
        Facade fachada = new Facade(coinMachine,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(1.5);
        
    }
    
    @Test
    public void simulaTrocoCorreto() throws Exception{
        
        // Adiciona para a máquina possuir moeda para dar de troco
        coinMachine.deposita(0.25);
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(90);
        coinMachine.deposita(1.0);
        coinMachine.deposita(1.0);
        coinMachine.deposita(0.5);
        Facade fachada = new Facade(coinMachine,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(2.5);
        assertNotNull(t);
        assertEquals(2.50f,coinMachine.getSaldo(),0.0f);
        fachada.geraLogParquimetro();
        
    }
    
    @Test
    public void simulaTrocoComMaquinaSemMoedas() throws Exception{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(90);
        coinMachine.deposita(1.0);
        coinMachine.deposita(1.0);
        coinMachine.deposita(0.5);
        Facade fachada = new Facade(coinMachine,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(2.5);
        assertNotNull(t);
        assertEquals(2.50f,coinMachine.getSaldo(),0.0f);
        fachada.geraLogParquimetro();
        
    }
    
    @Test
    public void simulaUsoDoCartao() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel();
        cartao.deposita(3);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = new Facade(cartao,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(3);
        assertNotNull(t);
        assertEquals(0,cartao.getSaldo(),0.0f);
        fachada.geraLogParquimetro();
        
    }
    
    @Test(expected = PagamentoException.class)
    public void simulaUsoDoCartaoComSaldoInsuficiente() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel();
        cartao.deposita(2);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = new Facade(cartao,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(3);
        
    }
    
    @Test
    public void simulaUsoDoCartaoComSaldoSuperior() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel();
        cartao.deposita(10);
        LocalDateTime saida = LocalDateTime.now().plusMinutes(120);
        Facade fachada = new Facade(cartao,LocalDateTime.now(),saida);
        Ticket t = fachada.geraTicket(3);
        assertNotNull(t);
        assertEquals(7.0f,cartao.getSaldo(),0.0f);
        fachada.geraLogParquimetro();
        
    }
    
    
}
