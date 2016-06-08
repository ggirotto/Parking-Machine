package App;

import Dominio.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class App {
    public static void main(String args[])throws Exception{
        

        CoinCollector coinMachine = CoinCollector.getInstance();
        LocalDateTime chegada = LocalDateTime.now();
        LocalDateTime saida = chegada.plusMinutes(30);
        
        Scanner in = new Scanner(System.in);
        String op = "";
        
        while((!op.equals("pagar"))){
            
            op = in.nextLine();
            
            switch(op){
                case "+":
                    saida = saida.plusMinutes(10);
                    System.out.println("Tempo de estadia (minutos): " + diferencaTempo(chegada, saida));
                    break;
                case "-":
                    saida = saida.minusMinutes(10);
                    System.out.println("Tempo de estadia (minutos): " + diferencaTempo(chegada, saida));
                    break;
            }
        }
        
        System.out.println("Moedas: 1)0.05, 2)0.1, 3)0.25, 4)0.5, 5)1.0");
        
        while((!op.equals("pronto"))){
            
            op = in.nextLine();
            
            switch(op){
                case "1":
                    coinMachine.deposita(0.05);
                    System.out.println("Total Pago: " + coinMachine.getSaldo());
                    break;
                case "2":
                    coinMachine.deposita(0.1);
                    System.out.println("Total Pago: " + coinMachine.getSaldo());
                    break;
                case "3":
                    coinMachine.deposita(0.25);
                    System.out.println("Total Pago: " + coinMachine.getSaldo());
                    break;
                case "4":
                    coinMachine.deposita(0.5);
                    System.out.println("Total Pago: " + coinMachine.getSaldo());
                    break;
                case "5":
                    coinMachine.deposita(1.0);
                    System.out.println("Total Pago: " + coinMachine.getSaldo());
                    break;
            }
        }
        
        Facade f = new Facade(coinMachine,chegada,saida);
        f.geraTicket(coinMachine.getSaldo());
        f.geraLogParquimetro();
        System.out.println("Saldo Maquina: " + coinMachine.getSaldo());
    }
    
    /*
        Retorna a diferença de tempo em minutos entre a chegada e a hora de saida
    */
    private static int diferencaTempo(LocalDateTime chegada, LocalDateTime saida){
        
        return ((saida.getHour() * 60) + saida.getMinute()) -
                        ((chegada.getHour() * 60) + chegada.getMinute());
        
    }
}
