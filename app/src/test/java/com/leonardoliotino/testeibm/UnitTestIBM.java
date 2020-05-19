package com.leonardoliotino.testeibm;

import com.leonardoliotino.testeibm.controller.validar.CPF;
import com.leonardoliotino.testeibm.controller.validar.Valida;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTestIBM {

    @Test
    public void validaEmail() {
        boolean flag = Valida.isEmail("LEO@GMAIL.COM");
        assertTrue(flag);
    }

    @Test
    public void validaCPF() {
        boolean flag = CPF.isCPF("46531358009");
        assertTrue(flag);
    }


    @Test
    public void validaPassword() {
    boolean flag = Valida.isPassword("E#a1");
    assertTrue(flag);
    }





}