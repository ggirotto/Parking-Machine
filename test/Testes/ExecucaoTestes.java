/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestesParametrizadosComMoedas.class, TestesParametrizadosComCartao.class })
public class ExecucaoTestes {
    
    @AfterClass
    public static void geraRelatorio() throws Exception{
        TestesParametrizadosComMoedas.geraRelatorio();
    }
}
