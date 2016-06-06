package Interface;

import Dominio.*;
import java.time.LocalDateTime;
import java.time.Month;

public class App {
    public static void main(String args[])throws Exception{
        CartaoRecarregavel cartao = new CartaoRecarregavel("1");
        CoinCollector coinMachine = new CoinCollector();
        coinMachine.deposita(0.25);
        coinMachine.deposita(0.25);
        coinMachine.deposita(0.25);
        coinMachine.deposita(0.50);
        System.out.println("debug");
        cartao.deposita(1);
        LocalDateTime saida = LocalDateTime.of(2016, Month.JUNE, 3, 12, 30);
        Facade f = new Facade(coinMachine,saida);
        f.geraTicket();
        System.out.println("Saldo Cartao: " + cartao.getSaldo());
        System.out.println("Saldo Maquina: " + coinMachine.getSaldo());
        
    }
}
