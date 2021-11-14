package ua.petstore.client.requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

public class HttpService {
    private static HttpService instance;
    private final CloseableHttpClient httpClient;
    private final Gson gson;

    public static final String HOST = "https://petstore.swagger.io/v2";

    public static HttpService getInstance() {
        if (instance == null) {
            instance = new HttpService();
        }
        return instance;
    }

    private HttpService() {
        this.httpClient = HttpClients.createDefault();
        this.gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    }

    public String send(HttpUriRequest httpRequest) throws IOException {
        String strJson;
        CloseableHttpResponse response = httpClient.execute(httpRequest);
        InputStream inputStream = response.getEntity().getContent();
        strJson = new String(inputStream.readAllBytes());
        inputStream.close();
        return strJson;
    }

    public Gson getGson() {
        return gson;
    }
}