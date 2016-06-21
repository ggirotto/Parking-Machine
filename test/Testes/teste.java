package Testes;

import Dominio.Facade;
import Dominio.PagamentoException;
import Dominio.ParquimetroException;
import Dominio.Ticket;
import Dominio.TicketException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class teste {
    
    public static Facade f;
    public static double saldoAnterior;
    
    public static void main(String args[]) throws PagamentoException, ParquimetroException, TicketException, Exception{
        LocalDateTime chegada;
        int saida;
        Facade f;
        List<Double> moedas = null;
        
        for(int i=0; i<= 250; i++){
            Random r = new Random();
            int minuto = r.nextInt(51-0)+0;
            while(minuto%10 != 0) minuto = r.nextInt(51-0)+0;
            int tempo = r.nextInt(121-30)+30;
            while(tempo%10 != 0) tempo = r.nextInt(121-30)+30;
            switch(tempo){
                case 30:
                    moedas = Arrays.asList(0.25, 0.25, 0.25);
                    break;
                case 40:
                    moedas = Arrays.asList(0.5, 0.5);
                    break;
                case 50:
                    moedas = Arrays.asList(0.5, 0.5, 0.25);
                    break;
                case 60:
                    moedas = Arrays.asList(1.0, 0.5);
                    break;
                case 70:
                    moedas = Arrays.asList(1.0, 0.5, 0.25);
                    break;
                case 80:
                    moedas = Arrays.asList(1.0, 1.0);
                    break;
                case 90:
                    moedas = Arrays.asList(1.0, 1.0, 0.25);
                    break;
                case 100:
                    moedas = Arrays.asList(1.0, 1.0, 0.5);
                    break;
                case 110:
                    moedas = Arrays.asList(1.0, 1.0, 0.5, 0.25);
                    break;
                case 120:
                    moedas = Arrays.asList(1.0, 1.0, 1.0);
                    break;
                
            }
            LocalDateTime randomData = LocalDateTime.of(2016
                                                    ,5
                                                    ,15
                                                    ,23
                                                    ,30);
            
            testaAi(randomData,50,moedas);
        }
        
    }
    
    private static void testaAi(LocalDateTime randomData, int tempo, List<Double> moedas) throws PagamentoException, ParquimetroException, TicketException, Exception{
        
        
        f = Facade.getInstance(randomData, randomData.plusMinutes(tempo));
        
        
        double cont = 0;
        for(double d : moedas){
            f.insereMoeda(d);
            cont+=d;
        }
        
        Ticket t = f.geraTicket(cont);
        
        if(saldoAnterior+cont != f.getSaldoMaquina()) throw new Exception("Não sao Iguais: Saldo( "+f.getSaldoMaquina()+") - Esperado(" + saldoAnterior+cont + ")");
        
        saldoAnterior = f.getSaldoMaquina();
    }

}
