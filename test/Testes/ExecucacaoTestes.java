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
@SuiteClasses({ParquimetroTestesComMoedas.class, ParquimetroTestesComCartao.class })
public class ExecucacaoTestes {
    
    @AfterClass
    public static void geraRelatorio() throws Exception{
        ParquimetroTestesComMoedas.geraRelatorio();
    }
}
