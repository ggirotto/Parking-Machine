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
public class TestesParametrizadosComMoedas {
    
    private static Facade f;
    private final List<Double> moedas;
    
    @Parameters
    public static Collection data() {
        
        Collection retorno = new ArrayList<>();
        
        for(int i=0; i<= 250; i++){
            
            Random r = new Random();
            
            int tempo = 30+r.nextInt(10)*10;
            
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
            
            int hora = r.nextInt(11)+8;
            
            int minuto = r.nextInt(6)*10;
            
            
            if(hora==8)
                minuto=(r.nextInt(3)+3)*10;
            else if(hora == 18)
                minuto=r.nextInt(3)*10;
            
            int mes = r.nextInt(13-1)+1;
            
            int ano = r.nextInt(2017-1997)+1997;
            
            int dia;
            
            if(mes == 2 && ano%4==0) dia = r.nextInt(30-1)+1;
            else if(mes == 2 && ano%4!=0) dia = r.nextInt(29-1)+1;
            else if(mes == 4 || mes == 6 || mes == 9 || mes == 11) dia = r.nextInt(31-1)+1;
            else dia = r.nextInt(32-1)+1;
            
            LocalDateTime randomData = LocalDateTime.of(ano
                                                    ,mes
                                                    ,dia
                                                    ,hora
                                                    ,minuto);
            
            retorno.add(new Object[]{randomData ,tempo ,moedas});
        }
        return retorno;
    }
    
    public TestesParametrizadosComMoedas(LocalDateTime chegada, int saida, List moedas) {
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
        double troco = f.getTroco();
        
        if(t!=null)
            assertEquals(((saldoAnterior+cont)-troco),f.getSaldoMaquina(),0.0f);
    }
    
    public static void geraRelatorio() throws Exception{
        f.geraLogParquimetro();
    }
    
    
}
