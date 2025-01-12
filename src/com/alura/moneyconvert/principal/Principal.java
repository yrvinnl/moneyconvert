package com.alura.moneyconvert.principal;

import com.alura.moneyconvert.monedas.ExchangeRate;
import com.alura.moneyconvert.monedas.OpcionDeCambio;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal{

    public static void main(String[] args) throws IOException, InterruptedException {
        //Creamos la conexión con el cliente
        HttpClient client = HttpClient.newHttpClient();

        var busqueda = 0;
        boolean esNumeroValido;

        while (busqueda < 7) {
            Encabezado(); // Llamamos a las opciones que tiene nuestra aplicacion
            Scanner opcion = new Scanner(System.in);
            busqueda = Integer.parseInt(opcion.nextLine()); // creamos una variable a nuestro código creado

            if (busqueda == 7) {
                System.out.println("Muchas gracias por usar el conversor de monedas");
                break;
            }

            OpcionDeCambio opcionDeCambio = new OpcionDeCambio();
            var parDeCambio = opcionDeCambio.cambioOpcionMoneda(Integer.parseInt(String.valueOf(busqueda)));

            var base_code = parDeCambio.getBaseCode(); // Selecionamos el par de cambio base
            var target_code = parDeCambio.getTargetCode(); // Seleccionamos el par de cambio objetivo

            //Colocamos el link de la api
            String direccion = "https://v6.exchangerate-api.com/v6/991f99d3a39337f2771f7cad/pair/" + base_code + "/" + target_code;
            //https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/EUR/GBP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String jsonString = response.body();
            System.out.println("Ingresa el valor a convertir");
            Scanner ingresoValor = new Scanner(System.in);
            double valorAConvertir = ingresoValor.nextDouble();




            //Creamos la instancia del Json
            Gson gson = new Gson();
            ExchangeRate exchangeRate = gson.fromJson(jsonString, ExchangeRate.class);

            //Resultado de la tasa de conversion
            var totalCambiado = exchangeRate.getConversion_rate() * valorAConvertir;

            System.out.println("El valor " + valorAConvertir + " [" + base_code + "] corresponde al valor final de =>>> " + totalCambiado + " [" + target_code + "]");
        }







//        do {
//
//            busqueda = Integer.parseInt(opcion.nextLine()); // creamos una variable a nuestro código creado
//            System.out.println("Aún no se encuentra habilitado este tipo de cambio. Por favor seleccione otra opción");
//
//        } while (busqueda > 7 );
////            } while (!esNumeroValido);






    }




    public static void Encabezado() {
        //Creamos el texto introductorio de nuestra app
        System.out.println("**********************************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda =)");
        System.out.println(
                "1) Dólar =>> Peso argentino\n" +
                        "2) Peso argentino =>> Dólar\n" +
                        "3) Dólar =>> Real brasileño\n" +
                        "4) Real brasileño =>> Dólar\n" +
                        "5) Dólar =>> Peso colombiano\n" +
                        "6) Peso colombiano =>> Dólar\n" +
                        "7) Salir\n" +
                        "Elija una opción válida");
        System.out.println("**********************************************************");
    }

    public static boolean esNumero(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
