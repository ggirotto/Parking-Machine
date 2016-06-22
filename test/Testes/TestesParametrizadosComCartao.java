package Testes;

import org.junit.Before;
import org.junit.Test;
import Dominio.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestesParametrizadosComCartao {
    
    private final LocalDateTime saida;
    private static Facade f;
    private final double saldo;
    private final double valorNecessario;
    
    @Parameters
    public static Collection data() {
        
        Collection retorno = new ArrayList<>();
        
        for(int i=0; i<= 250; i++){
            
            Random r = new Random();
            
            int saldo = r.nextInt(15-10)+10;
            
            int tempo = 30+r.nextInt(10)*10;
            
            double valorNecessario = 0;
            
            if(tempo == 30) valorNecessario = 0.75;
            if(tempo == 40) valorNecessario = 1.0;
            if(tempo == 50) valorNecessario = 1.25;
            if(tempo == 60) valorNecessario = 1.50;
            if(tempo == 70) valorNecessario = 1.75;
            if(tempo == 80) valorNecessario = 2.00;
            if(tempo == 90) valorNecessario = 2.25;
            if(tempo == 100) valorNecessario = 2.50;
            if(tempo == 110) valorNecessario = 2.75;
            if(tempo == 120) valorNecessario = 3.0;
            
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
            
            retorno.add(new Object[]{randomData, tempo ,saldo ,valorNecessario});
        }
        return retorno;
    }
    
    public TestesParametrizadosComCartao(LocalDateTime chegada, int tempo, double saldo, double valorNecessario) {
        this.saida = chegada.plusMinutes(tempo);
        this.f = Facade.getInstance(chegada,chegada.plusMinutes(tempo));
        this.saldo = saldo;
        this.valorNecessario = valorNecessario;
        
    }
    
    @Before
    public void zeraFacade(){
        f.voltaPadrao();
    }
    
    @Test
    public void TesteComCartao() throws PagamentoException, ParquimetroException, TicketException{
        
        CartaoRecarregavel cartao = new CartaoRecarregavel("01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768");
        cartao.deposita(saldo);
        f.cartaoInserido(cartao);
        Ticket t = f.geraTicket(valorNecessario);
        if(t!=null && saida.toLocalTime().isBefore(LocalTime.of(18, 30)))
            assertEquals(saldo-valorNecessario,cartao.getSaldo(),0.0f);
        
    }
    
    public static void geraRelatorio() throws Exception{
        f.geraLogParquimetro();
    }
    
    
}
