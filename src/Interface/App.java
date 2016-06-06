package Interface;

import Dominio.*;
import java.time.LocalDateTime;
import java.time.Month;

public class App {
    public static void main(String args[])throws Exception{
        double valorPago = 0;
        CoinCollector coinMachine = CoinCollector.getInstance();
        
        coinMachine.deposita(0.25);
        valorPago+=0.25;
        coinMachine.deposita(0.25);
        valorPago+=0.25;
        coinMachine.deposita(0.25);
        valorPago+=0.25;
        
        LocalDateTime saida1 = LocalDateTime.of(2016, Month.JUNE, 3, 12, 30);
        LocalDateTime saida = LocalDateTime.of(2016, Month.JUNE, 3, 13, 00);
        
        Facade f = new Facade(coinMachine,saida1,valorPago);
        f.geraTicket();
        
        valorPago = 0;
        coinMachine.deposita(1.0);
        coinMachine.deposita(1.0);
        valorPago += 2;
        
        Facade p = new Facade(coinMachine,saida,valorPago);
        p.geraTicket();

        System.out.println("Saldo Maquina: " + coinMachine.getSaldo());
        CartaoRecarregavel card = new CartaoRecarregavel();
        System.out.println(card.getId());
    }
}
