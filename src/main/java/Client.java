
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Client {
    public static final String REMOTE_URL =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet(REMOTE_URL); //посылаем запрос по URL
        //указываем тип, который будем запрашивать
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request); //получаем ответ от него

        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        httpClient.close(); //не забываем закрыть так как он не автозакрываемый

        readFact(body);
    }

    private static void readFact(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<FactAboutCat> facts = mapper.readValue(body, new TypeReference<>() {
        });

        facts.stream()
                .filter(fact -> fact.getUpvotes() > 0) //отсортировываем только факты набравшие хотя бы 1 голос
                .forEach(System.out::println); //и выводим их
    }
}
