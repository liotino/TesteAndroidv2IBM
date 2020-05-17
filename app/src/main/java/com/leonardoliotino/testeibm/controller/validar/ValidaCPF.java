package com.leonardoliotino.testeibm.controller.validar;
import android.util.Log;


public class ValidaCPF {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private String editText;


    public ValidaCPF(String peditText) {


        this.editText = peditText;

    }



    private int calcularDigito(String str, int[] peso) {

        int soma = 0;

        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {

            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }

        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;

    }


    private Boolean calcularDigitoFinal() {

        String cpf = this.editText;

        boolean calculoFlagFinal;


        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11)) {

            Log.d("tagcpf","false numeros iguais");

            return false;

        }

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);

        //o calculo dessas variaaveis da o valor de true ou falso no boleano
        calculoFlagFinal = cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());

        if(calculoFlagFinal == true) {

            Log.d("tagcpf","everdade");

            return true;

        }else{

            Log.d("tagcpf","falso");
            return false;

        }


    }


    public boolean isValidCPF() {

        boolean result;

        if(this.calcularDigitoFinal() != true) {

            result = true;

        }else{

            result = false;

        }

        return result;


    }

}
