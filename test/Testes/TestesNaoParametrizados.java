package Testes;

import org.junit.*;
import Dominio.*;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class TestesNaoParametrizados {
    
    private static Facade f;
    
    @Before
    public void zeraFachada(){
        Facade.getInstance(LocalDateTime.MAX, LocalDateTime.MIN).voltaPadrao();
    }
    
    @Test
    public void simularInsercaoDasMoedas() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);
        f = Facade.getInstance(LocalDateTime.now(),saida);
        
        try{
            f.insereMoeda(0.05);
            f.insereMoeda(0.1);
            f.insereMoeda(0.25);
            f.insereMoeda(0.5);
            f.insereMoeda(1.0);
        }catch(PagamentoException e){
            throw e;
        }
        
        Ticket t = f.geraTicket(1.9);
        
        assertNotNull(t);
        
    }
    
    @Test
    public void simulaInsercaoDoCartao() throws PagamentoException{
        
        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);
        f = Facade.getInstance(LocalDateTime.now(),saida);
        
        CartaoRecarregavel card = new CartaoRecarregavel("01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768");
        f.cartaoInserido(card);
        
        assertNotNull(f.getCartao());
    }
    
    
    @Test
    public void simulaTempoMinimo() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime chegada = LocalDateTime.of(2016, 5, 5, 12, 00);
        f = Facade.getInstance(chegada,chegada.plusMinutes(30));
        
        double saldoAnterior = f.getSaldoMaquina();
        
        double cont = 0;
        
        f.insereMoeda(0.25);
        cont+=0.25;
        f.insereMoeda(0.25);
        cont+=0.25;
        f.insereMoeda(0.25);
        cont+=0.25;
        
        Ticket t = f.geraTicket(cont);
        double troco = f.getTroco();
        
        
        assertNotNull(t);
        assertEquals(((saldoAnterior+cont)-troco),f.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaTempoMaximo() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime chegada = LocalDateTime.of(2016, 5, 5, 12, 00);
        f = Facade.getInstance(chegada,chegada.plusMinutes(120));
        
        double saldoAnterior = f.getSaldoMaquina();
        
        double cont = 0;
        
        f.insereMoeda(1.0);
        cont+=1;
        f.insereMoeda(1.0);
        cont+=1;
        f.insereMoeda(1.0);
        cont+=1;
        
        Ticket t = f.geraTicket(cont);
        double troco = f.getTroco();
        
        
        assertNotNull(t);
        assertEquals(((saldoAnterior+cont)-troco),f.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaTempoIntermediario() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime chegada = LocalDateTime.of(2016, 5, 5, 12, 00);
        f = Facade.getInstance(chegada,chegada.plusMinutes(80));
        
        double saldoAnterior = f.getSaldoMaquina();
        
        double cont = 0;
        
        f.insereMoeda(1.0);
        cont+=1;
        f.insereMoeda(1.0);
        cont+=1;
        
        Ticket t = f.geraTicket(cont);
        double troco = f.getTroco();
        
        
        assertNotNull(t);
        assertEquals(((saldoAnterior+cont)-troco),f.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaSaidaAposFimTarifacao() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime chegada = LocalDateTime.of(2016, 5, 5, 18, 00);
        f = Facade.getInstance(chegada,chegada.plusMinutes(50));
        
        double saldoAnterior = f.getSaldoMaquina();
        
        double cont = 0;
        
        f.insereMoeda(1.0);
        cont+=1;
        f.insereMoeda(0.25);
        cont+=0.25;
        
        Ticket t = f.geraTicket(cont);
        double troco = f.getTroco();
        
        
        assertNotNull(t);
        assertEquals(((saldoAnterior+cont)-troco),f.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaTempoForaDeTarifacao() throws PagamentoException, ParquimetroException, TicketException{
        
        LocalDateTime chegada = LocalDateTime.of(2016, 5, 5, 19, 00);
        f = Facade.getInstance(chegada,chegada.plusMinutes(30));
        
        double saldoAnterior = f.getSaldoMaquina();
        
        double cont = 0;
        
        f.insereMoeda(0.25);
        cont+=0.25;
        f.insereMoeda(0.25);
        cont+=0.25;
        f.insereMoeda(0.25);
        cont+=0.25;
        
        Ticket t = f.geraTicket(cont);
        double troco = f.getTroco();
        
        
        assertNull(t);
        assertEquals(saldoAnterior,f.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaTrocoCorreto() throws Exception{

        LocalDateTime chegada = LocalDateTime.of(2016, 5, 5, 15, 00);
        f = Facade.getInstance(chegada,chegada.plusMinutes(30));
        
        // Adiciona para a máquina possuir moeda para dar de troco
        f.insereMoeda(0.25);
        f.insereMoeda(0.25);
        f.insereMoeda(0.25);
        
        Ticket t = f.geraTicket(0.75);
        
        chegada = LocalDateTime.of(2016, 5, 5, 16, 00);
        f = Facade.getInstance(chegada,chegada.plusMinutes(90));
        
        double saldoAnterior = f.getSaldoMaquina();
        
        double cont = 0;
        
        f.insereMoeda(1.0);
        cont+=1;
        f.insereMoeda(1.0);
        cont+=1;
        f.insereMoeda(0.5);
        cont+=0.5;
        
        t = f.geraTicket(cont);
        double troco = f.getTroco();
        
        
        assertNotNull(t);
        assertEquals(((saldoAnterior+cont)-troco),f.getSaldoMaquina(),0.0f);
        assertEquals(0.25f,troco,0.0f);
        
    }
    
    @Test
    public void simulaTrocoComMaquinaSemMoedas() throws Exception{
        
        LocalDateTime chegada = LocalDateTime.of(2016, 5, 5, 15, 00);
        f = Facade.getInstance(chegada,chegada.plusMinutes(90));
        f.zeraSaldo();
        
        double saldoAnterior = f.getSaldoMaquina();
        
        double cont = 0;
 
        f.insereMoeda(1.0);
        cont+=1.0;
        f.insereMoeda(1.0);
        cont+=1.0;
        f.insereMoeda(0.5);
        cont+=0.5;
        
        Ticket t = f.geraTicket(cont);
        
        assertNotNull(t);
        assertEquals(cont,f.getSaldoMaquina(),0.0f);
        
    }
    
    @Test
    public void simulaUsoDoCartao() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel("01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768");
        cartao.deposita(3);
        
        LocalDateTime chegada = LocalDateTime.of(2016, 5, 5, 15, 00);
        f = Facade.getInstance(chegada,chegada.plusMinutes(120));
        
        f.cartaoInserido(cartao);
        Ticket t = f.geraTicket(3);
        
        assertNotNull(t);
        assertEquals(0,cartao.getSaldo(),0.0f);
        
    }
    
    @Test
    public void simulaUsoDoCartaoComSaldoSuperior() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel("01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768");
        cartao.deposita(10);
        
        LocalDateTime chegada = LocalDateTime.of(2016, 5, 5, 15, 00);
        f = Facade.getInstance(chegada,chegada.plusMinutes(120));
        
        f.cartaoInserido(cartao);
        Ticket t = f.geraTicket(3);
        
        assertNotNull(t);
        assertEquals(7.0f,cartao.getSaldo(),0.0f);
        //f.geraLogParquimetro();
        
    }
    
    public static void geraRelatorio() throws Exception{
        f.geraLogParquimetro();
    }
    
    
}