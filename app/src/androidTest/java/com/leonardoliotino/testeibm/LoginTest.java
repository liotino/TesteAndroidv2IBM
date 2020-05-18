package com.leonardoliotino.testeibm;

import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @Mock
    EditText inputUsername;

    @Mock
    EditText inputSenha;


    @Test
    public void loginSuccess() {

        String username = "leonardo.liota@gmail.com";
        String password = "Teste@123";


    }




}
