package com.alura.moneyconvert.monedas;

public class OpcionDeCambio {
    private int numero;
    private String baseCode;
    private String targetCode;
    private double tasaDeCambio;

    //Creamos las opciones de cambio de acuerdo a las propiedades creadas arriba


    public MonedaCambiada cambioOpcionMoneda (int numero) {
            switch (numero) {
                case 1:
                    baseCode = "USD";
                    targetCode = "ARS";
                    break;

                case 2:
                    baseCode = "ARS";
                    targetCode = "USD";
                    break;

                case 3:
                    baseCode = "USD";
                    targetCode = "BRL";
                    break;

                case 4:
                    baseCode = "BRL";
                    targetCode = "USD";
                    break;

                case 5:
                    baseCode = "USD";
                    targetCode = "COP";
                    break;

                case 6:
                    baseCode = "COP";
                    targetCode = "USD";
                    break;

                default:
                    System.out.println("");

            }

            return new MonedaCambiada(baseCode, targetCode);

    }
}


