package Testes;

import org.junit.Before;
import org.junit.Test;
import Dominio.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParquimetroTestesComMoedas {
    
    private static Facade f;
    private final List<Double> moedas;
    
    @Parameters
    public static Collection data() {
        
        Collection retorno = new ArrayList<>();
        
        for(int i=0; i<= 250; i++){
            
            Random r = new Random();
            
            int minuto = r.nextInt(51-0)+0;
            while(minuto%10 != 0) minuto = r.nextInt(51-0)+0;
            
            int tempo = r.nextInt(121-30)+30;
            while(tempo%10 != 0) tempo = r.nextInt(121-30)+30;
            
            List moedas = null;
            
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
            LocalDateTime randomData = LocalDateTime.of(r.nextInt(2017-1997)+1997
                                                    ,r.nextInt(13-1)+1
                                                    ,r.nextInt(29-1)+1
                                                    ,r.nextInt(24-0)+0
                                                    ,minuto);
            
            retorno.add(new Object[]{randomData ,tempo ,moedas});
        }
        return retorno;
    }
    
    public ParquimetroTestesComMoedas(LocalDateTime chegada, int saida, List moedas) {
        this.f = Facade.getInstance(chegada,chegada.plusMinutes(saida));
        this.moedas = moedas;
    }
    
    @Before
    public void zeraFacade(){
        f.voltaPadrao();
    }
    
    @Test
    public void TestesComMoedas() throws PagamentoException, ParquimetroException, TicketException{
        
        double saldoAnterior = f.getSaldoMaquina();
        
        double cont = 0;
        for(double d : moedas){
            f.insereMoeda(d);
            cont+=d;
        }
        
        Ticket t = f.geraTicket(cont);
        if(t!=null)
            assertEquals(saldoAnterior+cont,f.getSaldoMaquina(),0.0f);
    }
    
    public static void geraRelatorio() throws Exception{
        f.geraLogParquimetro();
    }
    
    
}
