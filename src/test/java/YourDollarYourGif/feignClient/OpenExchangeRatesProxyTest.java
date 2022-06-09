package YourDollarYourGif.feignClient;

import YourDollarYourGif.config.WireMockConfig;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ContextConfiguration(classes = {WireMockConfig.class})
class OpenExchangeRatesProxyTest {

    @Autowired
    private WireMockServer wireExchangeRateService;
    @Autowired
    private OpenExchangeRatesProxy openExchangeRatesProxy;

    @BeforeEach
    void setUp() throws IOException {
        OpenExchangeRatesMocks.setupMockOpenExchagneRatesResponse(wireExchangeRateService);
    }

    @Test
    void whenGetHistoricalExchangeRateBySymbolId_ThenReturnJson() {
        String currentDate = Instant.now().toString().split("T")[0];
        assertFalse(openExchangeRatesProxy.getHistoricalExchangeRateBySymbolId("app_id", "RUB", currentDate).isEmpty());
    }
}