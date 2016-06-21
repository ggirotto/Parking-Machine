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
public class ParquimetroTestesComCartao {
    
    private final LocalDateTime saida;
    private final Facade f;
    private final double saldo;
    private final double valorNecessario;
    
    @Parameters
    public static Collection data() {
        Collection retorno = Arrays.asList(new Object[][]{
            {LocalDateTime.now().plusMinutes(30),10,0.75},
            {LocalDateTime.now().plusMinutes(40),10,1.0},
            {LocalDateTime.now().plusMinutes(50),10,1.25},
            {LocalDateTime.now().plusMinutes(60),10,1.5},
            {LocalDateTime.now().plusMinutes(70),10,1.75},
            {LocalDateTime.now().plusMinutes(80),10,2.0},
            {LocalDateTime.now().plusMinutes(90),10,2.25},
            {LocalDateTime.now().plusMinutes(100),10,2.5},
            {LocalDateTime.now().plusMinutes(110),10,2.75},
            {LocalDateTime.now().plusMinutes(120),10,3.0},
            {LocalDateTime.now().plusMinutes(30),0.75,0.75},
            {LocalDateTime.now().plusMinutes(40),1.0,1.0},
            {LocalDateTime.now().plusMinutes(50),1.25,1.25},
            {LocalDateTime.now().plusMinutes(60),1.5,1.5},
            {LocalDateTime.now().plusMinutes(70),1.75,1.75},
            {LocalDateTime.now().plusMinutes(80),2.0,2.0},
            {LocalDateTime.now().plusMinutes(90),2.25,2.25},
            {LocalDateTime.now().plusMinutes(100),2.5,2.5},
            {LocalDateTime.now().plusMinutes(110),2.75,2.75},
            {LocalDateTime.now().plusMinutes(120),3.0,3.0}
        });        
        return retorno;
    }
    
    public ParquimetroTestesComCartao(LocalDateTime saida, double saldo, double valorNecessario) {
        this.saida = saida;
        this.f = Facade.getInstance(LocalDateTime.now(),saida);
        this.saldo = saldo;
        this.valorNecessario = valorNecessario;
    }
    
    @Before
    public void zeraFacade(){
        f.voltaPadrao();
    }
    
    @Test
    public void TesteComCartao() throws Exception{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel("01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768");
        cartao.deposita(saldo);
        f.cartaoInserido(cartao);
        Ticket t = f.geraTicket(valorNecessario);
        assertNotNull(t);
        assertEquals(saldo-valorNecessario,cartao.getSaldo(),0.0f);
        
    }
    
    
}
