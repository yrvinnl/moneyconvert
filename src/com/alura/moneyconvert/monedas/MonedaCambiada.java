package com.alura.moneyconvert.monedas;

public class MonedaCambiada {
    //Creamos la propiedad
    private String baseCode;
    private String targetCode;


    public MonedaCambiada(String baseCode, String targetCode) {
        this.baseCode = baseCode;
        this.targetCode = targetCode;
    }

    public String getBaseCode() {
        return baseCode;

    }
    public String getTargetCode() {
        return targetCode;

    }
}
