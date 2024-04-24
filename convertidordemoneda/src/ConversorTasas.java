import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class ConversorTasas {
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final String apiKey;

    public ConversorTasas(String apiKey) {
        this.apiKey = apiKey;
    }

    public void convertirMoneda(String monedaOrigen, String monedaDestino) {
        try {
            URI uri = buildURI(monedaOrigen);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                RespuestaTasaCambio respuesta = gson.fromJson(response.body(), RespuestaTasaCambio.class);

                if (respuesta != null && respuesta.getConversionRates() != null) {
                    Map<String, Double> conversionRates = respuesta.getConversionRates();

                    if (conversionRates.containsKey(monedaDestino)) {
                        double tasa = conversionRates.get(monedaDestino);
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Por favor, introduce la cantidad a convertir (" + monedaOrigen + " a " + monedaDestino + "): ");
                        double cantidad = scanner.nextDouble();
                        double cantidadConvertida = cantidad * tasa;
                        System.out.println(cantidad + " " + monedaOrigen + " equivale a " + cantidadConvertida + " " + monedaDestino);
                    } else {
                        System.out.println("No se encontró la tasa de conversión para " + monedaDestino);
                    }
                } else {
                    System.out.println("La respuesta de la API no contiene los datos esperados.");
                }
            } else {
                System.out.println("Error al realizar la solicitud. Código de estado: " + response.statusCode());
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.err.println("Error al realizar la solicitud: " + e.getMessage());
        }
    }

    private URI buildURI(String monedaOrigen) throws URISyntaxException {
        String url = API_BASE_URL + apiKey + "/latest/" + monedaOrigen;
        return new URI(url);
    }
}
