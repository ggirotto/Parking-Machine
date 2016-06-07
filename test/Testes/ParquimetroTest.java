package Testes;

import org.junit.*;
import Dominio.*;
import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;

public class ParquimetroTest {
    private Facade fachada;
    private CoinCollector coinMachine;

    @Before
    public void setUp() throws PagamentoException {
        coinMachine = CoinCollector.getInstance();
        fachada = new Facade(coinMachine,LocalDateTime.now());
        coinMachine.deposita(1.0);
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
        assertEquals(2.90, coinMachine.getSaldo(), 0.01f);
    }
    
    @Test(expected = PagamentoException.class)
    public void testInsereMoedaErrada() throws PagamentoException{
        coinMachine.deposita(0.01);
    }

    @Test
    public void testGetSaldo() {
        double actual = coinMachine.getSaldo();
        assertEquals(1.0, actual, 0.0f);
    }

    @Test
    public void testTroco() throws PagamentoException{
        coinMachine.deposita(0.50);
        coinMachine.deposita(0.50);
        coinMachine.deposita(1.0);
        double troco = coinMachine.getTroco(1);
        assertEquals(1.0f, troco, 0.0f);
        double saldo = coinMachine.getSaldo();
        assertEquals(2.0f, saldo, 0.0f);
    }
}
