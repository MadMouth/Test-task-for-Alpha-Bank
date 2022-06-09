package YourDollarYourGif.feignClient;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class OpenExchangeRatesMocks {
    public static void setupMockOpenExchagneRatesResponse(WireMockServer mockService) throws IOException {
        String currentDate = Instant.now().toString().split("T")[0];
        String YesterdayDate = Instant.now().minus(1, ChronoUnit.DAYS).toString().split("T")[0];
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/historical/" + currentDate + ".json?app_id=app_id&symbols=RUB"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        GiphyMocks.class.getClassLoader().getResourceAsStream("payload/get-exchange-rate-today.json"),
                                        defaultCharset()))));
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/historical/" + YesterdayDate + ".json?app_id=app_id&symbols=RUB"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        GiphyMocks.class.getClassLoader().getResourceAsStream("payload/get-exchange-rate-yesterday.json"),
                                        defaultCharset()))));
    }
}
