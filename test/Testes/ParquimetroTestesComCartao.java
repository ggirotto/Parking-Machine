package Testes;

import org.junit.Before;
import org.junit.Test;
import Dominio.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParquimetroTestesComCartao {
    
    private final Facade f;
    private final double saldo;
    private final double valorNecessario;
    
    @Parameters
    public static Collection data() {
        
        Collection retorno = new ArrayList<>();
        
        for(int i=0; i<= 10; i++){
            
            Random r = new Random();
            
            int saldo = r.nextInt(15-10)+10;
            
            int minuto = r.nextInt(51-10)+10;
            while(minuto%10 != 0) minuto = r.nextInt(51-0)+0;
            
            int tempo = r.nextInt(121-30)+30;
            while(tempo%10 != 0) tempo = r.nextInt(121-30)+30;
            
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
            
            LocalDateTime randomData = LocalDateTime.of(r.nextInt(2017-1997)+1997
                                                    ,r.nextInt(13-1)+1
                                                    ,r.nextInt(29-1)+1
                                                    ,r.nextInt(24-0)+0
                                                    ,minuto);
            
            retorno.add(new Object[]{randomData, tempo ,saldo ,valorNecessario});
        }
        return retorno;
    }
    
    public ParquimetroTestesComCartao(LocalDateTime chegada, int saida, double saldo, double valorNecessario) {
        
        this.f = Facade.getInstance(chegada,chegada.plusMinutes(saida));
        this.saldo = saldo;
        this.valorNecessario = valorNecessario;
        
    }
    
    @Before
    public void zeraFacade(){
        f.voltaPadrao();
    }
    
    @Test
    public void TesteComCartao() throws Exception{
        System.out.println("Saldo: "+saldo+" - Valor: "+valorNecessario);
        CartaoRecarregavel cartao = new CartaoRecarregavel("01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768");
        cartao.deposita(saldo);
        f.cartaoInserido(cartao);
        Ticket t = f.geraTicket(valorNecessario);
        if(t!=null)
            assertEquals(saldo-valorNecessario,cartao.getSaldo(),0.0f);
        
    }
    
    
}
